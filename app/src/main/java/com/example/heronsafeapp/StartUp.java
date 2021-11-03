package com.example.heronsafeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartUp extends AppCompatActivity {
Button btnLogin, btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);

        btnLogin = findViewById(R.id.btnLoginStartUp);
        btnRegister = findViewById(R.id.btnRegisterStartUp);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l = new Intent(StartUp.this,LoginActivity.class);
                startActivity(l);
            }
        });

    }
}