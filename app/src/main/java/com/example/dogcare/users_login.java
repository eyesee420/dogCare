package com.example.dogcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class users_login extends AppCompatActivity {
    Button login_button ,register_button ,login_admin_btn;
    EditText email_address ,passWord ;
    ProgressBar progressBar;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_login);

        email_address = findViewById(R.id.email_address);
        passWord = findViewById(R.id.passWord);

        login_button = findViewById(R.id.login_button);
        register_button = findViewById(R.id.register_button);
        login_admin_btn = findViewById(R.id.login_admin_btn);
        progressBar = findViewById(R.id.progressbar);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
                progressBar.setVisibility(View.VISIBLE);
            }
        });
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(users_login.this , users_registration.class));
                finish();
            }
        });
        login_admin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(users_login.this , admin_login.class));

            }
        });
    }

    private void login() {
        String emailaddress = email_address.getText().toString().trim();
        String userpassword = passWord.getText().toString().trim();
        if(emailaddress.isEmpty()){
            email_address.setError("Email Required!");
            email_address.requestFocus();
            return;
        }
        if(userpassword.isEmpty()){
            passWord.setError("Password Required!");
            passWord.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(emailaddress,userpassword)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(users_login.this , users_home_nav.class));
                        finish();
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(users_login.this, "Welcome", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(users_login.this, "please Enter valid email and password", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}