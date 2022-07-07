package com.example.retrieve_img.model;

public class sign_updetails {

    private String mail;
    private String nation;
    private String name;

    public sign_updetails(String mail, String nation, String name) {
        this.mail = mail;
        this.nation = nation;
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
