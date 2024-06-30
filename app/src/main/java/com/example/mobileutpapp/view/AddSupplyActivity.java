package com.example.mobileutpapp.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.mobileutpapp.R;
import com.google.android.material.navigation.NavigationView;

public class AddSupplyActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    private DrawerLayout drawerLayout;
    private AppCompatImageButton btn_menu;
    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_supply);
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
                Intent intentInicio = new Intent(AddSupplyActivity.this, WelcomeActivity.class);
                startActivity(intentInicio);
                break;
            case R.id.nav_menu:
                Intent intentMenu = new Intent(AddSupplyActivity.this, MenuActivity.class);
                startActivity(intentMenu);
                break;
            case R.id.nav_ingInsumos:
                Toast.makeText(this, "Ya estás en la pantalla de Ingreso de Insumos", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_detInsumos:
                Intent intentDetalleIng = new Intent(AddSupplyActivity.this, DetalleIngresoInsumosActivity.class);
                startActivity(intentDetalleIng);
                break;
            case R.id.nav_almacen:
                Intent intentAlmacen = new Intent(AddSupplyActivity.this, AlmacenActivity.class);
                startActivity(intentAlmacen);
                break;
            case R.id.nav_reporte:
                Intent intentReporte = new Intent(AddSupplyActivity.this, ReporteActivity.class);
                startActivity(intentReporte);
                break;
            case R.id.nav_logout:
                Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show();
                Intent logout = new Intent(AddSupplyActivity.this, MainActivity.class);
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
