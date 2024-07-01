package com.example.mobileutpapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mobileutpapp.R;

public class gestionPedidos extends AppCompatActivity {

    Button Crearpedidos, Verpedidos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gestion_pedidos);

        //Al hacer clic nos llevará a crear pedidos
        Crearpedidos =(Button)findViewById(R.id.btn_crearPedidos);
        Crearpedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( gestionPedidos.this, CrearPedido.class);
                startActivity(intent);
            }
        });
        //fin codigo de crear de pedidos

        //Al hacer clic nos llevará a ver pedidos
        Verpedidos =(Button)findViewById(R.id.btn_verPedidos);
        Verpedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( gestionPedidos.this, VerPedidos.class);
                startActivity(intent);
            }
        });
        //fin codigo de ver pedidos




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}