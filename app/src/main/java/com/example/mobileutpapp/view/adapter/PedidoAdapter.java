package com.example.mobileutpapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mobileutpapp.R;
import com.example.mobileutpapp.entity.Pedido;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class PedidoAdapter extends BaseAdapter {

    private Context context;
    private List<Pedido> pedidos;
    private LayoutInflater inflater;

    public PedidoAdapter(Context context, List<Pedido> pedidos) {
        this.context = context;
        this.pedidos = pedidos;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return pedidos.size();
    }

    @Override
    public Object getItem(int position) {
        return pedidos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.pedido_list_item, parent, false);
        }

        TextView textViewPedido = convertView.findViewById(R.id.textViewPedido);
        Pedido pedido = pedidos.get(position);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String fechaFormatted = sdf.format(pedido.getFecha());

        String pedidoText = "ID: " + pedido.getId() +
                "\nProducto ID: " + pedido.getIdProducto() +
                "\nPrecio: " + pedido.getPrecio() +
                "\nCantidad: " + pedido.getCantidad() +
                "\nCosto Total: " + pedido.getCostoTotal() +
                "\nMesa: " + pedido.getMesa() +
                "\nFecha: " + fechaFormatted;

        textViewPedido.setText(pedidoText);

        return convertView;
    }
}
