package com.example.dogcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class admin_login extends AppCompatActivity {

    EditText email_address ,passWord ;
    Button login_button;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        email_address = findViewById(R.id.email_address);
        passWord = findViewById(R.id.passWord);

        login_button = findViewById(R.id.login_button);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login();

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

                        String email ="admin@gmail.com";
                        String pass = "123456";
                        if(email.equals(emailaddress) && pass.equals(userpassword) ){
                            startActivity(new Intent(admin_login.this , admin_home_nav.class));
                            finish();
                            Toast.makeText(admin_login.this, "Welcome", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(admin_login.this, "please login correct admin account", Toast.LENGTH_SHORT).show();
                        }


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(admin_login.this, "please Enter valid email and password", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}