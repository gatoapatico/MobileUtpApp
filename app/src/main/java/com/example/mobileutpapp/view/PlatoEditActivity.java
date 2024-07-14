package com.example.mobileutpapp.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileutpapp.R;
import com.example.mobileutpapp.controller.PlatoController;
import com.example.mobileutpapp.entity.Plato;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

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
    private ImageView imageViewPlatoInfo;
    private static final int REQUEST_IMAGE_PICK = 1;

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
        imageViewPlatoInfo = findViewById(R.id.imageViewPlatoInfo);
        platoController = new PlatoController(this);

        Intent intent = getIntent();
        platoId = intent.getIntExtra("PLATO_ID", -1);

        Plato plato = platoController.getPlatoById(platoId);

        etProductoNombre.setText(plato.getNombre());
        etProductoPrecio.setText(String.valueOf(plato.getPrecio()));
        etProductoDescripcion.setText(plato.getDescripcion());

        if (plato.getImagen() != null) {
            byte[] imageBytes = plato.getImagen();
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            imageViewPlatoInfo.setImageBitmap(bitmap);
        }

        imageViewPlatoInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagePicker();
            }
        });
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
                    Intent intent = new Intent(PlatoEditActivity.this, PlatoActivity.class);
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
                Intent intent = new Intent(PlatoEditActivity.this, PlatoActivity.class);
                startActivity(intent);
                //finish();
            }
        });

        btnPlatosBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlatoEditActivity.this, PlatoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_IMAGE_PICK);
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            if (selectedImageUri != null) {
                try {
                    updatedPlatoImagen = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                    imageViewPlatoInfo.setImageBitmap(updatedPlatoImagen);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Error al cargar la imagen", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private byte[] convertBitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
}
