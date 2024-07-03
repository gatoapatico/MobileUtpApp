package com.example.mobileutpapp.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.mobileutpapp.R;
import com.example.mobileutpapp.controller.PedidoController;
import com.example.mobileutpapp.controller.PlatoController;
import com.example.mobileutpapp.entity.Pedido;
import com.example.mobileutpapp.entity.Plato;
import java.util.ArrayList;
import java.util.List;

public class CrearPedido extends AppCompatActivity {
    private Spinner spinnerPlatos;
    private EditText editTextPrecio;
    private EditText editTextCantidad;
    private Spinner spinnerMesas;
    private Button btnGuardar;
    private PlatoController platoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_pedido);

        // Inicializar elementos del layout
        spinnerPlatos = findViewById(R.id.spinner_platos);
        editTextPrecio = findViewById(R.id.edit_text_precio);
        editTextCantidad = findViewById(R.id.edit_text_cantidad);
        spinnerMesas = findViewById(R.id.spinner_mesas);
        btnGuardar = findViewById(R.id.btn_guardar);

        // Inicializar controlador de platos y obtener la lista de platos
        platoController = new PlatoController(this);
        List<Plato> platos = platoController.getAllPlatos();

        // Crear lista de nombres de platos
        List<String> nombresPlatos = new ArrayList<>();
        for (Plato plato : platos) {
            nombresPlatos.add(plato.getNombre());
        }

        // Crear ArrayAdapter para el Spinner de platos con los nombres de los platos
        ArrayAdapter<String> adapterPlatos = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombresPlatos);
        adapterPlatos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPlatos.setAdapter(adapterPlatos);

        // Configurar listener para el Spinner de platos
        spinnerPlatos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Obtener el plato seleccionado (usando la lista original de platos)
                Plato selectedPlato = platos.get(position);
                // Mostrar el precio del plato seleccionado en el EditText de precio
                if (selectedPlato != null) {
                    editTextPrecio.setText(String.valueOf(selectedPlato.getPrecio()));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                editTextPrecio.setText("");
            }
        });

        // Crear ArrayAdapter para el Spinner de mesas
        ArrayAdapter<CharSequence> adapterMesas = ArrayAdapter.createFromResource(this,
                R.array.mesas_array, android.R.layout.simple_spinner_item);
        adapterMesas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMesas.setAdapter(adapterMesas);

        // Configurar listener para el botón de guardar
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí se implementará la lógica para guardar la información en la base de datos
                guardarPedido();
            }
        });
    }

    private void guardarPedido() {
        // Obtener datos del formulario
        int position = spinnerPlatos.getSelectedItemPosition();
        List<Plato> platos = platoController.getAllPlatos();
        Plato selectedPlato = platos.get(position);

        String cantidadStr = editTextCantidad.getText().toString();
        int cantidad = cantidadStr.isEmpty() ? 0 : Integer.parseInt(cantidadStr);

        String mesa = spinnerMesas.getSelectedItem().toString();

        // Calcular el costo total
        double costoTotal = selectedPlato.getPrecio() * cantidad;

        // Crear instancia de Pedido
        Pedido pedido = new Pedido(0, selectedPlato.getId(), selectedPlato.getPrecio(), cantidad, costoTotal, mesa);

        // Guardar el Pedido utilizando el controlador de Pedidos
        PedidoController pedidoController = new PedidoController(this);
        pedidoController.addPedido(pedido);

        // Mostrar mensaje de éxito al usuario
        Toast.makeText(this, "Pedido guardado correctamente", Toast.LENGTH_SHORT).show();
    }
}
