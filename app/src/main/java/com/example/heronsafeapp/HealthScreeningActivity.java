package com.example.heronsafeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HealthScreeningActivity extends AppCompatActivity {
Button btBack;
BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_screening);

        //casting button-back
        btBack = findViewById(R.id.btHealthScreenback);
        btBack.setBackground(null);

        //casting bottom navigation bar
        bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.setBackground(null);

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(HealthScreeningActivity.this, HomeActivity.class);
                startActivity(b);
            }
        });
    }
}