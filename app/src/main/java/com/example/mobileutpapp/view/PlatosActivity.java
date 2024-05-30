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
import com.example.mobileutpapp.controller.UserController;
import com.example.mobileutpapp.entity.Plato;
import com.example.mobileutpapp.entity.User;

import java.util.List;

public class PlatosActivity extends AppCompatActivity {
    private GridLayout gridLayoutUsers;
    private PlatoController platoController;
    private Button btn_almacen;
    private Button btn_agregar_plato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platos);

        btn_almacen = findViewById(R.id.btn_platos_almacen);
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
                Intent intent = new Intent(PlatosActivity.this, PlatosInfoActivity.class);
                intent.putExtra("PLATO_ID", plato.getId());
                startActivity(intent);
            });
            gridLayoutUsers.addView(platoButton);
        }

        btn_almacen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlatosActivity.this, AlmacenActivity.class);
                startActivity(intent);
            }
        });

        btn_agregar_plato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlatosActivity.this, PlatoAddActivity.class);
                startActivity(intent);
            }
        });
    }

    private int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}
