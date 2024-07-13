package com.example.mobileutpapp.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileutpapp.R;
import com.example.mobileutpapp.controller.PlatoController;
import com.example.mobileutpapp.controller.UserController;
import com.example.mobileutpapp.entity.Plato;
import com.example.mobileutpapp.entity.User;

import java.io.ByteArrayOutputStream;

public class PlatosInfoActivity extends AppCompatActivity {
    private TextView tvPlatoNombre;
    private TextView tvPrecio;
    private TextView tvDescripcion;
    private PlatoController platoController;
    private Button btn_platos;
    private Button btn_editar;
    private int plato_id;
    private ImageView ivPlatoImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platos_info);

        btn_platos = findViewById(R.id.btn_platosinfo_platos);
        btn_editar = findViewById(R.id.btn_platosinfo_editar);
        tvPlatoNombre = findViewById(R.id.tvNombrePlato);
        tvPrecio = findViewById(R.id.tvPrecio);
        tvDescripcion = findViewById(R.id.tvDescripcion);
        ivPlatoImage = findViewById(R.id.ivPlatoImage);
        platoController = new PlatoController(this);

        Intent intent = getIntent();
        int platoId = intent.getIntExtra("PLATO_ID", -1);

        if (platoId != -1) {
            Plato plato = platoController.getPlatoById(platoId);
            if (plato != null) {
                tvPlatoNombre.setText(plato.getNombre());
                tvPrecio.setText(String.valueOf(plato.getPrecio()));
                tvDescripcion.setText(plato.getDescripcion());
                plato_id = plato.getId();
                byte[] imageBytes = plato.getImagen();
                if (imageBytes != null) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                    ivPlatoImage.setImageBitmap(bitmap);
                }
            }
        }

        btn_platos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlatosInfoActivity.this, PlatosActivity.class);
                startActivity(intent);
            }
        });

        btn_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlatosInfoActivity.this, PlatoEditActivity.class);
                intent.putExtra("PLATO_ID", plato_id);
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
