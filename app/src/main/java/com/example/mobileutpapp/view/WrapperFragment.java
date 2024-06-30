package com.example.mobileutpapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class WrapperFragment extends Fragment {
    private static final String ARG_ACTIVITY_CLASS = "activity_class";
    public static WrapperFragment newInstance(Class<? extends AppCompatActivity> activityClass) {
        WrapperFragment fragment = new WrapperFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_ACTIVITY_CLASS, activityClass);
        fragment.setArguments(args);
        return fragment;
    }


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (getArguments() != null) {
            Class<?> activityClass = (Class<?>) getArguments().getSerializable(ARG_ACTIVITY_CLASS);
            if (activityClass != null) {
                Intent intent = new Intent(getActivity(), activityClass);
                startActivity(intent);
            }
        }
        return null;
    }

}
