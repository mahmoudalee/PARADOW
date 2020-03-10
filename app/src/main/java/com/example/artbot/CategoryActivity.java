package com.example.artbot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.artbot.adapters.CategoriesAdapter;
import com.example.artbot.adapters.HomeCardsAdapter;
import com.example.artbot.model.Categories;
import com.example.artbot.model.HomeCards;
import com.example.artbot.model.MainCategories;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity implements HomeCardsAdapter.ListItemClickListener {

    RecyclerView mRecyclerView;
    TextView tv;
    //    RecyclerView.LayoutManager mLayoutManager;
    GridLayoutManager gridLayoutManager;
    RecyclerView.Adapter mAdapter;
    HomeCards homeCards = new HomeCards();
    ArrayList<Categories> categoryItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);


        String flag = getIntent().getExtras().getString("cat");

        tv = findViewById(R.id.cat);
        tv.setText(flag);

        if(flag.equals("Cartoons")) {
            categoryItems = homeCards.CreateCartoons();
        }
        else if(flag.equals("Graphics"))
            categoryItems = homeCards.CreateGraphics();

        mRecyclerView = findViewById(R.id.rv_category_items);
        mRecyclerView.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(this ,2);
//        mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mAdapter = new HomeCardsAdapter(categoryItems, this , 2);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Toast.makeText(this,"image "+clickedItemIndex+ " Clicked" , Toast.LENGTH_LONG).show();
    }
}
