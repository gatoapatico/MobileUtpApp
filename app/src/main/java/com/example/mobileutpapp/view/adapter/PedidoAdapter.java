package com.example.mobileutpapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mobileutpapp.R;
import com.example.mobileutpapp.controller.PlatoController;
import com.example.mobileutpapp.entity.Pedido;
import com.example.mobileutpapp.entity.Plato;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class PedidoAdapter extends BaseAdapter {

    private Context context;
    private List<Pedido> pedidos;
    private LayoutInflater inflater;
    private PlatoController platoController;

    public PedidoAdapter(Context context, List<Pedido> pedidos) {
        this.context = context;
        this.pedidos = pedidos;
        this.inflater = LayoutInflater.from(context);
        this.platoController = new PlatoController(context); // Instancia del controlador de Plato
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
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.pedido_list_item, parent, false);
            holder = new ViewHolder();
            holder.textViewPedido = convertView.findViewById(R.id.textViewPedido);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Pedido pedido = pedidos.get(position);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String fechaFormatted = sdf.format(pedido.getFecha());

        // Obtener el nombre del plato utilizando su ID
        Plato plato = platoController.getPlatoById(pedido.getIdProducto());
        String nombrePlato = plato != null ? plato.getNombre() : "Nombre no encontrado";

        String pedidoText = "ID: " + pedido.getId() +
                "\nProducto: " + nombrePlato +  // Mostrar el nombre del plato en lugar del ID del producto
                "\nPrecio: " + pedido.getPrecio() +
                "\nCantidad: " + pedido.getCantidad() +
                "\nCosto Total: " + pedido.getCostoTotal() +
                "\nMesa: " + pedido.getMesa() +
                "\nFecha: " + fechaFormatted;

        holder.textViewPedido.setText(pedidoText);

        return convertView;
    }

    static class ViewHolder {
        TextView textViewPedido;
    }
}
