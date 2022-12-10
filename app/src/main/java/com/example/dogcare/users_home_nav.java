package com.example.dogcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class users_home_nav extends AppCompatActivity {
    BottomNavigationView bottom_navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_home_nav);

        bottom_navigation = findViewById(R.id.bottom_navigation_users);
        bottom_navigation.setSelectedItemId(R.id.nav_home);

        ImageSlider imageSlider = findViewById(R.id.image_slider);
        List<SlideModel> slideModels=new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.slider_dog1 , ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slider_dog2 , ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slider_dog3 , ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slider_dog4 , ScaleTypes.FIT));
        imageSlider.setImageList(slideModels,ScaleTypes.FIT);


        bottom_navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        return true;

                    case R.id.nav_set_Schedule:
                        startActivity(new Intent(getApplicationContext(), users_shed_nav.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;

                    case R.id.nav_appointments:
                        startActivity(new Intent(getApplicationContext(), users_apointment_nav.class));
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



    }
}