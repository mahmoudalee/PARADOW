package com.example.artbot.frags;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.artbot.R;
import com.example.artbot.adapters.HomeCardsAdapter;
import com.example.artbot.model.Categories;
import com.example.artbot.model.HomeCards;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment implements HomeCardsAdapter.ListItemClickListener {

    private RecyclerView rv;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    RecyclerView.Adapter mAdapter;
    ArrayList<Categories> favoriteItems;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_favorites, container, false);
//
        favoriteItems = new HomeCards().CreateFavorates();

//        // Inflate the layout for this fragment


       return rootView;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        rv =view.findViewById(R.id.favorites);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL);
        rv.setLayoutManager(staggeredGridLayoutManager);
        mAdapter = new HomeCardsAdapter( favoriteItems , this,3);
        rv.setAdapter(mAdapter);

    }
    @Override
    public void onListItemClick(int clickedItemIndex) {
        Toast.makeText(getContext() ,"image "+clickedItemIndex+ " Clicked" , Toast.LENGTH_LONG).show();
    }






}
