package com.example.heronsafeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
BottomNavigationView bottomNavigationView;
CardView cvHealthScreening, cvExposure, cvAnnouncement, cvHealthAdvice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //casting bottom navigation bar
        bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.setBackground(null);

        //casting card views
        cvHealthScreening = findViewById(R.id.cvHealthScreening);
        cvExposure = findViewById(R.id.cvExposure);
        cvAnnouncement = findViewById(R.id.cvAnnouncement);
        cvHealthAdvice = findViewById(R.id.cvHealthAdvice);

        //Click Listener for Card Views
        cvHealthScreening.setOnClickListener(this);
        cvExposure.setOnClickListener(this);
        cvAnnouncement.setOnClickListener(this);
        cvHealthAdvice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch(v.getId()){
            case R.id.cvHealthScreening:
                i = new Intent(this, HealthScreeningActivity.class);
                startActivity(i);
                break;
            case R.id.cvExposure:
                i = new Intent(this, ExposureActivity.class);
                startActivity(i);
                break;
            case R.id.cvAnnouncement:
                i = new Intent(this, AnnouncementActivity.class);
                startActivity(i);
                break;
            case R.id.cvHealthAdvice:
                i = new Intent(this, HealthAdviceActivity.class);
                startActivity(i);
                break;
            default: break;
        }
    }
}