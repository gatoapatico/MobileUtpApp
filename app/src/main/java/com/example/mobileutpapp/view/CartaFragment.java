package com.example.mobileutpapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;


import com.example.mobileutpapp.R;

public class CartaFragment extends Fragment {
    private Button btn_back;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.carta, container, false);
    }

        public void onViewCreated(View view,  Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            btn_back = view.findViewById(R.id.btn_back);

            btn_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(requireActivity(), MenuActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
