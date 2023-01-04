package com.example.dogcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.dogcare.adapters.apointments_adapter;
import com.example.dogcare.adapters.apointments_admin_confirmed_adapter;
import com.example.dogcare.adapters.apointments_users_confirmed_adapter;
import com.example.dogcare.models.apointments_model;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Calendar;

public class users_setAppointments_nav extends AppCompatActivity {
    BottomNavigationView bottom_navigation;
    FloatingActionButton set_btn;

    private apointments_users_confirmed_adapter adapter;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    RecyclerView recycle_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_set_appointments_nav);

        recycle_view =findViewById(R.id.recycle_view);
        set_btn = findViewById(R.id.set_btn);
        bottom_navigation = findViewById(R.id.bottom_navigation_users);
        bottom_navigation.setSelectedItemId(R.id.nav_setAppointment);

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

                        return true;

                    case R.id.nav_myAppointments:
                        startActivity(new Intent(getApplicationContext(), users_myAppointments_nav.class));
                        overridePendingTransition(0, 0);
                        finish();
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



        set_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(users_setAppointments_nav.this , set_appointment.class));
            }
        });

        recycleview();
    }

    private void recycleview() {
        Query query = db.collection("Confirmed Appointments");

        FirestoreRecyclerOptions<apointments_model> options = new FirestoreRecyclerOptions.Builder<apointments_model>()
                .setQuery(query, apointments_model.class).build();



        adapter = new apointments_users_confirmed_adapter(options);
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

        startActivity(new Intent(users_setAppointments_nav.this , users_home_nav.class));
        finish();
    }

}