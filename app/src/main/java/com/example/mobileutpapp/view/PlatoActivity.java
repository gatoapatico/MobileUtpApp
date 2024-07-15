package com.example.mobileutpapp.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileutpapp.R;
import com.example.mobileutpapp.controller.PlatoController;
import com.example.mobileutpapp.entity.Plato;

import java.util.List;

public class PlatoActivity extends AppCompatActivity {
    private GridLayout gridLayoutUsers;
    private PlatoController platoController;
    private Button btn_back;
    private Button btn_agregar_plato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platos);

        btn_back = findViewById(R.id.btn_back);
        btn_agregar_plato = findViewById(R.id.btn_platos_agregar);
        gridLayoutUsers = findViewById(R.id.gridLayoutUsers);
        platoController = new PlatoController(this);

        List<Plato> platos = platoController.getAllPlatos();

        for (Plato plato : platos) {
            Button platoButton = new Button(this);

            platoButton.setText(plato.getNombre());

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = GridLayout.LayoutParams.WRAP_CONTENT;
            params.height = GridLayout.LayoutParams.WRAP_CONTENT;
            params.setMargins(dpToPx(8), dpToPx(8), dpToPx(8), dpToPx(8));
            platoButton.setLayoutParams(params);

            platoButton.setBackgroundResource(R.drawable.btn_background_1);
            platoButton.setTextColor(Color.WHITE);
            platoButton.setWidth(300);
            platoButton.setHeight(400);

            platoButton.setOnClickListener(v -> {
                Intent intent = new Intent(PlatoActivity.this, PlatosInfoActivity.class);
                intent.putExtra("PLATO_ID", plato.getId());
                startActivity(intent);
            });
            gridLayoutUsers.addView(platoButton);
        }

        btn_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            Intent intent = new Intent(PlatoActivity.this, AlmacenActivity.class);

            startActivity(intent);
             }
        });

        btn_agregar_plato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlatoActivity.this, PlatoAddActivity.class);
                startActivity(intent);
            }
        });
    }

    private int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}
