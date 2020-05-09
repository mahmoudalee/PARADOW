package com.example.artbot.network;

import com.example.artbot.model.DnSignUp;
import com.example.artbot.model.LoginRes;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DataService {

//    @POST("/auth/register/")
//    Call<User> signUp(@Body User user);

    @GET("/api/login")
    Call<LoginRes> login(@Query("email") String login, @Query("password") String password);

    @GET("/api/signup")
    Call<DnSignUp> signUp(@Query("name")String name, @Query("email") String login, @Query("password") String password, @Query("repassword") String repassword);

//    @GET("/en_US/api/user/get_token")
//    Call<LoginRes> login(@Query("login") String login, @Query("password") String password);

//    @GET("/disasters/")
//    Call<List<Disease>> getPossibleDisease(@Header("token") String token, @Query("lat") double lat, @Query("lang") double lang);

}