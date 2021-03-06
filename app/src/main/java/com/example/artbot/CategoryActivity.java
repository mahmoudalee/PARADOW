package com.example.artbot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.artbot.adapters.CatItemsAdapter;
import com.example.artbot.databinding.ActivityCategoryBinding;
import com.example.artbot.model.Datum;
import com.example.artbot.network.DataService;
import com.example.artbot.network.RetrofitInstance;
import com.example.artbot.utils.Links;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity implements CatItemsAdapter.ListItemClickListener {



    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    CatItemsAdapter mAdapter;

    ArrayList<Datum> categoryItems;

    ActivityCategoryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        categoryItems = bundle.getParcelableArrayList("catItems");

        if (categoryItems.size() > 0) {
            Log.i("CategoryActivity:", String.valueOf(categoryItems.get(0).getTitle()));
        }

        binding.rvCategoryItems.setHasFixedSize(true);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL);

        binding.rvCategoryItems.setLayoutManager(staggeredGridLayoutManager);

        mAdapter = new CatItemsAdapter(this);
        binding.rvCategoryItems.setAdapter(mAdapter);

        mAdapter.setData(categoryItems);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        //TODO(7) Open Review for Image
        Log.i("Home ", "categoryItem Clicked ");
        Log.i("Home ", "categoryItem Clicked " +categoryItems.get(clickedItemIndex).getImage());
        Log.i("Home ", "categoryItem Clicked " +categoryItems.get(clickedItemIndex).getImageOnWall());

        Intent i = new Intent(this , ReviewActivity.class);

        if(categoryItems.get(clickedItemIndex) != null){
            i.putExtra("id",categoryItems.get(clickedItemIndex).getId());
            i.putExtra("catName",categoryItems.get(clickedItemIndex).getCategoryName());
            i.putExtra("title",categoryItems.get(clickedItemIndex).getTitle());
            i.putExtra("price",categoryItems.get(clickedItemIndex).getPriceAfterOff());
            i.putExtra("image",categoryItems.get(clickedItemIndex).getImage());
            i.putExtra("image_on_wall",categoryItems.get(clickedItemIndex).getImageOnWall());
            i.putExtra("n_color",categoryItems.get(clickedItemIndex).getNoOfColor());
            i.putExtra("n_fav",categoryItems.get(clickedItemIndex).getFavProduct());
            //Just to add as a view
            Links.calImageView(categoryItems.get(clickedItemIndex).getId());

            startActivity(i);
        }
    }


}
