package com.example.artbot.frags;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
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
import com.example.artbot.R;
import com.example.artbot.adapters.CategoriesAdapter;
import com.example.artbot.adapters.HomeCardsAdapter;
import com.example.artbot.model.CategoryRes;
import com.example.artbot.model.Datum;
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
public class DiscoverFragment extends Fragment
        implements CategoriesAdapter.ListItemClickListener{

    @BindView(R.id.rv_categories)
    RecyclerView mRecyclerView;

    GridLayoutManager gridLayoutManager;

    CategoriesAdapter mAdapter;

    private Unbinder unbinder;
    List<CategoryRes.Message> categories;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_discover, container, false);

        callCategories();
        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(getContext() ,2);

        mRecyclerView.setLayoutManager(gridLayoutManager);

        mAdapter = new CategoriesAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }


    private void callCategories() {
        DataService service = RetrofitInstance.getRetrofitInstance().create(DataService.class);
        Call<CategoryRes> call = service.Categories();
        call.enqueue(new Callback<CategoryRes>() {
            @Override
            public void onResponse(Call<CategoryRes> call, Response<CategoryRes> response) {

                if (response.code()==400)
                {
                    Log.i("Response:","Categories have a problem");
                    Toast.makeText(getContext(), "Categories can't be loaded", Toast.LENGTH_LONG).show();
                }
                else{
//                    SharedPreferences preferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
//                    preferences.edit().putString("token", response.body().getMessage().getRememberToken()).apply();
                    categories = response.body().getMessage();
                    Log.i("Response:",categories.get(0).getName());
                    publishCategories(response.body());
                }
            }

            @Override
            public void onFailure(Call<CategoryRes> call, Throwable t) {
                Log.i("Failure:",t.getMessage());
            }
        });

    }

    private void publishCategories(CategoryRes body) {
        Log.i("publishCategories:","Start Publish");
        mAdapter.setData(body.getMessage());
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        //dTODO(5):open the category
        Intent intent = new Intent(getContext(), CategoryActivity.class);

        ArrayList<Datum> catItems = (ArrayList<Datum>) categories.get(clickedItemIndex).getData();
        Bundle bundle = new Bundle();

        bundle.putParcelableArrayList("catItems", catItems);
        intent.putExtras(bundle);

        startActivity(intent);

    }
}
