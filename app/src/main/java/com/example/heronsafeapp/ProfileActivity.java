package com.example.heronsafeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {
Button btBack;
BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //casting button-back
        btBack = findViewById(R.id.btProfileBack);
        btBack.setBackground(null);

        //casting bottom navigation bar
        bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.setBackground(null);

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(b);
            }
        });
    }
}