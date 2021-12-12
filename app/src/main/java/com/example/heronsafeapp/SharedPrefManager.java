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
    private static final String KEY_DEPARTMENT = "department";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_VACCINED = "vaccined";
    private static final String KEY_VACCINETYPE = "vaccine_type";
    private static final String KEY_FIRSTDOSE = "first_dose_at";
    private static final String KEY_SECONDDOSE = "second_dose_at";
    private static final String KEY_RESULT = "result";

    private SharedPrefManager(Context context) {
        ctx = context;

    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPrefManager(context);
        }
        return instance;
    }

    public boolean userLogin(int id, String name, String student_id,String contact_number, String email, String role, String created_at, String department, String gender, String vaccined, String vaccine_type, String first_dose_at, String second_dose_at){

        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_ID, id);
        editor.putString(KEY_FULLNAME, name);
        editor.putString(KEY_STUDENTID, student_id);
        editor.putString(KEY_CONTACTNUMBER, contact_number);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_ROLE, role);
        editor.putString(KEY_CREATED, created_at);
        editor.putString(KEY_DEPARTMENT, department);
        editor.putString(KEY_GENDER, gender);
        editor.putString(KEY_VACCINED, vaccined);
        editor.putString(KEY_VACCINETYPE, vaccine_type);
        editor.putString(KEY_FIRSTDOSE, first_dose_at);
        editor.putString(KEY_SECONDDOSE, second_dose_at);


        editor.apply();

        return true;
    }

    public boolean getHistory(String result){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_RESULT, result);

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

    public String getDepartment(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_DEPARTMENT, null);
    }

    public String getGender(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_GENDER, null);
    }
    public String getVaccined(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_VACCINED, null);
    }
    public String getVaccineType(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_VACCINETYPE, null);
    }
    public String getFirstDose(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_FIRSTDOSE, null);
    }
    public String getSecondDose(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_SECONDDOSE, null);
    }

    public String getResult(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_RESULT, null);
    }


}
