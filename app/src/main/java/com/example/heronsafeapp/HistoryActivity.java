package com.example.heronsafeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HistoryActivity extends AppCompatActivity {

    private TextView recordID, Date, Result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recordID = findViewById(R.id.tvScreeningID);
        Date = findViewById(R.id.tvScreeningDate);
        Result = findViewById(R.id.tvScreeningResult);
       //
        // Catching incoming intent
        Intent intent = getIntent();

        String student_id = intent.getStringExtra("student_id");
        String date = intent.getStringExtra("date");
        String result = intent.getStringExtra("result");

        if (intent !=null){

           recordID.setText(student_id);
           Date.setText(date);
           Result.setText(result);

        }

    }
    }
