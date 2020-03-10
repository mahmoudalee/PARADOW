package com.example.artbot.frags;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artbot.CategoryActivity;
import com.example.artbot.adapters.CategoriesAdapter;
import com.example.artbot.R;
import com.example.artbot.model.Categories;
import com.example.artbot.model.MainCategories;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends Fragment
        implements CategoriesAdapter.ListItemClickListener{


    RecyclerView mRecyclerView;
    //    RecyclerView.LayoutManager mLayoutManager;
    GridLayoutManager gridLayoutManager;
    RecyclerView.Adapter mAdapter;
    MainCategories mainCategories = new MainCategories();
    ArrayList<Categories> categories;

    public DiscoverFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        categories = mainCategories.CreateCategories();

        return inflater.inflate(R.layout.fragment_discover, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_categories);
        mRecyclerView.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(getContext() ,2);
//        mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mAdapter = new CategoriesAdapter(categories, this);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onListItemClick(int itemIndex) {
        Intent intent = new Intent(getActivity() , CategoryActivity.class);
        if (itemIndex == 3) {
            intent.putExtra("cat", "Cartoons");
        }
        else if (itemIndex == 0)
            intent.putExtra("cat", "Graphics");

        startActivity(intent);
//        Toast.makeText(getContext(),"Done item "+itemIndex+" clicked",Toast.LENGTH_LONG).show();
    }


}
