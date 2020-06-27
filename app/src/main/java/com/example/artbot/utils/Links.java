package com.example.artbot.utils;

import android.util.Log;

import com.example.artbot.model.Datum;
import com.example.artbot.network.DataService;
import com.example.artbot.network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Links {
    public static final String IMAGE_BASE_URL = "http://paradowm.000webhostapp.com/images/";
    public static final String BASE_URL = "http://paradowm.000webhostapp.com";


    public static void calImageView(Long id) {
        DataService service = RetrofitInstance.getRetrofitInstance().create(DataService.class);
        Call<Datum> call = service.ImageViewed(id);
        call.enqueue(new Callback<Datum>() {
            @Override
            public void onResponse(Call<Datum> call, Response<Datum> response) {

                Log.i("Response:","Server  response code:"+response.code());

                if (response.code()==400)
                {
                    Log.i("Response:","ImageViewed");
                }
                else if(response.code() == 200){
                    Log.i("CategoryActivity:","ImageViewed");
                }
                else{
                    Log.i("Response:","Server have a problem");
                }
            }

            @Override
            public void onFailure(Call<Datum> call, Throwable t) {
                Log.i("Failure:",t.getMessage());
            }
        });
    }
}
