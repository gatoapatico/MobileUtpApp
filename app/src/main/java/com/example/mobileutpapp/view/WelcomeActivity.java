package com.example.mobileutpapp.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.mobileutpapp.R;
import com.google.android.material.navigation.NavigationView;

public class WelcomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView tvUsername;
    private Button btn_almacen;
    private SharedPreferences sharedPreferences;
    private DrawerLayout drawerLayout;
    private AppCompatImageButton btn_menu;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_welcome);

        sharedPreferences = getSharedPreferences("UserSession", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("USERNAME", "Guest");

        tvUsername = findViewById(R.id.txtalm_titulo);
        btn_almacen = findViewById(R.id.btn_almacen);

        tvUsername.setText("Hola, " + username + "!");
        btn_menu = findViewById(R.id.bottomNavigationView);

        btn_almacen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, AlmacenActivity.class);
                startActivity(intent);
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
                Toast.makeText(this, "Ya estás en la pantalla de inicio", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_menu:
                Intent intentMenu = new Intent(WelcomeActivity.this, MenuActivity.class);
                startActivity(intentMenu);
                break;
            case R.id.nav_ingInsumos:
                Intent intentAddSuply = new Intent(WelcomeActivity.this, AddSupplyActivity.class);
                startActivity(intentAddSuply);
                break;
            case R.id.nav_detInsumos:
                Intent intentDetalleIng = new Intent(WelcomeActivity.this, DetalleIngresoInsumosActivity.class);
                startActivity(intentDetalleIng);
                break;
            case R.id.nav_almacen:
                Intent intentAlmacen = new Intent(WelcomeActivity.this, AlmacenActivity.class);
                startActivity(intentAlmacen);
                break;
            case R.id.nav_reporte:
                Intent intentReporte = new Intent(WelcomeActivity.this, ReporteActivity.class);
                startActivity(intentReporte);
                break;
            case R.id.nav_logout:
                Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show();
                Intent logout = new Intent(WelcomeActivity.this, MainActivity.class);
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
