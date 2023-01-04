package com.example.dogcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dogcare.models.create_users_model;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class users_profile extends AppCompatActivity {
    BottomNavigationView bottom_navigation;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    Button back_btn,logout_btn;
    EditText full_name,contact_number,current_address,email_address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_profile);
        full_name = findViewById(R.id.full_name);
        contact_number = findViewById(R.id.contact_number);
        current_address = findViewById(R.id.current_address);
        email_address = findViewById(R.id.email_address);
        logout_btn = findViewById(R.id.logout_btn);
        back_btn = findViewById(R.id.back_btn);



        bottom_navigation = findViewById(R.id.bottom_navigation_users);
        bottom_navigation.setSelectedItemId(R.id.nav_profile);

        bottom_navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(), users_home_nav.class));
                        overridePendingTransition(0, 0);
                        finish();

                        return true;

                    case R.id.nav_setAppointment:
                        startActivity(new Intent(getApplicationContext(), users_setAppointments_nav.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;

                    case R.id.nav_myAppointments:
                        startActivity(new Intent(getApplicationContext(), users_myAppointments_nav.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;

                    case R.id.nav_profile:

                        return true;
                }
                return false;
            }
        });

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(users_profile.this,users_login.class));
                finish();
            }
        });
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(users_profile.this,users_home_nav.class));
                finish();
            }
        });
        FirebaseUser userid = mAuth.getCurrentUser();
        DocumentReference docRef = db.collection("users").document(userid.getUid());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                create_users_model create = documentSnapshot.toObject(create_users_model.class);
                if (create != null) {
                    full_name = findViewById(R.id.full_name);
                    contact_number = findViewById(R.id.contact_number);
                    current_address = findViewById(R.id.current_address);
                    email_address = findViewById(R.id.email_address);


                    full_name.setText(create.getFullname());
                    contact_number.setText(create.getPhonenumber());
                    current_address.setText(create.getCurrentaddress());
                    email_address.setText(create.getEmailaddress());

                }
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(users_profile.this , users_home_nav.class));
        finish();
    }
}