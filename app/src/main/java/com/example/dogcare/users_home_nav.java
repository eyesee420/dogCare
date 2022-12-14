package com.example.dogcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.dogcare.adapters.apointments_adapter;
import com.example.dogcare.adapters.bologpost_adapter;
import com.example.dogcare.models.apointments_model;
import com.example.dogcare.models.blogs_post_model;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

public class users_home_nav extends AppCompatActivity {
    BottomNavigationView bottom_navigation;
    RecyclerView recyclerView;

    private bologpost_adapter adapter;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_home_nav);

        recyclerView = findViewById(R.id.recycle_view);
        bottom_navigation = findViewById(R.id.bottom_navigation_users);
        bottom_navigation.setSelectedItemId(R.id.nav_home);

        ImageSlider imageSlider = findViewById(R.id.image_slider);
        List<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.slider_dog1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slider_dog2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slider_dog3, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slider_dog4, ScaleTypes.FIT));
        imageSlider.setImageList(slideModels, ScaleTypes.FIT);


        bottom_navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_home:
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
                        startActivity(new Intent(getApplicationContext(), users_profile.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                }
                return false;
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
        startActivity(new Intent(users_home_nav.this , users_login.class));
        finish();
    }

}