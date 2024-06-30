package com.example.mobileutpapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileutpapp.R;

public class MenuActivity extends AppCompatActivity {
    private Button btnCarta;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);

        btnCarta = findViewById(R.id.btnCarta);

        btnCarta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, CartaFragment.class);
                startActivity(intent);
            }
        });
    }
}