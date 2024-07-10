package com.example.mobileutpapp.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileutpapp.R;
import com.example.mobileutpapp.controller.PlatoController;
import com.example.mobileutpapp.entity.Plato;

import java.io.ByteArrayOutputStream;

public class PlatoEditActivity extends AppCompatActivity {
    private EditText etProductoNombre;
    private EditText etProductoPrecio;
    private EditText etProductoDescripcion;
    private Button btnProductoActualizar;
    private Button btnProductoEliminar;
    private Button btnPlatosBack;
    private PlatoController platoController;
    private int platoId;
    private Bitmap updatedPlatoImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_plato);

        etProductoNombre = findViewById(R.id.etProductoEditarNombre);
        etProductoPrecio = findViewById(R.id.etProductoEditarPrecio);
        etProductoDescripcion = findViewById(R.id.etProductoEditarDescripcion);
        btnProductoActualizar = findViewById(R.id.btnActualizarProducto);
        btnProductoEliminar = findViewById(R.id.btnEliminarProducto);
        btnPlatosBack = findViewById(R.id.btn_platosedit_platos);
        platoController = new PlatoController(this);

        Intent intent = getIntent();
        platoId = intent.getIntExtra("PLATO_ID", -1);

        Plato plato = platoController.getPlatoById(platoId);

        etProductoNombre.setText(plato.getNombre());
        etProductoPrecio.setText(String.valueOf(plato.getPrecio()));
        etProductoDescripcion.setText(plato.getDescripcion());

        btnProductoActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updatedNombre = etProductoNombre.getText().toString();
                String updatedPrecio = etProductoPrecio.getText().toString();
                String updatedDescripcion = etProductoDescripcion.getText().toString();

                if (!updatedNombre.isEmpty() && !updatedPrecio.isEmpty() && !updatedDescripcion.isEmpty()) {
                    byte[] imageBytes = null;
                    if (updatedPlatoImagen != null) {
                        imageBytes = convertBitmapToByteArray(updatedPlatoImagen);
                    }
                    platoController.updatePlato(platoId, updatedNombre, updatedDescripcion, Double.parseDouble(updatedPrecio), imageBytes);
                    Toast.makeText(PlatoEditActivity.this, "Plato actualizado satisfactoriamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PlatoEditActivity.this, PlatosActivity.class);
                    startActivity(intent);
                    //finish();
                } else {
                    Toast.makeText(PlatoEditActivity.this, "Porfavor llene todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnProductoEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                platoController.deletePlato(platoId);
                Toast.makeText(PlatoEditActivity.this, "Plato borrado con éxito", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PlatoEditActivity.this, PlatosActivity.class);
                startActivity(intent);
                //finish();
            }
        });

        btnPlatosBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlatoEditActivity.this, PlatosActivity.class);
                startActivity(intent);
            }
        });
    }

    private byte[] convertBitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
}
