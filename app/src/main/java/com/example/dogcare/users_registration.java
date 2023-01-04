package com.example.dogcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dogcare.models.create_users_model;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class users_registration extends AppCompatActivity {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText full_name,phone_number,current_address,email_address,passWord;
    Button register_button, back_btn;
    create_users_model createUsersModel;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_registration);

        progressBar = findViewById(R.id.progressbar);
        createUsersModel = new create_users_model();
        full_name = findViewById(R.id.full_name);
        phone_number = findViewById(R.id.phone_number);
        current_address = findViewById(R.id.current_address);
        email_address = findViewById(R.id.email_address);
        passWord = findViewById(R.id.passWord);
        register_button = findViewById(R.id.register_button);
        back_btn = findViewById(R.id.back_btn);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(users_registration.this , users_login.class));
                finish();
            }
        });

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount();
                progressBar.setVisibility(View.VISIBLE);
            }
        });



    }

    private void createAccount() {

        createUsersModel.setFullname(full_name.getText().toString().trim());
        createUsersModel.setPhonenumber(phone_number.getText().toString().trim());
        createUsersModel.setCurrentaddress(current_address.getText().toString().trim());
        createUsersModel.setEmailaddress(email_address.getText().toString().trim());
        createUsersModel.setPassword(passWord.getText().toString().trim());


        mAuth.createUserWithEmailAndPassword(email_address.getText().toString().trim(),
                passWord.getText().toString().trim()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                FirebaseUser userid = mAuth.getCurrentUser();
                createUsersModel.setUsers_id(userid.getUid());
                create_users_model  createUser = new create_users_model(createUsersModel.getFullname(),createUsersModel.getPhonenumber()
                        ,createUsersModel.getCurrentaddress(),createUsersModel.getEmailaddress(),createUsersModel.getPassword()
                        ,createUsersModel.getUsers_id());


                db.collection("users").document(userid.getUid()).set(createUser)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(users_registration.this,
                                        "Created Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(users_registration.this , users_login.class));
                                finish();
                                progressBar.setVisibility(View.GONE);
                            }
                        });
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(users_registration.this , users_login.class));
        finish();
    }
}