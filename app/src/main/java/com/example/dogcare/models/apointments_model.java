package com.example.dogcare.models;

public class apointments_model {
    public String full_name , cotntact_number,
            dog_name,dog_type,dog_symptoms,sched_date,sched_time,currentdate_time ,docId ,status , users_id;

    public apointments_model() {
    }

    public apointments_model(String full_name, String cotntact_number,
                             String dog_name, String dog_type, String dog_symptoms,
                             String sched_date, String sched_time, String currentdate_time,String docId

            ,String status ,String users_id) {
        this.full_name = full_name;
        this.cotntact_number = cotntact_number;
        this.dog_name = dog_name;
        this.dog_type = dog_type;
        this.dog_symptoms = dog_symptoms;
        this.sched_date = sched_date;
        this.sched_time = sched_time;
        this.currentdate_time = currentdate_time;
        this.docId = docId;
        this.status = status;
        this.users_id = users_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getCotntact_number() {
        return cotntact_number;
    }

    public void setCotntact_number(String cotntact_number) {
        this.cotntact_number = cotntact_number;
    }

    public String getDog_name() {
        return dog_name;
    }

    public void setDog_name(String dog_name) {
        this.dog_name = dog_name;
    }

    public String getDog_type() {
        return dog_type;
    }

    public void setDog_type(String dog_type) {
        this.dog_type = dog_type;
    }

    public String getDog_symptoms() {
        return dog_symptoms;
    }

    public void setDog_symptoms(String dog_symptoms) {
        this.dog_symptoms = dog_symptoms;
    }

    public String getSched_date() {
        return sched_date;
    }

    public void setSched_date(String sched_date) {
        this.sched_date = sched_date;
    }

    public String getSched_time() {
        return sched_time;
    }

    public void setSched_time(String sched_time) {
        this.sched_time = sched_time;
    }

    public String getCurrentdate_time() {
        return currentdate_time;
    }

    public void setCurrentdate_time(String currentdate_time) {
        this.currentdate_time = currentdate_time;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsers_id() {
        return users_id;
    }

    public void setUsers_id(String users_id) {
        this.users_id = users_id;
    }
}
