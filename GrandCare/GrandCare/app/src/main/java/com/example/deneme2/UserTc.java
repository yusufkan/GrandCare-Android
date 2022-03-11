package com.example.deneme2;

public class UserTc {
    private String TC;
    private String doc;

    public String getTC() {
        return TC;
    }

    public void setTC(String TC) {
        this.TC = TC;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public UserTc(String TC, String doc) {
        this.TC = TC;
        this.doc = doc;
    }
}
