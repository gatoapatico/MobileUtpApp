package com.example.mobileutpapp.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.mobileutpapp.R;
import com.google.android.material.navigation.NavigationView;

public class WelcomeActivity extends AppCompatActivity {

    private TextView tvUsername;
    private Button btn_almacen;
    private SharedPreferences sharedPreferences;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_welcome);

        sharedPreferences = getSharedPreferences("UserSession", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("USERNAME", "Guest");

        tvUsername = findViewById(R.id.txtalm_titulo);
        btn_almacen = findViewById(R.id.btn_almacen);

        tvUsername.setText("Hola, " + username + "!");

       /* Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);

        ActionBarDrawerToggle toggle =  new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);*/

        btn_almacen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, AlmacenActivity.class);
                startActivity(intent);
            }
        });
    }
}
