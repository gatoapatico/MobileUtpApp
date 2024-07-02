package com.example.mobileutpapp.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.mobileutpapp.R;
import com.example.mobileutpapp.controller.SupplyController;
import com.example.mobileutpapp.entity.Supply;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class AddSupplyActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    private DrawerLayout drawerLayout;
    private AppCompatImageButton btn_menu;
    private SupplyController supplyController;

    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_supply);
        btn_menu = findViewById(R.id.bottomNavigationView);
        supplyController = new SupplyController(this);
        loadSavedSupplies();
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        EditText costoInsumo = findViewById(R.id.costoInsumo);
        Button aumentarCosto = findViewById(R.id.aumentarCosto);
        Button disminuirCosto = findViewById(R.id.disminuirCosto);
        EditText pesoInsumo = findViewById(R.id.pesoInsumo);
        Button aumentarPeso = findViewById(R.id.aumentarPeso);
        Button disminuirPeso = findViewById(R.id.disminuirPeso);
        ImageButton borrar = findViewById(R.id.borrar);
        Button btnAgregar = findViewById(R.id.btn_agregar);
        EditText  editTextInsumo  = findViewById(R.id.insumo);
        TextWatcher textWatcher = new TextWatcher() {
            String currentCosto = "";
            String currentPeso = "";
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s == costoInsumo.getEditableText()) {
                    if (!s.toString().equals(currentCosto)) {
                        costoInsumo.removeTextChangedListener(this);
                        String cleanString = s.toString().replaceAll("[S/.,]", "").replaceAll("\\s", "");
                        try {
                            if (!cleanString.isEmpty()) {
                                double parsed = Double.parseDouble(cleanString) / 100;
                                String formatted = String.format("S/. %.2f", parsed);
                                currentCosto = formatted;
                                costoInsumo.setText(formatted);
                                costoInsumo.setSelection(formatted.length());
                            } else {
                                currentCosto = "S/. ";
                                costoInsumo.setText(currentCosto);
                                costoInsumo.setSelection(currentCosto.length());
                            }
                        } catch (NumberFormatException e) {
                            currentCosto = "S/. ";
                            costoInsumo.setText(currentCosto);
                            costoInsumo.setSelection(currentCosto.length());
                        }
                        costoInsumo.addTextChangedListener(this);
                    }
                }else if (s == pesoInsumo.getEditableText()){
                // Campo pesoInsumo
                if (!s.toString().equals(currentPeso)) {
                    pesoInsumo.removeTextChangedListener(this);
                    String cleanString = s.toString().replaceAll("[Kg]", "").replaceAll("\\s", "");
                    try {
                        if (!cleanString.isEmpty()) {
                            int parsed = Integer.parseInt(cleanString);
                            String formatted = String.format("%d Kg", parsed);
                            currentPeso = formatted;
                            pesoInsumo.setText(formatted);
                            pesoInsumo.setSelection(formatted.length());
                        } else {
                            currentPeso = "0 Kg";  // Valor inicial según necesidad
                            pesoInsumo.setText(currentPeso);
                            pesoInsumo.setSelection(currentPeso.length());
                        }
                    } catch (NumberFormatException e) {
                        currentPeso = "0 Kg";  // Manejo de error si no se puede parsear
                        pesoInsumo.setText(currentPeso);
                        pesoInsumo.setSelection(currentPeso.length());
                    }
                    pesoInsumo.addTextChangedListener(this);
                }
                }
            }
        };

        costoInsumo.addTextChangedListener(textWatcher);
        pesoInsumo.addTextChangedListener(textWatcher);

        aumentarCosto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = costoInsumo.getText().toString().replace("S/. ", "").replace(",", "");
                try {
                    double value = Double.parseDouble(text);
                    value += 0.01; // Aumenta por decimas
                    costoInsumo.setText(String.format("S/. %.2f", value));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });

        disminuirCosto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = costoInsumo.getText().toString().replace("S/. ", "").replace(",", "");
                try {
                    double value = Double.parseDouble(text);
                    value -= 0.01; // Disminuye por decimas
                    if (value < 0) value = 0; // Evita valores negativos
                    costoInsumo.setText(String.format("S/. %.2f", value));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });

        aumentarPeso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = pesoInsumo.getText().toString().replace("Kg", "").replaceAll("\\s", "");
                try {
                    int value = Integer.parseInt(text);
                    value += 1; // Aumenta por unidades
                    pesoInsumo.setText(String.format("%d Kg", value));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });

        disminuirPeso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = pesoInsumo.getText().toString().replace("Kg", "").replaceAll("\\s", "");
                try {
                    int value = Integer.parseInt(text);
                    value -= 1; // Disminuye por unidades
                    if (value < 0) value = 0; // Evita valores negativos
                    pesoInsumo.setText(String.format("%d Kg", value));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextInsumo .setText(""); // Limpia el texto del AutoCompleteTextView
            }
        });

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String insumo = editTextInsumo.getText().toString().trim();
                String costo = costoInsumo.getText().toString().trim();
                String peso = pesoInsumo.getText().toString().trim();
                costo = costo.replace("S/.", "").trim();
                peso = peso.replace("Kg", "").trim();

                // Validar que insumo no esté vacío y contenga solo texto
                if (!insumo.isEmpty() && insumo.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                    // Validar que costo y peso no estén vacíos
                    if (!costo.isEmpty() && !peso.isEmpty()) {
                        try {
                            double valueCosto = Double.parseDouble(costo);
                            double valuePeso = Double.parseDouble(peso);

                            // Validar que costo y peso sean mayores a 0
                            if (valueCosto > 0 && valuePeso > 0) {
                                String textoInsumo = insumo + "                   S/." + costo + "                   " + peso +"Kg";
                                //Agrega los datos de los insumos a la BD
                                Supply newSupply = supplyController.addSupply(insumo, valueCosto,valuePeso);
                                // Crear un nuevo LinearLayout para contener cada item de insumo
                                createInsumoLayout(textoInsumo,newSupply);
                            } else {
                                Toast.makeText(getApplicationContext(), "El costo y el peso deben ser mayores a 0", Toast.LENGTH_SHORT).show();
                            }
                        } catch (NumberFormatException e) {
                            Toast.makeText(getApplicationContext(), "Ingrese valores numéricos válidos para costo y peso", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Costo y Peso son obligatorios", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Insumo inválido", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadSavedSupplies() {
        List<Supply> supplies = supplyController.getAllSupply();
        for (Supply supply : supplies) {
            String textoInsumo = supply.getInsumo() + "                   S/." + supply.getPrecio() + "                   " + supply.getPeso() + "Kg";
            createInsumoLayout(textoInsumo, supply);
        }
    }

    private void createInsumoLayout(String textoInsumo,  Supply supply) {
        LinearLayout contenedorInsumos = findViewById(R.id.contenedorInsumos);
        // Crear un nuevo LinearLayout para contener cada item de insumo
        LinearLayout itemLayout = new LinearLayout(getApplicationContext());
        itemLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        itemLayout.setOrientation(LinearLayout.HORIZONTAL);

        // Crear un nuevo TextView
        TextView nuevoInsumo = new TextView(getApplicationContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1.0f
        );
        nuevoInsumo.setText(textoInsumo); // Aquí puedes establecer el texto deseado
        nuevoInsumo.setBackgroundColor(Color.parseColor("#C1C1C1")); // Fondo gris
        nuevoInsumo.setPadding(16, 8, 16, 8); // Ajusta el padding según sea necesario
        itemLayout.addView(nuevoInsumo); // Agrega el nuevo TextView al contenedor

        // Crear un nuevo botón para eliminar el insumo
        Button btnEliminar = createDeleteButton(supply,itemLayout);
        itemLayout.addView(btnEliminar); // Agregar el botón de eliminar al LinearLayout de item

        // Agregar el LinearLayout de item al contenedor principal
        contenedorInsumos.addView(itemLayout);
    }

    private Button createDeleteButton(Supply supply,LinearLayout itemLayout) {
        LinearLayout contenedorInsumos = findViewById(R.id.contenedorInsumos);
        Button btnEliminar = new Button(getApplicationContext());
        btnEliminar.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        btnEliminar.setText("Eliminar");
        btnEliminar.setBackgroundColor(Color.RED);
        btnEliminar.setTextColor(Color.WHITE); // Texto blanco para mayor contraste
        btnEliminar.setTag(supply.getId());
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supplyController.deleteSupply(supply.getId());// Eliminar el insumo de la base de datos
                contenedorInsumos.removeView(itemLayout); // Eliminar el item del contenedor
            }
        });
        return btnEliminar;
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
