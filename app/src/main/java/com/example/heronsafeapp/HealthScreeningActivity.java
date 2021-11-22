package com.example.heronsafeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HealthScreeningActivity extends AppCompatActivity implements View.OnClickListener {
Button btBack;
BottomNavigationView bottomNavigationView;
ImageView checkFever, checkCough, checkBreathless, checkCold, checkSoreThroat, checkHeadache;
CardView cvQ1Fever, cvQ1Cough, cvQ1Breathless, cvQ1Cold, cvQ1SoreThroat, cvQ1Headache, cvQ2Ans1, cvQ2Ans2, cvQ2Ans3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_screening);

        //casting button-back
        btBack = findViewById(R.id.btHealthScreenback);
        btBack.setBackground(null);

        cvQ1Fever = findViewById(R.id.cvQ1Fever);
        cvQ1Cough = findViewById(R.id.cvQ1Cough);
        cvQ1Breathless = findViewById(R.id.cvQ1Breathlessness);
        cvQ1Cold = findViewById(R.id.cvQ1Cold);
        cvQ1SoreThroat = findViewById(R.id.cvQ1SoreThroat);
        cvQ1Headache = findViewById(R.id.cvQ1Headache);
        cvQ2Ans1 = findViewById(R.id.cvQ2A1);
        cvQ2Ans2 = findViewById(R.id.cvQ2A2);
        cvQ2Ans3 = findViewById(R.id.cvQ2A3);

        checkFever = findViewById(R.id.ivCheckQ1Fever);
        checkCough = findViewById(R.id.ivCheckQ1Cough);
        checkBreathless = findViewById(R.id.ivCheckQ1Breathlessness);
        checkCold = findViewById(R.id.ivCheckQ1Cold);
        checkSoreThroat = findViewById(R.id.ivCheckQ1SoreThroat);
        checkHeadache = findViewById(R.id.ivCheckQ1Headache);

        cvQ1Fever.setOnClickListener(this);
        cvQ1Cough.setOnClickListener(this);
        cvQ1Breathless.setOnClickListener(this);
        cvQ1Cold.setOnClickListener(this);
        cvQ1SoreThroat.setOnClickListener(this);
        cvQ1Headache.setOnClickListener(this);
        cvQ2Ans1.setOnClickListener(this);
        cvQ2Ans2.setOnClickListener(this);
        cvQ2Ans3.setOnClickListener(this);

        //bottom navigation bar
        bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.setSelectedItemId(R.id.empty);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.menuHome:
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.menuNotification:
                    startActivity(new Intent(getApplicationContext(), NotificationsActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.menuContacts:
                    startActivity(new Intent(getApplicationContext(), ContactsActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.menuProfile:
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(HealthScreeningActivity.this, HomeActivity.class);
                startActivity(b);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_items, menu);
        return true;
    }

    @Override
    public void onClick(View v) {


        if(v.getId() == R.id.cvQ1Fever){
            checkFever.setVisibility(View.VISIBLE);
        }
        if(v.getId() == R.id.cvQ1Cough){
            checkCough.setVisibility(View.VISIBLE);
        }
        if(v.getId() == R.id.cvQ1Breathlessness){
            checkBreathless.setVisibility(View.VISIBLE);
        }
        if(v.getId() == R.id.cvQ1Cold){
            checkCold.setVisibility(View.VISIBLE);
        }
        if(v.getId() == R.id.cvQ1SoreThroat){
            checkSoreThroat.setVisibility(View.VISIBLE);
        }
        if(v.getId() == R.id.cvQ1Headache){
            checkHeadache.setVisibility(View.VISIBLE);
        }

        if(v.getId() == R.id.cvQ2A1){
            cvQ2Ans1.setCardBackgroundColor(Color.parseColor("#E4DBA8"));
        }
        if(v.getId() == R.id.cvQ2A2){
            cvQ2Ans2.setCardBackgroundColor(Color.parseColor("#E4DBA8"));
        }
        if(v.getId() == R.id.cvQ2A3){
            cvQ2Ans3.setCardBackgroundColor(Color.parseColor("#E4DBA8"));
        }

    }
}