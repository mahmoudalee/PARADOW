package com.example.artbot.model;

public class CategoriesBuilder {

    private String name;
    private String rate;
    private int image;

    public CategoriesBuilder setName(String name) {
        this.name = name;
        return this;
    }
    public CategoriesBuilder setImage(int image){
        this.image = image;
        return this;
    }

    public CategoriesBuilder setRate(String rate) {
        this.rate = rate;
        return this;
    }

    //Build a Rooms Contents and return it
    public Categories addCategory(){
        return new Categories(name, rate, image);
    }

}
