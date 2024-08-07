package com.example.mobileutpapp.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.mobileutpapp.R;
import com.google.android.material.navigation.NavigationView;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private Button btnCarta;
    private DrawerLayout drawerLayout;
    private AppCompatImageButton btn_menu;
    private Button btn_pedidos;
    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);

        btnCarta = findViewById(R.id.btnCarta);
        btn_menu = findViewById(R.id.bottomNavigationView);
        btn_pedidos = findViewById(R.id.btnPedidos);
        btnCarta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCarta = new Intent(MenuActivity.this, CartaActivity.class);
                startActivity(intentCarta);
            }
        });
        btn_pedidos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentPedidos = new Intent(MenuActivity.this, VerPedidos.class);
                startActivity(intentPedidos);
            }
        });

        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_inicio:
                Intent intentInicio = new Intent(MenuActivity.this, WelcomeActivity.class);
                startActivity(intentInicio);
                break;
            case R.id.nav_menu:
                Toast.makeText(this, "Ya estás en la pantalla de Menu", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_ingInsumos:
                Intent intentAddSuply = new Intent(MenuActivity.this, AddSupplyActivity.class);
                startActivity(intentAddSuply);
                break;
            case R.id.nav_almacen:
                Intent intentAlmacen = new Intent(MenuActivity.this, AlmacenActivity.class);
                startActivity(intentAlmacen);
                break;
            case R.id.nav_reporte:
                Intent intentReporte = new Intent(MenuActivity.this, ReporteActivity.class);
                startActivity(intentReporte);
                break;
            case R.id.nav_logout:
                Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show();
                Intent logout = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(logout);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}