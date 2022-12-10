package com.example.dogcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class users_login extends AppCompatActivity {
    Button login_button ,register_button ,login_admin_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_login);

        login_button = findViewById(R.id.login_button);
        register_button = findViewById(R.id.register_button);
        login_admin_btn = findViewById(R.id.login_admin_btn);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(users_login.this , users_home_nav.class));
            }
        });
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(users_login.this , users_registration.class));
            }
        });
        login_admin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(users_login.this , admin_login.class));
            }
        });
    }
}