package com.example.heronsafeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {
private ProgressDialog progressDialog;
ImageButton btnBack2;
TextInputLayout tlVaccinetype, tl1stDose, tl2ndDose;
TextInputEditText etFullname, etStudentID, etEmail, etPassword, etContactNum, etFirstVaccine, etSecondVaccine;
Button btnRegister;
String time = "";
String [] ifVaccined = {"Yes", "No"};
String [] VaccineType = {"Pfizer", "Moderna", "Sinovac", "AstraZeneca", "J&J/Janssen", "Sinopharm"};
TextView tvSignin;
AutoCompleteTextView ACVaccined, ACVaccineType;
ArrayAdapter<String> ifVaccinedAdapter;
ArrayAdapter<String> VaccineTypeAdapter;
//ProgressBar progressBar;
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
        etFirstVaccine = findViewById(R.id.etFirstVaccineDate);
        etSecondVaccine = findViewById(R.id.etSecondVaccineDate);

        tlVaccinetype = findViewById(R.id.txtLayoutVaccineType);
        tl1stDose = findViewById(R.id.txtLayoutFirstVaccine);
        tl2ndDose = findViewById(R.id.txtLayoutSecondVaccine);


        btnRegister = findViewById(R.id.btnRegister);

        tvSignin = findViewById(R.id.tvSignIn);

        //progressBar = findViewById(R.id.progressBarRegister);
        progressDialog = new ProgressDialog(this);

        etFirstVaccine.setInputType(InputType.TYPE_NULL);
        etSecondVaccine.setInputType(InputType.TYPE_NULL);

        ACVaccined = findViewById(R.id.tvACVaccined);
        ACVaccineType = findViewById(R.id.tvACVaccineType);
        VaccineTypeAdapter = new ArrayAdapter<>(this, R.layout.list_if_vaccined, VaccineType);
        ifVaccinedAdapter = new ArrayAdapter<>(this, R.layout.list_if_vaccined, ifVaccined);
        ACVaccined.setAdapter(ifVaccinedAdapter);
        ACVaccineType.setAdapter(VaccineTypeAdapter);

        ACVaccined.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String vaccined = parent.getItemAtPosition(position).toString();
                if(vaccined == "Yes"){
                    tlVaccinetype.setVisibility(view.VISIBLE);
                    tl1stDose.setVisibility(view.VISIBLE);
                    tl2ndDose.setVisibility(view.VISIBLE);
                }
                else if (vaccined == "No"){
                    tlVaccinetype.setVisibility(view.GONE);
                    tl1stDose.setVisibility(view.GONE);
                    tl2ndDose.setVisibility(view.GONE);
                }
            }
        });

        ACVaccineType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String vaccineType = parent.getItemAtPosition(position).toString();
            }
        });

        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Select date");
        final MaterialDatePicker materialDatePicker = builder.build();

        MaterialDatePicker.Builder builder2 = MaterialDatePicker.Builder.datePicker();
        builder2.setTitleText("Select date");
        final MaterialDatePicker materialDatePicker2 = builder.build();

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                etFirstVaccine.setText(materialDatePicker.getHeaderText());
            }
        });

        materialDatePicker2.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                etSecondVaccine.setText(materialDatePicker.getHeaderText());
            }
        });

        etFirstVaccine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getSupportFragmentManager(), "DATE_PICKER");
            }
        });

        etSecondVaccine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker2.show(getSupportFragmentManager(), "DATE_PICKER");
            }
        });

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
                final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                time = sdf.format(Calendar.getInstance().getTime());
                registerUser();
            }
        });

    }

    private void registerUser() {
        final String fullname = etFullname.getText().toString().trim();
        final String student_id = etStudentID.getText().toString().trim();
        final String contact_number = etContactNum.getText().toString().trim();
        final String email = etEmail.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();
        final String role = "student";
        final String created_at = time;



        progressDialog.setMessage("Registering User...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("name",fullname);
                params.put("student_id",student_id);
                params.put("contact_number",contact_number);
                params.put("email",email);
                params.put("password",password);
                params.put("role",role);
                params.put("created_at",created_at);

                return params;

            }

        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

        etFullname.getText().clear();
        etStudentID.getText().clear();
        etContactNum.getText().clear();
        etEmail.getText().clear();
        etPassword.getText().clear();
    }
}