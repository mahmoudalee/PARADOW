package com.example.artbot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.artbot.adapters.CatItemsAdapter;
import com.example.artbot.adapters.FavsAdapter;
import com.example.artbot.model.CategoryRes;
import com.example.artbot.model.Datum;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryActivity extends AppCompatActivity implements CatItemsAdapter.ListItemClickListener {


    @BindView(R.id.rv_category_items)
    RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    CatItemsAdapter mAdapter;

    ArrayList<Datum> categoryItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        categoryItems = bundle.getParcelableArrayList("catItems");

        Log.i("CategoryActivity:", String.valueOf(categoryItems.get(0).getTitle()));

        mRecyclerView.setHasFixedSize(true);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL);

        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);

        mAdapter = new CatItemsAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setData(categoryItems);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        //TODO(7) Open Review for Image
        Toast.makeText(this, "Card number: "+clickedItemIndex+" clicked", Toast.LENGTH_LONG).show();

    }
}
