package com.example.deneme2;

public class User {
    private String name,lastName,City,Birthday,userName, password, tcNo, mail, telNo,saglik;

    public User(String name, String lastName, String city, String birthday, String userName, String password, String tcNo, String mail, String telNo, String saglik) {
        this.name = name;
        this.lastName = lastName;
        City = city;
        Birthday = birthday;
        this.userName = userName;
        this.password = password;
        this.tcNo = tcNo;
        this.mail = mail;
        this.telNo = telNo;
        this.saglik = saglik;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSaglik() {
        return saglik;
    }

    public void setSaglik(String saglik) {
        this.saglik = saglik;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTcNo() {
        return tcNo;
    }

    public void setTcNo(String tcNo) {
        this.tcNo = tcNo;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }
}
