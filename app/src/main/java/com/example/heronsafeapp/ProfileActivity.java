package com.example.heronsafeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {
Button btBack;
AppCompatButton btEdit, btCancel, btSubmit;
BottomNavigationView bottomNavigationView;
TextView etFullname, etStudentId, etEmail,etContactNum, etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //casting button-back
        btBack = findViewById(R.id.btProfileBack);
        btBack.setBackground(null);

        etFullname = findViewById(R.id.etProfileFullname);
        etStudentId = findViewById(R.id.etProfileStudentId);
        etContactNum = findViewById(R.id.etProfileContactNum);
        etEmail = findViewById(R.id.etProfileEmail);
        etPassword = findViewById(R.id.etProfilePassword);

        //bottom navigation bar
        bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.setSelectedItemId(R.id.menuProfile);

        btEdit = findViewById(R.id.btEditProfile);
        btCancel = findViewById(R.id.btnCancelProfile);
        btSubmit = findViewById(R.id.btnSubmitProfile);

        etFullname.setText(SharedPrefManager.getInstance(this).getFullName());
        etStudentId.setText(SharedPrefManager.getInstance(this).getStudentId());
        etContactNum.setText(SharedPrefManager.getInstance(this).getContact());
        etEmail.setText(SharedPrefManager.getInstance(this).getEmail());


        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btCancel.setVisibility(View.VISIBLE);
                btSubmit.setVisibility(View.VISIBLE);

                etFullname.setEnabled(true);
                etStudentId.setEnabled(true);
                etContactNum.setEnabled(true);
                etEmail.setEnabled(true);
                etPassword.setEnabled(true);
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btCancel.setVisibility(View.GONE);
                btSubmit.setVisibility(View.GONE);

                etFullname.setEnabled(false);
                etStudentId.setEnabled(false);
                etContactNum.setEnabled(false);
                etEmail.setEnabled(false);
                etPassword.setEnabled(false);

            }
        });

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

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
                Intent b = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(b);
            }
        });
    }
}