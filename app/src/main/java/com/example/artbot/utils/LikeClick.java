package com.example.artbot.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.artbot.model.LikeRes;
import com.example.artbot.network.DataService;
import com.example.artbot.network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LikeClick {

    public void like(final Context context, Long id, String token) {
        DataService service = RetrofitInstance.getRetrofitInstance().create(DataService.class);

        boolean flag = false;
        Call<LikeRes> call = service.Like(id,token);
        call.enqueue(new Callback<LikeRes>() {
            @Override
            public void onResponse(Call<LikeRes> call, Response<LikeRes> response) {

                Log.i("Response:", "Like done");
                Toast.makeText(context, "Liked", Toast.LENGTH_LONG).show();
                Log.i("Response:", response.body().getMessage());
                if (response.body().getMessage().equals("love successfully"))
                {

                }

            }

            @Override
            public void onFailure(Call<LikeRes> call, Throwable t) {

            }

        });
    }

    public static void dislike(Long id, String token) {

    }
}
