package com.example.heronsafeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {
Button btBack;
AppCompatButton btEdit, btCancel, btSubmit, btChangePass,btSavePass,btCancelPass;
BottomNavigationView bottomNavigationView;
TextView etFullname, etStudentId, etEmail,etContactNum, etPassword;
String [] deptList = {"Application Development", "Social Computing", "Computational and Data Sciences", "Information and Network Security"};
String [] genderList = {"Female", "Male"};
AutoCompleteTextView ACGender, ACDept;
ArrayAdapter<String> DeptAdapter;
ArrayAdapter<String> GenderAdapter;
String uGender, dept, time, getPasstime,getGender, getDept;
TextInputLayout tilPass, tilName, tilEmail, tilStudentId, tilGender, tilDept, tilContact;
LinearLayout linProfile, linPassword;
private ProgressDialog progressDialog;
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
        btChangePass = findViewById(R.id.btChangePassword);
        btSavePass = findViewById(R.id.btnSubmitPass);
        btCancelPass = findViewById(R.id.btnCancelPass);

        etFullname.setText(SharedPrefManager.getInstance(this).getFullName());
        etStudentId.setText(SharedPrefManager.getInstance(this).getStudentId());
        etContactNum.setText(SharedPrefManager.getInstance(this).getContact());
        etEmail.setText(SharedPrefManager.getInstance(this).getEmail());

        tilPass = findViewById(R.id.layoutPassword);
        tilEmail = findViewById(R.id.layoutEmail);
        tilName = findViewById(R.id.layoutName);
        tilStudentId = findViewById(R.id.layoutStudentId);
        tilGender = findViewById(R.id.LayoutGender);
        tilDept = findViewById(R.id.LayoutDepartment);
        tilContact = findViewById(R.id.layoutContact);

        linProfile = findViewById(R.id.lnEditInfo);
        linPassword = findViewById(R.id.lnChangepass);
        progressDialog = new ProgressDialog(this);

        tilPass.setVisibility(View.GONE);

        ACGender = findViewById(R.id.tvGender);
        ACDept = findViewById(R.id.tvDepartment);

        ACDept.setText(SharedPrefManager.getInstance(this).getDepartment());
        ACGender.setText(SharedPrefManager.getInstance(this).getGender());

        DeptAdapter = new ArrayAdapter<>(this, R.layout.list_if_vaccined, deptList);
        GenderAdapter = new ArrayAdapter<>(this, R.layout.list_if_vaccined, genderList);

        getGender = SharedPrefManager.getInstance(this).getGender();
        getDept = SharedPrefManager.getInstance(this).getDepartment();

        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linProfile.setVisibility(View.VISIBLE);
                tilPass.setVisibility(View.GONE);
                tilName.setVisibility(View.GONE);
                tilEmail.setVisibility(View.GONE);
                tilStudentId.setVisibility(View.GONE);
                btEdit.setVisibility(View.INVISIBLE);
                btChangePass.setVisibility(View.INVISIBLE);

                tilDept.setEnabled(true);
                tilGender.setEnabled(true);
                etContactNum.setEnabled(true);

                ACGender.setAdapter(GenderAdapter);
                ACDept.setAdapter(DeptAdapter);

                uGender = getGender;
                dept = getDept;

                ACGender.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        uGender = parent.getItemAtPosition(position).toString();
                    }
                });

                ACDept.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        dept = parent.getItemAtPosition(position).toString();
                    }
                });
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linProfile.setVisibility(View.GONE);
                btEdit.setVisibility(View.VISIBLE);
                btChangePass.setVisibility(View.VISIBLE);

                tilName.setVisibility(View.VISIBLE);
                tilEmail.setVisibility(View.VISIBLE);
                tilStudentId.setVisibility(View.VISIBLE);

                etFullname.setEnabled(false);
                etStudentId.setEnabled(false);
                etContactNum.setEnabled(false);
                etEmail.setEnabled(false);

                tilDept.setEnabled(false);
                tilGender.setEnabled(false);

            }
        });

        btCancelPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linPassword.setVisibility(View.GONE);
                tilName.setVisibility(View.VISIBLE);
                tilEmail.setVisibility(View.VISIBLE);
                tilStudentId.setVisibility(View.VISIBLE);

                etFullname.setEnabled(false);
                etStudentId.setEnabled(false);
                etContactNum.setEnabled(false);
                etEmail.setEnabled(false);

                tilDept.setEnabled(false);
                tilGender.setEnabled(false);

                tilPass.setEnabled(false);
            }
        });

        btChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linPassword.setVisibility(View.VISIBLE);
                tilPass.setVisibility(View.VISIBLE);
                btEdit.setVisibility(View.INVISIBLE);
                btChangePass.setVisibility(View.INVISIBLE);

                tilName.setVisibility(View.GONE);
                tilEmail.setVisibility(View.GONE);
                tilStudentId.setVisibility(View.GONE);
                tilGender.setVisibility(View.GONE);
                tilDept.setVisibility(View.GONE);
                tilContact.setVisibility(View.GONE);

                tilPass.setEnabled(true);
            }
        });

        btSavePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                getPasstime = sdf.format(Calendar.getInstance().getTime());
                changePass();
            }
        });

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                time = sdf.format(Calendar.getInstance().getTime());
                updateUser();
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

    private void changePass() {
        final String password = etPassword.getText().toString().trim();
        final String email = SharedPrefManager.getInstance(this).getEmail();
        final String updated_at = getPasstime;
        progressDialog.setMessage("Changing Password...");
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
                params.put("email",email);
                params.put("password",password);
                params.put("updated_at",updated_at);

                return params;

            }

        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void updateUser() {
        final String contact_number = etContactNum.getText().toString().trim();
        final String email = SharedPrefManager.getInstance(this).getEmail();
        final String updated_at = time;
        final String gender = uGender;
        final String department = dept;

        progressDialog.setMessage("Updating User Information...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_UPDATE,
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
                params.put("email",email);
                params.put("department",department);
                params.put("contact_number",contact_number);
                params.put("gender",gender);
                params.put("updated_at",updated_at);

                return params;

            }

        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        linProfile.setVisibility(View.GONE);
        btEdit.setVisibility(View.VISIBLE);
        btChangePass.setVisibility(View.VISIBLE);

        tilName.setVisibility(View.VISIBLE);
        tilEmail.setVisibility(View.VISIBLE);
        tilStudentId.setVisibility(View.VISIBLE);

        etFullname.setEnabled(false);
        etStudentId.setEnabled(false);
        etContactNum.setEnabled(false);
        etEmail.setEnabled(false);

        tilDept.setEnabled(false);
        tilGender.setEnabled(false);


    }
}