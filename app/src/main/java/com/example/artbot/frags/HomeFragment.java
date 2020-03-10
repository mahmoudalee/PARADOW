package com.example.artbot.frags;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artbot.adapters.HomeCardsAdapter;
import com.example.artbot.R;
import com.example.artbot.ReviewActivity;
import com.example.artbot.model.Categories;
import com.example.artbot.model.HomeCards;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment
        implements HomeCardsAdapter.ListItemClickListener {

    RecyclerView rvRecommends;
    RecyclerView rvPopulars;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mRecAdapter;
    RecyclerView.Adapter mPopAdapter;
    HomeCards homeCards = new HomeCards();
    ArrayList<Categories> populars;
    ArrayList<Categories> recommends ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        populars = homeCards.CreateCardsPopular();
        recommends = homeCards.CreateCardsRecommends();

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
//
//        // Inflate the layout for this fragment

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        rvPopulars = (RecyclerView) view.findViewById(R.id.rv_popular);
        rvPopulars.setHasFixedSize(true);

        rvRecommends = (RecyclerView) view.findViewById(R.id.rv_recommends);
        rvRecommends.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getContext() , LinearLayoutManager.HORIZONTAL , false);

        rvPopulars.setLayoutManager(mLayoutManager);
        mLayoutManager = new LinearLayoutManager(getContext() , LinearLayoutManager.HORIZONTAL , false);
        rvRecommends.setLayoutManager(mLayoutManager);

        mPopAdapter = new HomeCardsAdapter(populars, this , 1);
        rvPopulars.setAdapter(mPopAdapter);

        mRecAdapter = new HomeCardsAdapter(recommends, this , 1);
        rvRecommends.setAdapter(mRecAdapter);
    }
    @Override
    public void onListItemClick(int itemIndex) {

        // Validation to Check if the user entered any data or not
        // true flag refers to the card that going to be added is not in dashboard so it will be added
//        boolean flag = true;
//        if(!checkForDashboard.isEmpty())
//        {
//            for (int i=0 ; i<checkForDashboard.size() ; i++){
//                if(!checkForDashboard.get(i).getName().equals(courses.get(itemIndex).getName())) {
//                    flag = true;
//                }
//                else {
//                    flag = false;
//                    break;
//                }
//            }
//        }
//        if (flag){
//            DashboardFragment.addToDashboard(courses.get(itemIndex).getName(), courses.get(itemIndex).getImage());
//            checkForDashboard.add((Dashbord) dashbordFactory.getCard(courses.get(itemIndex).getName(), courses.get(itemIndex).getImage()));
//        }
        Intent intent = new Intent(getActivity() , ReviewActivity.class);
        startActivity(intent);
    }
}
