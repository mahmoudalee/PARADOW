package com.example.artbot.model;

import com.example.artbot.R;

import java.util.ArrayList;

public class MainCategories {

    // this class is responsible for filling the data in home Page
    ArrayList<Categories> categories = new ArrayList<Categories>();
    public ArrayList<Categories> CreateCategories(){
        categories.add(new CategoriesBuilder().setName("Graphics").setImage(R.drawable.graphic).addCategory());
        categories.add(new CategoriesBuilder().setName("Animals").setImage(R.drawable.animal).addCategory());
        categories.add(new CategoriesBuilder().setName("Nature").setImage(R.drawable.natural).addCategory());
        categories.add(new CategoriesBuilder().setName("Cartoons").setImage(R.drawable.cartoon).addCategory());
        categories.add(new CategoriesBuilder().setName("Architectures").setImage(R.drawable.arch).addCategory());
        categories.add(new CategoriesBuilder().setName("Sculptures").setImage(R.drawable.sclupture).addCategory());
        categories.add(new CategoriesBuilder().setName("Sports").setImage(R.drawable.sports).addCategory());
        categories.add(new CategoriesBuilder().setName("Quotes").setImage(R.drawable.quote).addCategory());
        return categories;
    }
}
