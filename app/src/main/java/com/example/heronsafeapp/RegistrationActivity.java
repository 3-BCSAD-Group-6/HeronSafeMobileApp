package com.example.heronsafeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.textfield.TextInputEditText;
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
TextInputEditText etFullname, etStudentID, etEmail, etPassword, etContactNum;
Button btnRegister;
String time = "";
TextView tvSignin;
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

        btnRegister = findViewById(R.id.btnRegister);

        tvSignin = findViewById(R.id.tvSignIn);

        //progressBar = findViewById(R.id.progressBarRegister);
        progressDialog = new ProgressDialog(this);

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