package com.example.artbot.model;

import com.example.artbot.R;

import java.util.ArrayList;

public class HomeCards {


    // this class is responsible for filling the data in home Page
    ArrayList<Categories> popular = new ArrayList<Categories>();
    ArrayList<Categories> recom = new ArrayList<Categories>();
    public ArrayList<Categories> CreateCardsRecommends(){
        popular.add(new CategoriesBuilder().setName("").setRate("4.5").setImage(R.drawable.f).addCategory());
        popular.add(new CategoriesBuilder().setName("").setRate("5").setImage(R.drawable.k).addCategory());
        popular.add(new CategoriesBuilder().setName("").setRate("3").setImage(R.drawable.h).addCategory());
        popular.add(new CategoriesBuilder().setName("").setRate("4").setImage(R.drawable.i).addCategory());
        popular.add(new CategoriesBuilder().setName("").setRate("3.5").setImage(R.drawable.j).addCategory());
        return popular;
    }
    public ArrayList<Categories> CreateCardsPopular(){
        recom.add(new CategoriesBuilder().setName("").setRate("4").setImage(R.drawable.a).addCategory());
        recom.add(new CategoriesBuilder().setName("").setRate("5").setImage(R.drawable.b).addCategory());
        recom.add(new CategoriesBuilder().setName("").setRate("3.5").setImage(R.drawable.c).addCategory());
        recom.add(new CategoriesBuilder().setName("").setRate("4.5").setImage(R.drawable.d).addCategory());
        recom.add(new CategoriesBuilder().setName("").setRate("3").setImage(R.drawable.e).addCategory());
        return recom;
    }

    public ArrayList<Categories> CreateCartoons(){
        recom.add(new CategoriesBuilder().setName("").setRate("4").setImage(R.drawable.imag10).addCategory());
        recom.add(new CategoriesBuilder().setName("").setRate("5").setImage(R.drawable.images).addCategory());
        recom.add(new CategoriesBuilder().setName("").setRate("3.5").setImage(R.drawable.images1).addCategory());
        recom.add(new CategoriesBuilder().setName("").setRate("4.5").setImage(R.drawable.images2).addCategory());
        recom.add(new CategoriesBuilder().setName("").setRate("3").setImage(R.drawable.images4).addCategory());
        recom.add(new CategoriesBuilder().setName("").setRate("4").setImage(R.drawable.images5).addCategory());
        recom.add(new CategoriesBuilder().setName("").setRate("5").setImage(R.drawable.images6).addCategory());
        recom.add(new CategoriesBuilder().setName("").setRate("3.5").setImage(R.drawable.images7).addCategory());
        recom.add(new CategoriesBuilder().setName("").setRate("4.5").setImage(R.drawable.images9).addCategory());
        recom.add(new CategoriesBuilder().setName("").setRate("3").setImage(R.drawable.images11).addCategory());
        recom.add(new CategoriesBuilder().setName("").setRate("4").setImage(R.drawable.images21).addCategory());
        recom.add(new CategoriesBuilder().setName("").setRate("5").setImage(R.drawable.images31).addCategory());
        recom.add(new CategoriesBuilder().setName("").setRate("3.5").setImage(R.drawable.images97).addCategory());
        return recom;
    }
    public ArrayList<Categories> CreateGraphics(){
        recom.add(new CategoriesBuilder().setName("").setRate("4").setImage(R.drawable.b).addCategory());
        recom.add(new CategoriesBuilder().setName("").setRate("5").setImage(R.drawable.c).addCategory());
        recom.add(new CategoriesBuilder().setName("").setRate("3.5").setImage(R.drawable.e).addCategory());
        recom.add(new CategoriesBuilder().setName("").setRate("4.5").setImage(R.drawable.f).addCategory());
        recom.add(new CategoriesBuilder().setName("").setRate("3.5").setImage(R.drawable.images1).addCategory());
        return recom;
    }
    public ArrayList<Categories> CreateFavorates(){
        recom.add(new CategoriesBuilder().setName("").setRate("4").setImage(R.drawable.h).addCategory());
        recom.add(new CategoriesBuilder().setName("").setRate("5").setImage(R.drawable.c).addCategory());
        recom.add(new CategoriesBuilder().setName("").setRate("3.5").setImage(R.drawable.k).addCategory());
        recom.add(new CategoriesBuilder().setName("").setRate("4.5").setImage(R.drawable.images).addCategory());
        recom.add(new CategoriesBuilder().setName("").setRate("5").setImage(R.drawable.images31).addCategory());
        return recom;
    }
}
