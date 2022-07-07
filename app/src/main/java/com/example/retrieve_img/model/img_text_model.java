package com.example.retrieve_img.model;

import android.widget.ImageView;

public  class img_text_model {

    private String text;
    private String img_URL;

    public img_text_model(){

    }

    public img_text_model(String text, String img_URL) {
        this.text = text;
        this.img_URL = img_URL;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImg_URL() {
        return img_URL;
    }

    public void setImg_URL(String img_URL) {
        this.img_URL = img_URL;
    }
}
