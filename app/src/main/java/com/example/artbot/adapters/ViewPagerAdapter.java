package com.example.artbot.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.artbot.R;


public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    String IMAGE_BASE_URL = "http://paradowme.000webhostapp.com/images/";
    String image;
    //this photos that going to be shown in the ViewPager
//    private Integer[] images = {R.drawable.slide1,R.drawable.slide2,R.drawable.slide3};

    public ViewPagerAdapter(Context context,String image) {
        this.context = context;
        this.image = image;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.review_pager_layout, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
//        imageView.setImageResource(images[position]);

        Glide.with(context)
                .load(IMAGE_BASE_URL + image )
                .placeholder(R.drawable.loading)
                .error(R.mipmap.ic_launcher)
                .into(imageView);


        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }
}