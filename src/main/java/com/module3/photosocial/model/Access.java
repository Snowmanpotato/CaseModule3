package com.module3.photosocial.model;

public class Access {
    private int admin;
    private String value;

    public Access(int admin, String value) {
        this.admin = admin;
        this.value = value;
    }

    public Access() {
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
