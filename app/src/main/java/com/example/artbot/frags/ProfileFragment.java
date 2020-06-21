package com.example.artbot.frags;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.artbot.LoginActivity;
import com.example.artbot.MainActivity;
import com.example.artbot.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    @BindView(R.id.sign_out_btn)
    Button signOut;

    private Unbinder unbinder;

//    SharedPreferences preferences = getSharedPreferences("myPrefs", MODE_PRIVATE);

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        unbinder = ButterKnife.bind(this, view);

        signOut.setOnClickListener(view1 -> {

            SharedPreferences preferences = getContext().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
            preferences.edit().clear().apply();

            Intent intent= new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        });

        return view;
    }

}
