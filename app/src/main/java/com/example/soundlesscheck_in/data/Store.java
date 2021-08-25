package com.example.soundlesscheck_in.data;

public class Store {
    private String licenseNumber;
    private String tradeName;
    private Visitor visitor;

    public Store(String licenseNumber, Visitor visitor) {
        this.licenseNumber = licenseNumber;
        this.visitor = visitor;
    }

    public Store(String licenseNumber, String tradeName, Visitor visitor) {
        this.licenseNumber = licenseNumber;
        this.tradeName = tradeName;
        this.visitor = visitor;
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

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }
}
