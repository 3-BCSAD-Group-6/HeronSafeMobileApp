package com.example.heronsafeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

public class HistoryActivity extends AppCompatActivity {

    private TextView recordID, Date, Result;
    private MaterialCardView cvFever, cvCough, cvCold, cvBreathless, cvSoreThroat, cvHeadache, cvNoSymptoms, cvQ2A, cvQ2B, cvQ2C, cvQ2D;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recordID = findViewById(R.id.tvScreeningID);
        Date = findViewById(R.id.tvScreeningDate);
        Result = findViewById(R.id.tvScreeningResult);
        cvFever = findViewById(R.id.cvFever);
        cvCough = findViewById(R.id.cvCough);
        cvCold = findViewById(R.id.cvCold);
        cvBreathless = findViewById(R.id.cvBreathless);
        cvSoreThroat = findViewById(R.id.cvSoreThroat);
        cvHeadache = findViewById(R.id.cvHeadache);
        cvNoSymptoms = findViewById(R.id.cvNoSymptoms);
        cvQ2A = findViewById(R.id.cvQ2A);
        cvQ2B = findViewById(R.id.cvQ2B);
        cvQ2C = findViewById(R.id.cvQ2C);
        cvQ2D = findViewById(R.id.cvQ2D);

       //
        // Catching incoming intent
        Intent intent = getIntent();
        String with = "1";
        String recordId = intent.getStringExtra("record_number");
        String date = intent.getStringExtra("date");
        String result = intent.getStringExtra("result");
        String condition = intent.getStringExtra("condition");
        String fever = intent.getStringExtra("fever");
        String cough = intent.getStringExtra("cough");
        String breathless = intent.getStringExtra("breathless");
        String cold = intent.getStringExtra("cold");
        String sorethroat = intent.getStringExtra("sorethroat");
        String headache = intent.getStringExtra("headache");
        String no_symptoms = intent.getStringExtra("no_symptoms");
        String exposure = intent.getStringExtra("exposure");

        if (intent !=null){

           recordID.setText(recordId);
           Date.setText(date);
           Result.setText(result);

           if(fever.equals(with)){
               cvFever.setVisibility(View.VISIBLE);
           }
            if(cough.equals(with)){
                cvCough.setVisibility(View.VISIBLE);
            }
            if(breathless.equals(with)){
                cvBreathless.setVisibility(View.VISIBLE);
            }
            if(cold.equals(with)){
                cvCold.setVisibility(View.VISIBLE);
            }
            if(sorethroat.equals(with)){
                cvSoreThroat.setVisibility(View.VISIBLE);
            }
            if(headache.equals(with)){
                cvHeadache.setVisibility(View.VISIBLE);
            }
            if(no_symptoms.equals(with)){
                cvNoSymptoms.setVisibility(View.VISIBLE);
            }

            switch (exposure){
                case "10":
                    cvQ2A.setVisibility(View.VISIBLE);
                    break;
                case "5":
                    cvQ2B.setVisibility(View.VISIBLE);
                    break;
                case "1":
                    cvQ2C.setVisibility(View.VISIBLE);
                    break;
                case "11":
                    cvQ2A.setVisibility(View.VISIBLE);
                    cvQ2C.setVisibility(View.VISIBLE);
                    break;
                case "15":
                    cvQ2A.setVisibility(View.VISIBLE);
                    cvQ2B.setVisibility(View.VISIBLE);
                    break;
                case "6":
                    cvQ2C.setVisibility(View.VISIBLE);
                    cvQ2B.setVisibility(View.VISIBLE);
                    break;
                case "0":
                    cvQ2D.setVisibility(View.VISIBLE);
                    break;
            }

        }

    }
    }
