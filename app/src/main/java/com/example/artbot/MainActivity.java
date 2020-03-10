package com.example.artbot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.artbot.frags.DiscoverFragment;
import com.example.artbot.frags.HomeFragment;
import com.example.artbot.frags.FavoritesFragment;
import com.example.artbot.frags.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sdsmdg.harjot.vectormaster.models.PathModel;

public class MainActivity extends AppCompatActivity {

    CurvedBottomNavigationView bottomNavigationView;
    //    VectorMasterView fab1,fab2,fab3,fab4,fab5;
//    RelativeLayout lin_id;
    PathModel outLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_nav);
//        fab1 = findViewById(R.id.fab1);
//        fab2 = findViewById(R.id.fab2);
//        fab3 = findViewById(R.id.fab3);
//        fab4 = findViewById(R.id.fab4);
//        fab5 = findViewById(R.id.fab5);


//        lin_id = findViewById(R.id.lin_id);

        bottomNavigationView.inflateMenu(R.menu.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container , new HomeFragment() ).commit();
//        bottomNavigationView.setOnNavigationItemSelectedListener(this);

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
                    selectedFragment = new FavoritesFragment();
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

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//        switch (item.getItemId())
//        {
//            case R.id.nav_home:
//                draw(9);
//                lin_id.setX(bottomNavigationView.mFirstCurveControlPoint1.x);
//                fab1.setVisibility(View.VISIBLE);
//                fab2.setVisibility(View.GONE);
//                fab3.setVisibility(View.GONE);
//                fab4.setVisibility(View.GONE);
//                fab5.setVisibility(View.GONE);
//                drowAnimation(fab1);
//                break;
//
//            case R.id.nav_discover:
//                draw(6);
//                lin_id.setX(bottomNavigationView.mFirstCurveControlPoint1.x);
//                fab1.setVisibility(View.GONE);
//                fab2.setVisibility(View.VISIBLE);
//                fab5.setVisibility(View.GONE);
//                fab3.setVisibility(View.GONE);
//                fab4.setVisibility(View.GONE);
//                drowAnimation(fab2);
//                break;
//
//            case R.id.nav_go:
//                draw(6);
//                lin_id.setX(bottomNavigationView.mFirstCurveControlPoint1.x);
//                fab1.setVisibility(View.GONE);
//                fab2.setVisibility(View.GONE);
//                fab5.setVisibility(View.VISIBLE);
//                fab3.setVisibility(View.GONE);
//                fab4.setVisibility(View.GONE);
//                drowAnimation(fab5);
//                break;
//            case R.id.nav_favorites:
//                draw(3);
//                lin_id.setX(bottomNavigationView.mFirstCurveControlPoint1.x);
//                fab1.setVisibility(View.GONE);
//                fab2.setVisibility(View.GONE);
//                fab5.setVisibility(View.GONE);
//                fab3.setVisibility(View.VISIBLE);
//                fab4.setVisibility(View.GONE);
//                drowAnimation(fab3);
//                break;
//
//            case R.id.nav_profile:
//                draw();
//                lin_id.setX(bottomNavigationView.mFirstCurveControlPoint1.x);
//                fab1.setVisibility(View.GONE);
//                fab2.setVisibility(View.GONE);
//                fab5.setVisibility(View.GONE);
//                fab3.setVisibility(View.GONE);
//                fab4.setVisibility(View.VISIBLE);
//                drowAnimation(fab4);
//                break;
//
//        }
//
//        return false;
//    }
//
//    private void draw() {
//        bottomNavigationView.mFirstCurveStartPoint.set((bottomNavigationView.mNavigationBarWidth *10/11) - (bottomNavigationView.CURVE_CIRCLE_RADIUS*2) - (bottomNavigationView.CURVE_CIRCLE_RADIUS/3),0);
//        bottomNavigationView.mFirstCurveEndPoint.set((bottomNavigationView.mNavigationBarWidth *10/11 ),bottomNavigationView.CURVE_CIRCLE_RADIUS + (bottomNavigationView.CURVE_CIRCLE_RADIUS/4));
//
//        bottomNavigationView.mSecondCurveStartPoint = bottomNavigationView.mFirstCurveEndPoint;
//        bottomNavigationView.mSecondCurveEndPoint.set((bottomNavigationView.mNavigationBarWidth *10/11)+(bottomNavigationView.CURVE_CIRCLE_RADIUS*2)+(bottomNavigationView.CURVE_CIRCLE_RADIUS/3),0);
//
//        bottomNavigationView.mFirstCurveControlPoint1.set(bottomNavigationView.mFirstCurveStartPoint.x + bottomNavigationView.CURVE_CIRCLE_RADIUS + (bottomNavigationView.CURVE_CIRCLE_RADIUS/4), bottomNavigationView.mFirstCurveStartPoint.y);
//        bottomNavigationView.mFirstCurveControlPoint2.set(bottomNavigationView.mFirstCurveEndPoint.x - (bottomNavigationView.CURVE_CIRCLE_RADIUS *2 ) + bottomNavigationView.CURVE_CIRCLE_RADIUS , bottomNavigationView.mFirstCurveEndPoint.y);
//
//        bottomNavigationView.mSecondCurveControlPoint1.set(bottomNavigationView.mSecondCurveStartPoint.x + (bottomNavigationView.CURVE_CIRCLE_RADIUS*2)-bottomNavigationView.CURVE_CIRCLE_RADIUS, bottomNavigationView.mSecondCurveStartPoint.y);
//        bottomNavigationView.mSecondCurveControlPoint2.set(bottomNavigationView.mSecondCurveEndPoint.x - (bottomNavigationView.CURVE_CIRCLE_RADIUS + (bottomNavigationView.CURVE_CIRCLE_RADIUS/4)), bottomNavigationView.mSecondCurveEndPoint.y);
//
//    }
//
//    private void drowAnimation(final VectorMasterView fab) {
//        outLine = fab.getPathModelByName("outline");
//        outLine.setStrokeColor(Color.WHITE);
//        outLine.setTrimPathEnd(0.0f);
//
//        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0.0f,1.0f);
//        valueAnimator.setDuration(1000);
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                outLine.setTrimPathEnd((Float)valueAnimator.getAnimatedValue());
//                fab.update();
//            }
//        });
//        valueAnimator.start();
//    }
//
//    private void draw(int i) {
//        bottomNavigationView.mFirstCurveStartPoint.set((bottomNavigationView.mNavigationBarWidth/i)-(bottomNavigationView.CURVE_CIRCLE_RADIUS*2)-(bottomNavigationView.CURVE_CIRCLE_RADIUS/3),0);
//        bottomNavigationView.mFirstCurveEndPoint.set((bottomNavigationView.mNavigationBarWidth/i),bottomNavigationView.CURVE_CIRCLE_RADIUS + (bottomNavigationView.CURVE_CIRCLE_RADIUS/4));
//
//        bottomNavigationView.mSecondCurveStartPoint = bottomNavigationView.mFirstCurveEndPoint;
//        bottomNavigationView.mSecondCurveEndPoint.set((bottomNavigationView.mNavigationBarWidth/i)+(bottomNavigationView.CURVE_CIRCLE_RADIUS*2)+(bottomNavigationView.CURVE_CIRCLE_RADIUS/3),0);
//
//        bottomNavigationView.mFirstCurveControlPoint1.set(bottomNavigationView.mFirstCurveStartPoint.x + bottomNavigationView.CURVE_CIRCLE_RADIUS + (bottomNavigationView.CURVE_CIRCLE_RADIUS/4), bottomNavigationView.mFirstCurveStartPoint.y);
//        bottomNavigationView.mFirstCurveControlPoint2.set(bottomNavigationView.mFirstCurveEndPoint.x - (bottomNavigationView.CURVE_CIRCLE_RADIUS *2 ) + bottomNavigationView.CURVE_CIRCLE_RADIUS , bottomNavigationView.mFirstCurveEndPoint.y);
//
//        bottomNavigationView.mSecondCurveControlPoint1.set(bottomNavigationView.mSecondCurveStartPoint.x + (bottomNavigationView.CURVE_CIRCLE_RADIUS*2)-bottomNavigationView.CURVE_CIRCLE_RADIUS, bottomNavigationView.mSecondCurveStartPoint.y);
//        bottomNavigationView.mSecondCurveControlPoint2.set(bottomNavigationView.mSecondCurveEndPoint.x - (bottomNavigationView.CURVE_CIRCLE_RADIUS + (bottomNavigationView.CURVE_CIRCLE_RADIUS/4)), bottomNavigationView.mSecondCurveEndPoint.y);
//    }
//}
