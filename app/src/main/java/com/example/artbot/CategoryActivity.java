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
        assert bundle != null;
        categoryItems = bundle.getParcelableArrayList("catItems");

        if (categoryItems.size() > 0) {
            Log.i("CategoryActivity:", String.valueOf(categoryItems.get(0).getTitle()));
        }

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
        Log.i("Home ", "categoryItem Clicked ");
        Log.i("Home ", "categoryItem Clicked " +categoryItems.get(clickedItemIndex).getImage());
        Log.i("Home ", "categoryItem Clicked " +categoryItems.get(clickedItemIndex).getTitle());

        Intent i = new Intent(this , ReviewActivity.class);

        if(categoryItems.get(clickedItemIndex) != null){
            i.putExtra("id",categoryItems.get(clickedItemIndex).getId());
            i.putExtra("catName",categoryItems.get(clickedItemIndex).getCategoryName());
            i.putExtra("title",categoryItems.get(clickedItemIndex).getTitle());
            i.putExtra("price",categoryItems.get(clickedItemIndex).getPriceAfterOff());
            i.putExtra("image",categoryItems.get(clickedItemIndex).getImage());
            i.putExtra("n_color",categoryItems.get(clickedItemIndex).getNoOfColor());
            i.putExtra("n_fav",categoryItems.get(clickedItemIndex).getFavProduct());


            startActivity(i);
        }
    }
}
