package com.example.mobileutpapp.view;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileutpapp.R;
import com.example.mobileutpapp.adapter.PedidoAdapter;
import com.example.mobileutpapp.controller.PedidoController;
import com.example.mobileutpapp.entity.Pedido;

import java.util.List;

public class VerPedidos extends AppCompatActivity {

    private ListView listViewPedidos;
    private PedidoController pedidoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_pedidos);

        listViewPedidos = findViewById(R.id.listViewPedidos);
        pedidoController = new PedidoController(this);

        loadPedidos();
    }

    private void loadPedidos() {
        List<Pedido> pedidos = pedidoController.getAllPedidos();
        PedidoAdapter adapter = new PedidoAdapter(this, pedidos);
        listViewPedidos.setAdapter(adapter);
    }
}
