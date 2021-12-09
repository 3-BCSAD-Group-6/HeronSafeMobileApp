package com.example.heronsafeapp;

public class announcement {
    private String subject, message, created_by, created_at;

    public announcement (String subject, String message, String created_by, String created_at){
        this.subject = subject;
        this.message = message;
        this.created_by = created_by;
        this.created_at = created_at;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
