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

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
private ProgressDialog progressDialog;
ImageButton btnBack;
TextInputEditText etEmail, etPassword;
Button btnLogin;
TextView tvSignup;
//ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnBack = findViewById(R.id.btnBackLogin);

        etEmail = findViewById(R.id.etLoginEmail);
        etPassword = findViewById(R.id.etLoginPassword);

        btnLogin = findViewById(R.id.btnLogin);

        tvSignup = findViewById(R.id.tvRegisterNow);
        progressDialog = new ProgressDialog(this);

        //progressBar = findViewById(R.id.progressBarLogin);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(LoginActivity.this, StartUp.class);
                startActivity(b);
                finish();
            }
        });

        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });
    }

    private void userLogin() {
        final String email = etEmail.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();

        progressDialog.show();
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST, Config.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")){
                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(
                                        obj.getInt("id"),
                                        obj.getString("name"),
                                        obj.getString("student_id"),
                                        obj.getString("contact_number"),
                                        obj.getString("email"),
                                        obj.getString("role"),
                                        obj.getString("created_at"));


                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);
                                finish();

                            }else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String , String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
}