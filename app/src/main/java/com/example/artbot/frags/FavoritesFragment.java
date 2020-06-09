package com.example.artbot.frags;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.artbot.R;
import com.example.artbot.adapters.CategoriesAdapter;
import com.example.artbot.adapters.FavsAdapter;
import com.example.artbot.adapters.HomeCardsAdapter;
import com.example.artbot.model.CategoryRes;
import com.example.artbot.model.FavRes;
import com.example.artbot.model.MostLike;
import com.example.artbot.model.UserFav;
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

public class FavoritesFragment extends Fragment implements FavsAdapter.ListItemClickListener{


    @BindView(R.id.favorites)
    RecyclerView mRecyclerView;

    //TODO(6) some edit if needed to random hight
    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    FavsAdapter mAdapter;

    private Unbinder unbinder;
    private Long userID;

    public FavoritesFragment(Long id) {
        userID = id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_favorites, container, false);


        callFavs();
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
        mRecyclerView.setHasFixedSize(true);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL);

        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);

        mAdapter = new FavsAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

    }



    private void callFavs() {
        DataService service = RetrofitInstance.getRetrofitInstance().create(DataService.class);
        Call<FavRes> call = service.Favs( userID );
        call.enqueue(new Callback<FavRes>() {
            @Override
            public void onResponse(Call<FavRes> call, Response<FavRes> response) {

                if (response.code()==400)
                {
                    Log.i("Response:","Favorites have a problem");
                    Toast.makeText(getContext(), "Favorites can't be loaded", Toast.LENGTH_LONG).show();
                }
                else{
//                    SharedPreferences preferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
//                    preferences.edit().putString("token", response.body().getMessage().getRememberToken()).apply();
                    Log.i("Response Favs:", String.valueOf(response.body().getMessage().getUserFav().get(1).getTitle()));
                    publishFavs(response.body());
                }
            }

            @Override
            public void onFailure(Call<FavRes> call, Throwable t) {
                Log.i("Failure:",t.getMessage());
            }
        });




    }

    private void publishFavs(FavRes body) {
        Log.i("publishFavorites:","Start Publish");
        List<UserFav> list = body.getMessage().getUserFav();
        while (list.remove(null)) {
        }
        mAdapter.setData(list);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

        //TODO(5):on image in Category clicked

    }
}
