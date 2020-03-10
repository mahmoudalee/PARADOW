package com.example.artbot.model;

public class Categories {
    private String name;
    private String rate;
    private int image;

    public Categories(String name,String rate, int image){
        this.name = name;
        this.rate = rate;
        this.image = image;
    }

    public String getName() {return name;}
    public int getImage() {return image;}

    public String getRate() {
        return rate;
    }
}
