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

        String recordId = intent.getStringExtra("record_number");
        String date = intent.getStringExtra("date");
        String result = intent.getStringExtra("result");

        if (intent !=null){

           recordID.setText(recordId);
           Date.setText(date);
           Result.setText(result);

        }

    }
    }
