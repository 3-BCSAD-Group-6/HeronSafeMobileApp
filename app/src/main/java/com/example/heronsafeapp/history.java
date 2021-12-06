package com.example.heronsafeapp;

public class history {
    private String student_id, date, result, record_number;

    public history (String student_id, String date, String result, String record_number){

        this.student_id = student_id;
        this.date = date;
        this.result = result;
        this. record_number = record_number;

    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getRecord_id() {
        return result;
    }

    public void setRecord_id(String result) {
        this.record_number = record_number;
    }

}