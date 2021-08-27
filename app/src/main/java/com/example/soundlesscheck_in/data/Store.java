package com.example.soundlesscheck_in.data;

public class Store {
    private String licenseNumber;
    private String tradeName;
    private String city;
    private String town;

    public Store(String licenseNumber, String tradeName, String city, String town) {
        this.licenseNumber = licenseNumber;
        this.tradeName = tradeName;
        this.city = city;
        this.town = town;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
