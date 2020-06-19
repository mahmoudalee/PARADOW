package com.example.artbot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.artbot.frags.DiscoverFragment;
import com.example.artbot.frags.HomeFragment;
import com.example.artbot.frags.FavoritesFragment;
import com.example.artbot.frags.ProfileFragment;
import com.example.artbot.model.UserData;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sdsmdg.harjot.vectormaster.models.PathModel;

public class MainActivity extends AppCompatActivity {

    CurvedBottomNavigationView bottomNavigationView;

    Intent callerIntent;
    public static String token;
    SharedPreferences sharedPreferences;
//    UserData user;
    Long userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_nav);

        sharedPreferences=getSharedPreferences("myPrefs",MODE_PRIVATE);
        token=sharedPreferences.getString("token",null);

        userID=sharedPreferences.getLong("userID",-1);

//        callerIntent= this.getIntent();
//        user =  callerIntent.getParcelableExtra("user");




        bottomNavigationView.inflateMenu(R.menu.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container , new HomeFragment() ).commit();

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;
            switch (menuItem.getItemId()){
                case R.id.nav_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.nav_discover:
                    selectedFragment = new DiscoverFragment();
                    break;
                case R.id.nav_favorites:
                    selectedFragment = new FavoritesFragment(userID);
                    break;
                case R.id.nav_profile:
                    selectedFragment = new ProfileFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container , selectedFragment ).commit();
            return true;
        }
    };

}
