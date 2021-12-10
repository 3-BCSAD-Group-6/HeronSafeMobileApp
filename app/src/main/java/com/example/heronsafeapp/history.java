package com.example.heronsafeapp;

public class history {
    private String student_id, submitted_at, result, record_number, condition, cough, fever, breathless, cold, sorethroat, headache, no_symptoms, exposure;

    public history (String student_id, String fever, String cough, String breathless, String cold, String sorethroat, String headache, String no_symptoms, String exposure, String condition, String result, String record_number, String submitted_at){

        this.student_id = student_id;
        this.fever = fever;
        this.cough = cough;
        this.breathless = breathless;
        this.cold = cold;
        this.sorethroat = sorethroat;
        this.headache = headache;
        this.no_symptoms = no_symptoms;
        this.exposure = exposure;
        this.condition = condition;
        this.result = result;
        this.record_number = record_number;
        this.submitted_at = submitted_at;


    }

    public String getExposure() {
        return exposure;
    }

    public void setExposure(String exposure) {
        this.exposure = exposure;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getDate() {
        return submitted_at;
    }

    public void setDate(String submitted_at) {
        this.submitted_at = submitted_at;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getRecord_number() {
        return record_number;
    }

    public void setRecord_number(String record_number) {
        this.record_number = record_number;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCough() {
        return cough;
    }

    public void setCough(String cough) {
        this.cough = cough;
    }

    public String getFever() {
        return fever;
    }

    public void setFever(String fever) {
        this.fever = fever;
    }

    public String getBreathless() {
        return breathless;
    }

    public void setBreathless(String breathless) {
        this.breathless = breathless;
    }

    public String getCold() {
        return cold;
    }

    public void setCold(String cold) {
        this.cold = cold;
    }

    public String getSorethroat() {
        return sorethroat;
    }

    public void setSorethroat(String sorethroat) {
        this.sorethroat = sorethroat;
    }

    public String getHeadache() {
        return headache;
    }

    public void setHeadache(String headache) {
        this.headache = headache;
    }

    public String getNo_symptoms() {
        return no_symptoms;
    }

    public void setNo_symptoms(String no_symptoms) {
        this.no_symptoms = no_symptoms;
    }
}