package com.example.mobileutpapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.mobileutpapp.R;
import com.google.android.material.navigation.NavigationView;

public class ReporteActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private AppCompatImageButton btn_menu;
    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reportes);
        btn_menu = findViewById(R.id.bottomNavigationView);
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
                Intent intentInicio = new Intent(ReporteActivity.this, WelcomeActivity.class);
                startActivity(intentInicio);
                break;
            case R.id.nav_menu:
                Intent intentMenu = new Intent(ReporteActivity.this, MenuActivity.class);
                startActivity(intentMenu);
                break;
            case R.id.nav_ingInsumos:
                Intent intentAddSuply = new Intent(ReporteActivity.this, AddSupplyActivity.class);
                startActivity(intentAddSuply);
                break;
            case R.id.nav_detInsumos:
                Intent intentDetalleIng = new Intent(ReporteActivity.this, DetalleIngresoInsumosActivity.class);
                startActivity(intentDetalleIng);
                break;
            case R.id.nav_almacen:
                Intent intentAlmacen = new Intent(ReporteActivity.this, AlmacenActivity.class);
                startActivity(intentAlmacen);
                break;
            case R.id.nav_reporte:
                Toast.makeText(this, "Ya estás en la pantalla de Reportes", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_logout:
                Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show();
                Intent logout = new Intent(ReporteActivity.this, MainActivity.class);
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
