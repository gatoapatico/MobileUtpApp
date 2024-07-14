package com.example.mobileutpapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileutpapp.R;
import com.example.mobileutpapp.entity.Plato;
import com.example.mobileutpapp.view.adapter.PlatoAdapter;

import java.util.List;

public class CartaActivity extends AppCompatActivity {
    private Button btnBack;
    private RecyclerView recyclerViewPlatos;
    private PlatoAdapter platoAdapter;
    private PlatoViewModel platoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carta);

        btnBack = findViewById(R.id.btn_back);
        recyclerViewPlatos = findViewById(R.id.recyclerViewPlatos);

        recyclerViewPlatos.setLayoutManager(new LinearLayoutManager(this));
        platoAdapter = new PlatoAdapter(this);
        recyclerViewPlatos.setAdapter(platoAdapter);

        platoViewModel = new ViewModelProvider(this).get(PlatoViewModel.class);
        platoViewModel.getAllPlatos().observe(this, new Observer<List<Plato>>() {
            @Override
            public void onChanged(List<Plato> platos) {
                platoAdapter.setPlatoList(platos);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartaActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}
