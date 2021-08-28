package com.example.soundlesscheck_in.data;

public class Visitor {
    private String phoneNumber;
    private String residence;
    private String place; // place of visit
    private String date; // visit date

    public Visitor(String phoneNumber, String residence, String place, String date) {
        this.phoneNumber = phoneNumber;
        this.residence = residence;
        this.place = place;
        this.date = date;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
