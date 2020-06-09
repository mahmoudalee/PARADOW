package com.example.artbot.frags;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artbot.R;
import com.example.artbot.ReviewActivity;
import com.example.artbot.adapters.HomeCardsAdapter;
import com.example.artbot.model.LoginRes;
import com.example.artbot.model.MostLike;
import com.example.artbot.network.DataService;
import com.example.artbot.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment
        implements HomeCardsAdapter.ListItemClickListener {
    @BindView(R.id.rv_recommends)
    RecyclerView rvRecommends;

    @BindView(R.id.rv_popular)
    RecyclerView rvPopulars;

    @BindView(R.id.progress_bar_rec)
    ProgressBar recProgressBar;

    @BindView(R.id.progress_bar_pop)
    ProgressBar popProgressBar;

    RecyclerView.LayoutManager mLayoutManager;

    HomeCardsAdapter mRecAdapter;
    HomeCardsAdapter mPopAdapter;

    private Unbinder unbinder;
    List<MostLike.Message> homeCards;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        callRecommended();

        callMostLike();
        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        rvPopulars.setHasFixedSize(true);

        rvRecommends.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getContext() , LinearLayoutManager.HORIZONTAL , false);
        rvPopulars.setLayoutManager(mLayoutManager);

        mLayoutManager = new LinearLayoutManager(getContext() , LinearLayoutManager.HORIZONTAL , false);
        rvRecommends.setLayoutManager(mLayoutManager);

        mRecAdapter = new HomeCardsAdapter( this , 1);
        rvRecommends.setAdapter(mRecAdapter);

        mPopAdapter = new HomeCardsAdapter( this , 1);
        rvPopulars.setAdapter(mPopAdapter);
    }

    private void callRecommended() {
        DataService service = RetrofitInstance.getRetrofitInstance().create(DataService.class);

        Call<MostLike> call = service.Recommendations();
        call.enqueue(new Callback<MostLike>() {
            @Override
            public void onResponse(Call<MostLike> call, Response<MostLike> response) {

                if (response.code()==400)
                {
                    Log.i("Response:","Recommendations have a problem");
                    Toast.makeText(getContext(), "Recommendations can't be loaded", Toast.LENGTH_LONG).show();
                }
                else{
//                    SharedPreferences preferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
//                    preferences.edit().putString("token", response.body().getMessage().getRememberToken()).apply();
                    Log.i("Response:",response.body().getMessage().get(0).getDescription());
                    publishRecommend(response.body());
                }
            }

            @Override
            public void onFailure(Call<MostLike> call, Throwable t) {
                Log.i("Failure:",t.getMessage());
            }
        });
    }

    private void publishRecommend(MostLike body) {
        Log.i("publishRecommend:","Start Publish");
        mRecAdapter.setData(body.getMessage());
        rvRecommends.setVisibility(View.VISIBLE);
        recProgressBar.setVisibility(View.GONE);
    }

    private void callMostLike(){
        DataService service = RetrofitInstance.getRetrofitInstance().create(DataService.class);
        Call<MostLike> call = service.mostliked();
        call.enqueue(new Callback<MostLike>() {
            @Override
            public void onResponse(Call<MostLike> call, Response<MostLike> response) {

                if (response.code()==400)
                {
                    Log.i("Response:","MostLike have a problem");
                    Toast.makeText(getContext(), "MostLike can't be loaded", Toast.LENGTH_LONG).show();
                }
                else{
//                    SharedPreferences preferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
//                    preferences.edit().putString("token", response.body().getMessage().getRememberToken()).apply();
                    Log.i("Response:",response.body().getMessage().get(0).getDescription());
                    homeCards = response.body().getMessage();
                    publishMostLike(response.body());
                }
            }

            @Override
            public void onFailure(Call<MostLike> call, Throwable t) {
                Log.i("Failure:",t.getMessage());
            }
        });

    }

    private void publishMostLike(MostLike body) {
        Log.i("publishMostLike:","Start Publish");
        mPopAdapter.setData(body.getMessage());
        rvPopulars.setVisibility(View.VISIBLE);
        popProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        //dTODO(4):open the image
        Intent i = new Intent(getContext() , ReviewActivity.class);

        i.putExtra("id",homeCards.get(clickedItemIndex).getId());
        i.putExtra("catName",homeCards.get(clickedItemIndex).getCategoryName());
        i.putExtra("title",homeCards.get(clickedItemIndex).getTitle());
        i.putExtra("price",homeCards.get(clickedItemIndex).getPriceAfterOff());
        i.putExtra("image",homeCards.get(clickedItemIndex).getImage());
        i.putExtra("n_color",homeCards.get(clickedItemIndex).getNoOfColor());
        i.putExtra("n_fav",homeCards.get(clickedItemIndex).getFavProduct());


        startActivity(i);

    }
}
