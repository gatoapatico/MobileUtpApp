package com.example.mobileutpapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileutpapp.R;
import com.example.mobileutpapp.controller.PlatoController;

public class PlatoAddActivity extends AppCompatActivity {
    private EditText editTextNombre;
    private EditText editTextDescripcion;
    private EditText editTextPrecio;
    private Button buttonGuardar;
    private Button btnPlato;
    private PlatoController platoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plato);

        editTextNombre = findViewById(R.id.et_addpl_nombrePlato);
        editTextDescripcion = findViewById(R.id.et_addpl_descripcion);
        editTextPrecio = findViewById(R.id.et_addpl_precioPlato);
        buttonGuardar = findViewById(R.id.btn_addplato_addplato);
        btnPlato = findViewById(R.id.btn_addplato_platos);

        platoController = new PlatoController(this);

        btnPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlatoAddActivity.this, PlatosActivity.class);
                startActivity(intent);
            }
        });

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarPlato();
            }
        });
    }

    private void guardarPlato() {
        String nombre = editTextNombre.getText().toString().trim();
        String descripcion = editTextDescripcion.getText().toString().trim();
        String precioStr = editTextPrecio.getText().toString().trim();

        if (TextUtils.isEmpty(nombre) || TextUtils.isEmpty(descripcion) || TextUtils.isEmpty(precioStr)) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        double precio;
        try {
            precio = Double.parseDouble(precioStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Precio inválido", Toast.LENGTH_SHORT).show();
            return;
        }

        platoController.addPlato(nombre, descripcion, precio);

        Toast.makeText(this, "Plato añadido", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(PlatoAddActivity.this, PlatosActivity.class);
        startActivity(intent);
        finish();
    }
}
