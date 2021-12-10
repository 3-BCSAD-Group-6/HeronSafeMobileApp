package com.example.heronsafeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExposureActivity extends AppCompatActivity {
    Button btBack;
    BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerView;
    private List<history> historys;
    private ProgressBar progressBar;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exposure);

        //casting button-back
        btBack = findViewById(R.id.btExposureBack);
        btBack.setBackground(null);

        //bottom navigation bar
        bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.setSelectedItemId(R.id.empty);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menuHome:
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.menuNotification:
                    startActivity(new Intent(getApplicationContext(), NotificationsActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.menuContacts:
                    startActivity(new Intent(getApplicationContext(), ContactsActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.menuProfile:
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(ExposureActivity.this, HomeActivity.class);
                startActivity(b);
            }
        });


        //
        progressBar = findViewById(R.id.progressbar);
        recyclerView = findViewById(R.id.history_recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(ExposureActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        historys = new ArrayList<>();

        getHistory();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items, menu);
        return true;
    }

    public void getHistory() {
        final String studentID = SharedPrefManager.getInstance(this).getStudentId();
        progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_HISTORY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressBar.setVisibility(View.GONE);

                        try {

                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {

                                JSONObject object = array.getJSONObject(i);

                                String student_id = object.getString("student_id");
                                String fever = object.getString("fever");
                                String cough = object.getString("cough");
                                String breathless = object.getString("breathless");
                                String cold = object.getString("cold");
                                String sorethroat = object.getString("sorethroat");
                                String headache = object.getString("headache");
                                String no_symptoms = object.getString("no_symptoms");
                                String exposure = object.getString("exposure");
                                String condition = object.getString("condition");
                                String result = object.getString("result");
                                String record_number = object.getString("record_number");
                                String submitted_at = object.getString("submitted_at");

                                history histories = new history(student_id, fever, cough, breathless, cold, sorethroat, headache, no_symptoms, exposure, condition, result, record_number, submitted_at);
                                historys.add(histories);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        mAdapter = new historyRecycleAdpter(ExposureActivity.this,historys);
                        recyclerView.setAdapter(mAdapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressBar.setVisibility(View.GONE);
                Toast.makeText(ExposureActivity.this, error.toString(), Toast.LENGTH_LONG).show();

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String , String> params = new HashMap<>();
                params.put("student_id", studentID);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
}