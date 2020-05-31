package com.example.artbot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.artbot.adapters.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewActivity extends AppCompatActivity {

    Long id;
    String catName,title,price,image,nColor,nFavs;

    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;

    @BindView(R.id.image_name_txt)
    TextView mimageName;
    @BindView(R.id.review_cat_name_txt)
    TextView mcatName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        id = intent.getLongExtra("id",0);
        catName = intent.getStringExtra("catName");
        title = intent.getStringExtra("title");
        price =intent.getStringExtra("price");
        image = intent.getStringExtra("image");
        nColor = intent.getStringExtra("n_color");
        nFavs = intent.getStringExtra("n_fav");

        Toast.makeText(this, "ReviewActivity "+id+":id , "+title+":title", Toast.LENGTH_LONG).show();
        makeViewPager();

        mimageName.setText(title);
        mcatName.setText(catName);


    }

    private void makeViewPager() {
        viewPager = findViewById(R.id.viewPager);
        sliderDotspanel = findViewById(R.id.SliderDots);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this,image);

        viewPager.setAdapter(viewPagerAdapter);

        //viewPagerAdapter.getCount() returns the number of images that add in ViewPagerAdapter class
        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        // used to create the dots that indicate which photo shown in home
        for(int i = 0; i < dotscount; i++){
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.active_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 4);

            sliderDotspanel.addView(dots[i], params);
        }
        //set the default active to the first dot
        dots[0].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.non_active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                //used to change the position of the active dot by set all of them non-active
                //  then set the active one useing the position
                for(int i = 0; i< dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(ReviewActivity.this, R.drawable.non_active_dot));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(ReviewActivity.this, R.drawable.active_dot));
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void choose(View view) {
        Toast.makeText(this, "ReviewActivity: nice Choice", Toast.LENGTH_LONG).show();
    }
}
