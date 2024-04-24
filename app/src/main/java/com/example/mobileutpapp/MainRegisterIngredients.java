package com.example.mobileutpapp;

import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainRegisterIngredients extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_register_ingredients);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            setTableConfiguration();

            return insets;
        });
    }



    public void setTableConfiguration() {

        List<Map<String, String>> dataSet = new ArrayList<>();
        dataSet.add(new HashMap<String, String>() {{
            put("Producto", "Col");
            put("Cantidad", "3");
            put("Precio", "10.00");
        }});
        dataSet.add(new HashMap<String, String>() {{
            put("Producto", "Fideos");
            put("Cantidad", "4");
            put("Precio", "10.00");
        }});

        // Obtenemos el ID del Layout
        TableLayout tableLayout = findViewById(R.id.tableLayout);


        // Itera sobre la lista de datos y crea filas para cada elemento
        for (Map<String, String> datosMap : dataSet) {
            TableRow dataRow = new TableRow(this);

            // Crea un TextView para el nombre
            TextView textViewNombre = new TextView(this);
            textViewNombre.setText(datosMap.get("Producto"));
            dataRow.addView(textViewNombre);

            // Crea un TextView para el email
            TextView textViewEmail = new TextView(this);
            textViewEmail.setText(datosMap.get("Cantidad"));
            dataRow.addView(textViewEmail);

            // Crea un TextView para el teléfono
            TextView textViewTelefono = new TextView(this);
            textViewTelefono.setText(datosMap.get("Precio"));
            dataRow.addView(textViewTelefono);

            // Agrega la fila de datos al TableLayout
            tableLayout.addView(dataRow);
        }




    }



}