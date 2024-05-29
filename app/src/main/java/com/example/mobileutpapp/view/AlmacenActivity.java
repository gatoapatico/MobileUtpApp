package com.example.mobileutpapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileutpapp.R;

public class AlmacenActivity extends AppCompatActivity {

    private Button btn_inicio;
    private Button btn_insumos;
    private Button btn_productos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_almacen);

        btn_inicio = findViewById(R.id.btn_inicio);
        btn_insumos = findViewById(R.id.btn_almacen);
        btn_productos = findViewById(R.id.btn_productos);

        btn_inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlmacenActivity.this, WelcomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
