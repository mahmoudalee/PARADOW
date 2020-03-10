package com.example.artbot;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreen extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        EasySplashScreen easySplashScreen = new EasySplashScreen(SplashScreen.this).withFullScreen()
                .withTargetActivity(LoginActivity.class).
//                        withFullScreen().
                        withSplashTimeOut(1500).
//                        withBackgroundResource(R.drawable.splash).
                        withBackgroundColor(Color.parseColor("#F5F5F5")).
                        withLogo(R.drawable.logo).
                        withBeforeLogoText("PARADOW");
//                        withFooterText("Developed by \n  Stone Team");

        easySplashScreen.getBeforeLogoTextView().setTextColor(Color.BLACK);
//        easySplashScreen.getFooterTextView().setTextColor(Color.WHITE);
//        easySplashScreen.getFooterTextView().setPaddingRelative(0,0,0,50);

        View view = easySplashScreen.create();

        setContentView(view);

    }
}
