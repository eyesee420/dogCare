package com.example.dogcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.dogcare.adapters.bologpost_adapter;
import com.example.dogcare.models.blogs_post_model;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class admin_home_nav extends AppCompatActivity {
    BottomNavigationView bottom_navigation;
    FloatingActionButton add_btn;

    RecyclerView recyclerView;

    private bologpost_adapter adapter;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_nav);

        recyclerView = findViewById(R.id.recycle_view);
        add_btn = findViewById(R.id.add_btn);
        bottom_navigation = findViewById(R.id.bottom_navigation_admin);
        bottom_navigation.setSelectedItemId(R.id.nav_home);


        bottom_navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        return true;

                    case R.id.nav_reqAppointments:
                        startActivity(new Intent(getApplicationContext(), admin_request_appointments.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;

                    case R.id.nav_confirmed_Appointments:
                        startActivity(new Intent(getApplicationContext(), admin_confirmed_appointments.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;

                    case R.id.nav_profile:
                        startActivity(new Intent(getApplicationContext(), admin_profile.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                }
                return false;
            }
        });

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(admin_home_nav.this , admin_add_post.class));
                finish();
            }
        });
        recyclerview();
    }

    private void recyclerview() {
        Query query = db.collection("blogs and post");

        FirestoreRecyclerOptions<blogs_post_model> options = new FirestoreRecyclerOptions.Builder<blogs_post_model>()
                .setQuery(query, blogs_post_model.class).build();



        adapter = new bologpost_adapter(options);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(admin_home_nav.this , admin_login.class));
        finish();
    }
}