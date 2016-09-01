package com.challenge.zap.zappropertieslist.data.model;

/**
 * Created by eliete on 9/1/16.
 */
public class ZapMessage {

    public String name;
    public String email;
    public String phone;
    public int codeAds;

    public ZapMessage(String name, String email, String phone, int codeAds) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.codeAds = codeAds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCodeAds() {
        return codeAds;
    }

    public void setCodeAds(int codeAds) {
        this.codeAds = codeAds;
    }
}
