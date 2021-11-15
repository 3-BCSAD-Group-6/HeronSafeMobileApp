package com.example.heronsafeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class RegistrationActivity extends AppCompatActivity {
ImageButton btnBack2;
TextInputEditText etFullname, etStudentID, etEmail, etPassword, etContactNum;
Button btnRegister;
TextView tvSignin;
ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        btnBack2 = findViewById(R.id.btnBackRegistration);

        etFullname = findViewById(R.id.etRegisterFName);
        etStudentID = findViewById(R.id.etRegisterStudentID);
        etEmail = findViewById(R.id.etRegisterEmail);
        etPassword = findViewById(R.id.etRegisterPassword);
        etContactNum = findViewById(R.id.etRegisterContactNum);

        btnRegister = findViewById(R.id.btnRegister);

        tvSignin = findViewById(R.id.tvSignIn);

        progressBar = findViewById(R.id.progressBarRegister);

        btnBack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent c = new Intent(RegistrationActivity.this, StartUp.class);
                startActivity(c);
                finish();
            }
        });

        tvSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String student_id, email, password, fullname, contact_number;

                student_id = String.valueOf(etStudentID.getText());
                email = String.valueOf(etEmail.getText());
                password = String.valueOf(etPassword.getText());
                fullname = String.valueOf(etFullname.getText());
                contact_number = String.valueOf(etContactNum.getText());

                if (!student_id.equals("") && !email.equals("") && !password.equals("") && !fullname.equals("") && !contact_number.equals("")){

                    progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[5];
                            field[0] = "student_id";
                            field[1] = "email";
                            field[2] = "password";
                            field[3] = "fullname";
                            field[4] = "contact_number";
                            //Creating array for data
                            String[] data = new String[5];
                            data[0] = student_id;
                            data[1] = email;
                            data[2] = password;
                            data[3] = fullname;
                            data[4] = contact_number;
                            PutData putData = new PutData("http://192.168.100.165/heron-safe/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    if(result.equals("Sign Up Success")){
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(getApplicationContext(), "All Fields are Required", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}