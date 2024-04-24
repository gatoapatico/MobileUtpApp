package com.example.mobileutpapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private final int LAYOUT_ID = R.layout.activity_main;   //NOMBRE DEL ARCHIVO XML CREADO EN LA CAPA LAYOUT
    private final int VIEW_ID = R.id.main;  //NOMBRE DEL ID DEL LAYOUT CREADO. POR DEFECTO VIENE VACIO, SE DEBE SETEAR.

    /*@Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(LAYOUT_ID);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(VIEW_ID), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }*/

    //Permite pasar de un layout a otro
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button boton = findViewById(R.id.button);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cambiar al otro layout
                setContentView(R.layout.activity_screen_welcome);
            }
        });
    }
}