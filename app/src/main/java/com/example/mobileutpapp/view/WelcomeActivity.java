package com.example.mobileutpapp.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileutpapp.R;

public class WelcomeActivity extends AppCompatActivity {

    private TextView tvUsername;
    private Button btn_almacen;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_welcome);

        sharedPreferences = getSharedPreferences("UserSession", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("USERNAME", "Guest");

        tvUsername = findViewById(R.id.txtalm_titulo);
        btn_almacen = findViewById(R.id.btn_almacen);

        tvUsername.setText("Hola, " + username + "!");

        btn_almacen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, AlmacenActivity.class);
                startActivity(intent);
            }
        });
    }
}
