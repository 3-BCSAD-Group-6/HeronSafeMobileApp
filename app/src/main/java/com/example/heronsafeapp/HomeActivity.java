package com.example.heronsafeapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
BottomNavigationView bottomNavigationView;
CardView cvHealthScreening, cvExposure, cvAnnouncement, cvHealthAdvice;
TextView tvFullname, tvDashboardStatus, tvGreeting;
String getRes ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //bottom navigation bar
        bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.setSelectedItemId(R.id.menuHome);
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

        //casting card views
        cvHealthScreening = findViewById(R.id.cvHealthScreening);
        cvExposure = findViewById(R.id.cvExposure);
        cvAnnouncement = findViewById(R.id.cvAnnouncement);
        cvHealthAdvice = findViewById(R.id.cvHealthAdvice);

        //Click Listener for Card Views
        cvHealthScreening.setOnClickListener(this);
        cvExposure.setOnClickListener(this);
        cvAnnouncement.setOnClickListener(this);
        cvHealthAdvice.setOnClickListener(this);

        tvFullname = findViewById(R.id.tvDashboardUsername);
        tvDashboardStatus = findViewById(R.id.tvDashboardStatus);
        tvGreeting = findViewById(R.id.tvDashboardGreeting);

        tvFullname.setText(SharedPrefManager.getInstance(this).getFullName());
        getCondition();
//        String result = SharedPrefManager.getInstance(this).getResult();


        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if(timeOfDay >= 0 && timeOfDay < 12){
            tvGreeting.setText("Good Morning,");
        }else if(timeOfDay >= 12 && timeOfDay < 16){
            tvGreeting.setText("Good Afternoon,");
        }else if(timeOfDay >= 16 && timeOfDay < 21){
            tvGreeting.setText("Good Evening,");
        }else if(timeOfDay >= 21 && timeOfDay < 24){
            tvGreeting.setText("Good Night,");
        }
    }

    private void getCondition() {
        final String getEmail = SharedPrefManager.getInstance(this).getEmail();
        final String[] res = new String[1];
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_GETCONDITION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            JSONArray object = new JSONArray(response);
                            res[0] =  object.getString(Integer.parseInt("result"));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        getRes = res[0];
                        String getResult = getRes;
                        String neg = "Negative";
                        String pos = "Positive";
                        if (neg.equals(getRes)){
                            tvDashboardStatus.setText("You are in bad condition");
//                tvDashboardStatus.setBackgroundColor(this.getResources().getColor(R.color.red));
                            tvDashboardStatus.setBackgroundResource(R.color.red);
                        }else if(pos.equals(getRes)) {
                            tvDashboardStatus.setText("You are in good condition");
//          tvDashboardStatus.setBackgroundColor(this.getResources().getColor(R.color.green));
                            tvDashboardStatus.setBackgroundResource(R.color.green);
                        }else{
                            tvDashboardStatus.setText("Take Screening Test");
//               tvDashboardStatus.setBackgroundColor(this.getResources().getColor(R.color.gray));
                            tvDashboardStatus.setBackgroundResource(R.color.gray);
//                            tvDashboardStatus.setTextColor(this.getResources().getColor(R.color.black));

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(HomeActivity.this, error.toString(), Toast.LENGTH_LONG).show();

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String , String> params = new HashMap<>();
                params.put("email", getEmail);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);


    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch(v.getId()){
            case R.id.cvHealthScreening:
                i = new Intent(this, HealthScreeningActivity.class);
                startActivity(i);
                break;
            case R.id.cvExposure:
                i = new Intent(this, ExposureActivity.class);
                startActivity(i);
                break;
            case R.id.cvAnnouncement:
                i = new Intent(this, AnnouncementActivity.class);
                startActivity(i);
                break;
            case R.id.cvHealthAdvice:
                i = new Intent(this, HealthAdviceActivity.class);
                startActivity(i);
                break;
            default: break;
        }

    }


}