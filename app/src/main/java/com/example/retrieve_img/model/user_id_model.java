package com.example.retrieve_img.model;

public class user_id_model {
    private String name;
    private String location;
    private String available;
    private String language;
    private int pts;
    private String img_url;

   public  user_id_model(){

    }

    public user_id_model(String name, String location, String available, String language, int pts , String img_url) {
        this.name = name;
        this.location = location;
        this.available = available;
        this.language = language;
        this.pts = pts;
        this.img_url = img_url;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getPts() {
        return pts;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }
}
