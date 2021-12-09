package com.example.heronsafeapp;

import androidx.appcompat.app.AppCompatActivity;
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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AnnouncementActivity extends AppCompatActivity {
Button btBack;
BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerView;
    private List<announcement> announcements;
    private ProgressBar progressBar;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);

        //casting button-back
        btBack = findViewById(R.id.btAnnouncementBack);
        btBack.setBackground(null);

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
                Intent b = new Intent(AnnouncementActivity.this, HomeActivity.class);
                startActivity(b);
            }
        });

        //
        progressBar = findViewById(R.id.progressbar);
        recyclerView = findViewById(R.id.announcement_recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(AnnouncementActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        announcements = new ArrayList<>();

        getAnnouncement();
    }

    private void getAnnouncement() {
        progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Config.URL_ANNOUNCEMENT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressBar.setVisibility(View.GONE);

                        try {

                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {

                                JSONObject object = array.getJSONObject(i);
                                String subject = object.getString("subject");
                                String message = object.getString("message");
                                String created_by = object.getString("created_by");
                                String created_at = object.getString("created_at");

                                announcement announcementss = new announcement(subject, message, created_by, created_at);
                                announcements.add(announcementss);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        mAdapter = new announcementRecycleAdapter(AnnouncementActivity.this,announcements);
                        // historyRecycleAdpter adapter = new historyRecycleAdpter(ExposureActivity.this,historys);
                        recyclerView.setAdapter(mAdapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressBar.setVisibility(View.GONE);
                Toast.makeText(AnnouncementActivity.this, error.toString(), Toast.LENGTH_LONG).show();

            }
        });

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

}