package com.example.mobileutpapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.mobileutpapp.R;

public class AlmacenFragment extends Fragment {

    private Button btn_inicio;
    private Button btn_productos;
    //private Button btn_insumos;

    @Override
    public  View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_almacen, container, false);

        btn_inicio = view.findViewById(R.id.btn_inicio);
//      btn_insumos = findViewById(R.id.btn_almacen);
        btn_productos = view.findViewById(R.id.btn_productos);

        btn_inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WelcomeActivity.class);
                startActivity(intent);
            }
        });

        btn_productos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PlatosActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
