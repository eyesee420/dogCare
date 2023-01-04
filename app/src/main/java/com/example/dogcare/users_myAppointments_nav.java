package com.example.dogcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.dogcare.adapters.apointments_adapter;
import com.example.dogcare.models.apointments_model;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class users_myAppointments_nav extends AppCompatActivity {

    private apointments_adapter adapter;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    RecyclerView recycle_view;
    BottomNavigationView bottom_navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_my_appointments_nav);


        recycle_view =findViewById(R.id.recycle_view);
        bottom_navigation = findViewById(R.id.bottom_navigation_users);
        bottom_navigation.setSelectedItemId(R.id.nav_myAppointments);

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
                        return true;

                    case R.id.nav_profile:
                        startActivity(new Intent(getApplicationContext(), users_profile.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                }
                return false;
            }
        });

        recycleview();


    }

    private void recycleview() {


        FirebaseUser userid = mAuth.getCurrentUser();
        Query query = db.collection("appointments").document(userid.getUid())
                .collection("my sched");

        FirestoreRecyclerOptions<apointments_model> options = new FirestoreRecyclerOptions.Builder<apointments_model>()
                .setQuery(query, apointments_model.class).build();



        adapter = new apointments_adapter(options);
        recycle_view.setHasFixedSize(true);
        recycle_view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recycle_view.setAdapter(adapter);
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
        super.onBackPressed();

        startActivity(new Intent(users_myAppointments_nav.this , users_home_nav.class));
        finish();
    }
}