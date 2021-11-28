package com.example.heronsafeapp;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPrefManager {
    private static SharedPrefManager instance;
    private static Context ctx;

    private static final String SHARED_PREF_NAME = "mysharedpref12";
    private static final String KEY_ID = "id";
    private static final String KEY_FULLNAME = "name";
    private static final String KEY_STUDENTID = "student_id";
    private static final String KEY_CONTACTNUMBER = "contact_number";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_ROLE = "role";
    private static final String KEY_CREATED = "created_at";

    private SharedPrefManager(Context context) {
        ctx = context;

    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPrefManager(context);
        }
        return instance;
    }

    public boolean userLogin(int id, String name, String student_id,String contact_number, String email, String role, String created_at){

        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_ID, id);
        editor.putString(KEY_FULLNAME, name);
        editor.putString(KEY_STUDENTID, student_id);
        editor.putString(KEY_CONTACTNUMBER, contact_number);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_ROLE, role);
        editor.putString(KEY_CREATED, created_at);

        editor.apply();

        return true;
    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if (sharedPreferences.getString(KEY_EMAIL, null) != null){
            return true;
        }else {
            return false;
        }
    }

    public boolean logout(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;

    }
    public String getEmail(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_EMAIL, null);


    }

    public String getFullName(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_FULLNAME, null);


    }

    public String getContact(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_CONTACTNUMBER, null);

    }

    public String getStudentId(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_STUDENTID, null);

    }

    public String getRole(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_ROLE, null);

    }


}
