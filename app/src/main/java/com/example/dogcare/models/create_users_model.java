package com.example.dogcare.models;

public class create_users_model {
    public String fullname , phonenumber ,currentaddress,emailaddress,password ,users_id;

    public create_users_model() {
    }

    public create_users_model(String fullname, String phonenumber,
                              String currentaddress, String emailaddress, String password
                             ,String users_id) {
        this.fullname = fullname;
        this.phonenumber = phonenumber;
        this.currentaddress = currentaddress;
        this.emailaddress = emailaddress;
        this.password = password;
        this.users_id = users_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getCurrentaddress() {
        return currentaddress;
    }

    public void setCurrentaddress(String currentaddress) {
        this.currentaddress = currentaddress;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsers_id() {
        return users_id;
    }

    public void setUsers_id(String users_id) {
        this.users_id = users_id;
    }
}
