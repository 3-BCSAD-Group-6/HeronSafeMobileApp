package com.example.heronsafeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HealthScreeningActivity extends AppCompatActivity implements View.OnClickListener {
Button btBack;
private ProgressDialog progressDialog;
AppCompatButton btSubmit;
MaterialCardView cvQ1Fever, cvQ1Cough, cvQ1Breathless, cvQ1Cold, cvQ1SoreThroat, cvQ1Headache, cvQ1None, cvQ2Ans1, cvQ2Ans2, cvQ2Ans3, cvQ2Ans4;
BottomNavigationView bottomNavigationView;
String a = "", b = "", c = "", d = "", e = "", f = "", g = "";
int exp1 = 0, exp2 = 0, exp3 = 0, exp4 = 0;
String finalSymptom = "", time = "", getFever= "", getCough= "", getBreathless= "", getCold= "", getSoreThroat= "", getHeadache= "", getNoSymptom= "";
String finalExposure = "", getStatus="";
String uid = "", getResult = "", getCondition = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_screening);

        btSubmit = findViewById(R.id.btScreeningSubmit);

        //casting button-back
        btBack = findViewById(R.id.btHealthScreenback);
        btBack.setBackground(null);

        cvQ1Fever = findViewById(R.id.cvQ1Fever);
        cvQ1Cough = findViewById(R.id.cvQ1Cough);
        cvQ1Breathless = findViewById(R.id.cvQ1Breathlessness);
        cvQ1Cold = findViewById(R.id.cvQ1Cold);
        cvQ1SoreThroat = findViewById(R.id.cvQ1SoreThroat);
        cvQ1Headache = findViewById(R.id.cvQ1Headache);
        cvQ1None = findViewById(R.id.cvQ1None);
        cvQ2Ans1 = findViewById(R.id.cvQ2A1);
        cvQ2Ans2 = findViewById(R.id.cvQ2A2);
        cvQ2Ans3 = findViewById(R.id.cvQ2A3);
        cvQ2Ans4 = findViewById(R.id.cvQ2A4);

        progressDialog = new ProgressDialog(this);

        cvQ1Fever.setOnClickListener(this);
        cvQ1Cough.setOnClickListener(this);
        cvQ1Breathless.setOnClickListener(this);
        cvQ1Cold.setOnClickListener(this);
        cvQ1SoreThroat.setOnClickListener(this);
        cvQ1Headache.setOnClickListener(this);
        cvQ1None.setOnClickListener(this);
        cvQ2Ans1.setOnClickListener(this);
        cvQ2Ans2.setOnClickListener(this);
        cvQ2Ans3.setOnClickListener(this);
        cvQ2Ans4.setOnClickListener(this);

        //bottom navigation bar
        bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.setSelectedItemId(R.id.empty);
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
                Intent b = new Intent(HealthScreeningActivity.this, HomeActivity.class);
                startActivity(b);
            }
        });

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cvQ1Fever.isChecked() || cvQ1Cough.isChecked() || cvQ1Breathless.isChecked() || cvQ1Cold.isChecked() || cvQ1SoreThroat.isChecked() || cvQ1Headache.isChecked() || cvQ1None.isChecked() || cvQ2Ans1.isChecked() || cvQ2Ans2.isChecked() || cvQ2Ans3.isChecked() || cvQ2Ans4.isChecked()  ){
                    String [] symptom = {a,b,c,d,e,f,g};
                    int n = 0;
                    ArrayList<String> symptomList =  new ArrayList<String>();
//                    for (String s : symptom) {
//                        if (s != "") {
//                            symptomList.add(s);
//                        }
//                    }

                    int [] exposure = {exp1, exp2, exp3, exp4};
                    int getExposureNum = 0;
                    String finalExposureNum = "";
                    for(int i = 0; i < exposure.length; i++){
                        getExposureNum += exposure[i];
                    }
                    finalExposureNum = String.valueOf(getExposureNum);

                    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    String id = UUID.randomUUID().toString();
                    if(getCough.isEmpty()||getCough.trim().isEmpty()||getCough == null){
                        getCough = "0";
                    }
                    if(getFever.isEmpty()||getFever.trim().isEmpty()||getFever == null){
                        getFever = "0";
                    }
                    if(getBreathless.isEmpty()||getBreathless.trim().isEmpty()||getBreathless == null){
                        getBreathless = "0";
                    }
                    if(getCold.isEmpty()||getCold.trim().isEmpty()||getCold == null){
                        getCold = "0";
                    }
                    if(getSoreThroat.isEmpty()||getSoreThroat.trim().isEmpty()||getSoreThroat == null){
                        getSoreThroat = "0";
                    }
                    if(getHeadache.isEmpty()||getHeadache.trim().isEmpty()||getHeadache == null){
                        getHeadache = "0";
                    }
                    if(!cvQ1None.isChecked() && !cvQ2Ans4.isChecked()){
                        getCondition = "w/ symptoms";
                        getResult = "Positive";
                    }else{
                        getCough = "0";
                        getFever = "0";
                        getCold = "0";
                        getBreathless = "0";
                        getHeadache = "0";
                        getSoreThroat = "0";
                        getNoSymptom = "1";
                        getCondition = "w/o symptoms";
                        getResult = "Negative";
                    }
                    SharedPrefManager.getInstance(getApplicationContext()).getHistory(getResult);
                    uid = id;
                    finalExposure = finalExposureNum;
                    time = sdf.format(Calendar.getInstance().getTime());
                    getStatus = "submitted";
                    screening();
                    update();
                    notifyUser();
                    notification();

                } else{
                    new MaterialAlertDialogBuilder(HealthScreeningActivity.this)
                            .setTitle("Warning")
                            .setMessage("Message")
                            .setPositiveButton("Got it", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            })
                            .show();
                }
            }
        });
    }

    private void notifyUser() {
    }

    private void notification() {
    }


    private void update() {
        final String name = SharedPrefManager.getInstance(this).getFullName();
        final String student_id = SharedPrefManager.getInstance(this).getStudentId();
        final String email = SharedPrefManager.getInstance(this).getEmail();
        final String department = SharedPrefManager.getInstance(this).getDepartment();
        final String status = getStatus;
        final String record_number = uid;
        final String submitted_at = time;

        progressDialog.setMessage("Submitting form...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_SCREENINGUPDATE,
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
                params.put("student_id",student_id);
                params.put("name",name);
                params.put("email",email);
                params.put("department",department);
                params.put("status",status);
                params.put("record_number",record_number);
                params.put("submitted_at",submitted_at);
                return params;
            }

        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void screening() {

//        final String symptoms = finalSymptom;
        final String name = SharedPrefManager.getInstance(this).getFullName();
        final String student_id = SharedPrefManager.getInstance(this).getStudentId();
        final String email = SharedPrefManager.getInstance(this).getEmail();
        final String department = SharedPrefManager.getInstance(this).getDepartment();
        final String result = getResult;
        final String condition = getCondition;
        final String cough = getCough;
        final String fever = getFever;
        final String cold = getCold;
        final String breathless = getBreathless;
        final String sorethroat = getSoreThroat;
        final String headache = getHeadache;
        final String no_symptom = getNoSymptom;
        final String submitted_at = time;
        final String exposure = finalExposure;
        final String record_number = uid;

        progressDialog.setMessage("Submitting form...");
        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_SCREENING,
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
                    params.put("student_id",student_id);
                    params.put("name",name);
                    params.put("email",email);
                    params.put("department",department);
                    params.put("fever",fever);
                    params.put("cough",cough);
                    params.put("breathless",breathless);
                    params.put("cold",cold);
                    params.put("sorethroat",sorethroat);
                    params.put("headache",headache);
                    params.put("no_symptoms",no_symptom);
                    params.put("exposure",exposure);
                    params.put("condition",condition);
                    params.put("result",result);
                    params.put("record_number",record_number);
                    params.put("submitted_at",submitted_at);
                    return params;
            }

        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_items, menu);
        return true;
    }

    @Override
    public void onClick(View v) {


        if(v.getId() == R.id.cvQ1Fever){
            cvQ1Fever.setChecked(!cvQ1Fever.isChecked());
            if(cvQ1Fever.isChecked()){
                a = "1";
                getFever = a;
            }
            else if(!cvQ1Fever.isChecked()){
                a = "0";
                getFever = a;
            }
            if(!cvQ1Cough.isChecked() || !cvQ1Breathless.isChecked() || !cvQ1Cold.isChecked() || !cvQ1SoreThroat.isChecked() || !cvQ1Headache.isChecked()){
                b = "0";
                c = "0";
                d = "0";
                e = "0";
                f = "0";
            }
        }
        if(v.getId() == R.id.cvQ1Cough){
            cvQ1Cough.setChecked(!cvQ1Cough.isChecked());
            if(cvQ1Cough.isChecked()){
                b = "1";
                getCough = b;
                if(!cvQ1Fever.isChecked() || !cvQ1Breathless.isChecked() || !cvQ1Cold.isChecked() || !cvQ1SoreThroat.isChecked() || !cvQ1Headache.isChecked()){
                    a = "0";
                    c = "0";
                    d = "0";
                    e = "0";
                    f = "0";
                }
            }
            else if(!cvQ1Cough.isChecked()){
                b = "0";
                getCough = b;
            }

        }
        if(v.getId() == R.id.cvQ1Breathlessness){
            cvQ1Breathless.setChecked(!cvQ1Breathless.isChecked());
            if(cvQ1Breathless.isChecked()){
                c = "1";
                getBreathless = c;
                if(!cvQ1Fever.isChecked() || !cvQ1Cough.isChecked() || !cvQ1Cold.isChecked() || !cvQ1SoreThroat.isChecked() || !cvQ1Headache.isChecked()){
                    a = "0";
                    b = "0";
                    d = "0";
                    e = "0";
                    f = "0";
                }
            }
            else if(!cvQ1Breathless.isChecked()){
                c = "0";
                getBreathless = c;
            }

        }
        if(v.getId() == R.id.cvQ1Cold){
            cvQ1Cold.setChecked(!cvQ1Cold.isChecked());
            if(cvQ1Cold.isChecked()){
                d = "1";
                getCold = d;
                if(!cvQ1Fever.isChecked() || !cvQ1Cough.isChecked() || !cvQ1Breathless.isChecked() || !cvQ1SoreThroat.isChecked() || !cvQ1Headache.isChecked()){
                    a = "0";
                    b = "0";
                    c = "0";
                    e = "0";
                    f = "0";
                }
            }
            else if(!cvQ1Cold.isChecked()){
                d = "0";
                getCold = d;
            }

        }
        if(v.getId() == R.id.cvQ1SoreThroat){
            cvQ1SoreThroat.setChecked(!cvQ1SoreThroat.isChecked());
            if(cvQ1SoreThroat.isChecked()){
                e = "1";
                getSoreThroat = e;
                if(!cvQ1Fever.isChecked() || !cvQ1Cough.isChecked() || !cvQ1Breathless.isChecked() || !cvQ1Cold.isChecked() || !cvQ1Headache.isChecked()){
                    a = "0";
                    b = "0";
                    c = "0";
                    d = "0";
                    f = "0";
                }
            }
            else if(!cvQ1SoreThroat.isChecked()){
                e = "0";
                getSoreThroat = e;
            }


        }
        if(v.getId() == R.id.cvQ1Headache){
            cvQ1Headache.setChecked(!cvQ1Headache.isChecked());
            if(cvQ1Headache.isChecked()){
                f = "1";
                getHeadache = f;
                if(!cvQ1Fever.isChecked() || !cvQ1Cough.isChecked() || !cvQ1Breathless.isChecked() || !cvQ1Cold.isChecked() || !cvQ1SoreThroat.isChecked()){
                    a = "0";
                    b = "0";
                    c = "0";
                    d = "0";
                    e = "0";
                }
            }
            else if(!cvQ1Headache.isChecked()){
                f = "0";
                getHeadache = f;
            }

        }
        if(v.getId() == R.id.cvQ1None){
            cvQ1None.setChecked(!cvQ1None.isChecked());
            if(cvQ1None.isChecked()){
                g = "1";
                a = "0";
                b = "0";
                c = "0";
                d = "0";
                e = "0";
                f = "0";
                getNoSymptom = g;
                cvQ1Fever.setChecked(false);
                cvQ1Cough.setChecked(false);
                cvQ1Breathless.setChecked(false);
                cvQ1Cold.setChecked(false);
                cvQ1SoreThroat.setChecked(false);
                cvQ1Headache.setChecked(false);

                cvQ1Fever.setEnabled(false);
                cvQ1Cough.setEnabled(false);
                cvQ1Breathless.setEnabled(false);
                cvQ1Cold.setEnabled(false);
                cvQ1SoreThroat.setEnabled(false);
                cvQ1Headache.setEnabled(false);
            }
            else if(!cvQ1Headache.isChecked()){
                g = "0";
                a = "0";
                b = "0";
                c = "0";
                d = "0";
                e = "0";
                f = "0";
                getNoSymptom = g;
                cvQ1Fever.setEnabled(true);
                cvQ1Cough.setEnabled(true);
                cvQ1Breathless.setEnabled(true);
                cvQ1Cold.setEnabled(true);
                cvQ1SoreThroat.setEnabled(true);
                cvQ1Headache.setEnabled(true);
            }
        }

        if(v.getId() == R.id.cvQ2A1){
            cvQ2Ans1.setChecked(!cvQ2Ans1.isChecked());
            if(cvQ2Ans1.isChecked()){
                exp1 = 10;
            }
            else if(!cvQ2Ans1.isChecked()){
                exp1 = 0;
            }
        }
        if(v.getId() == R.id.cvQ2A2){
            cvQ2Ans2.setChecked(!cvQ2Ans2.isChecked());
            if(cvQ2Ans2.isChecked()){
                exp2 = 5;
            }
            else if(!cvQ2Ans2.isChecked()){
                exp2 = 0;
            }
        }
        if(v.getId() == R.id.cvQ2A3){
            cvQ2Ans3.setChecked(!cvQ2Ans3.isChecked());
            if(cvQ2Ans3.isChecked()){
                exp3 = 1;
            }
            else if(!cvQ2Ans3.isChecked()){
                exp3 = 0;
            }
        }

        if(v.getId() == R.id.cvQ2A4){
            cvQ2Ans4.setChecked(!cvQ2Ans4.isChecked());
            if(cvQ2Ans4.isChecked()){
                exp4 = 0;
                cvQ2Ans1.setChecked(false);
                cvQ2Ans2.setChecked(false);
                cvQ2Ans3.setChecked(false);

                cvQ2Ans1.setEnabled(false);
                cvQ2Ans2.setEnabled(false);
                cvQ2Ans3.setEnabled(false);

            } else if(!cvQ2Ans4.isChecked()){
                exp4 = 0;
                cvQ2Ans1.setEnabled(true);
                cvQ2Ans2.setEnabled(true);
                cvQ2Ans3.setEnabled(true);
            }
        }

    }
}