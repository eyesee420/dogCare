package com.example.dogcare.models;

public class blogs_post_model {
    public String imageuri , text_tittle, text_discription ,calendar, docId;

    public blogs_post_model() {
    }

    public blogs_post_model(String imageuri, String text_tittle, String text_discription, String calendar, String docId) {
        this.imageuri = imageuri;
        this.text_tittle = text_tittle;
        this.text_discription = text_discription;
        this.calendar = calendar;
        this.docId = docId;
    }

    public String getImageuri() {
        return imageuri;
    }

    public void setImageuri(String imageuri) {
        this.imageuri = imageuri;
    }

    public String getText_tittle() {
        return text_tittle;
    }

    public void setText_tittle(String text_tittle) {
        this.text_tittle = text_tittle;
    }

    public String getText_discription() {
        return text_discription;
    }

    public void setText_discription(String text_discription) {
        this.text_discription = text_discription;
    }

    public String getCalendar() {
        return calendar;
    }

    public void setCalendar(String calendar) {
        this.calendar = calendar;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }
}
