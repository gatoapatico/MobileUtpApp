package com.example.mobileutpapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileutpapp.R;

public class CartaActivity extends AppCompatActivity {
    private Button btn_back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carta);

        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(CartaActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}
