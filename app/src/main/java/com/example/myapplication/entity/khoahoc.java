package com.example.myapplication.entity;


public class khoahoc {

    private String khoahoc;
    private String email;
    private String password;
    private String hocphi;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKhoahoc() {
        return khoahoc;
    }

    public void setKhoahoc(String khoahoc) {
        this.khoahoc = khoahoc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getHocphi() {
        return hocphi;
    }

    public khoahoc(String name, String email, String password, String sdt) {
        this.khoahoc = name;
        this.email = email;
        this.password = password;
        this.hocphi = sdt;
    }

    public void setHocphi(String hocphi) {
        this.hocphi = hocphi;
    }


    public khoahoc(String khoahoc, String email, String password) {
        this.khoahoc = khoahoc;
        this.email = email;
        this.password = password;
    }

    public khoahoc(String khoahoc, String hocphi) {
        this.khoahoc = khoahoc;
        this.hocphi = hocphi;
    }

    public khoahoc() {
    }
}
