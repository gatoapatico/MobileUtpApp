package com.example.mobileutpapp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileutpapp.R;
import com.example.mobileutpapp.controller.PedidoController;
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
    private PedidoController pedidoController;
    private PlatoController platoController;

    public PedidoAdapter(Context context, List<Pedido> pedidos) {
        this.context = context;
        this.pedidos = pedidos;
        this.inflater = LayoutInflater.from(context);
        this.pedidoController = new PedidoController(context);
        this.platoController = new PlatoController(context);
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
            holder.btnEliminar = convertView.findViewById(R.id.buttonEliminar);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Pedido pedido = pedidos.get(position);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String fechaFormatted = sdf.format(pedido.getFecha());

        // Obtener el nombre del plato utilizando su ID
        Plato plato = platoController.getPlatoById(pedido.getIdProducto());
        String nombrePlato = plato != null ? plato.getNombre() : "Nombre no encontrado";

        String pedidoText = "Pedido #: " + pedido.getId() +
                "\nProducto: " + nombrePlato +  // Mostrar el nombre del plato en lugar del ID del producto
                "\nPrecio: " + pedido.getPrecio() +
                "\nCantidad: " + pedido.getCantidad() +
                "\nCosto Total: " + pedido.getCostoTotal() +
                "\nMesa: " + pedido.getMesa() +
                "\nFecha: " + fechaFormatted;

        holder.textViewPedido.setText(pedidoText);

        // Manejar la eliminación del pedido
        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean eliminado = pedidoController.eliminarPedido(pedido.getId());
                if (eliminado) {
                    Toast.makeText(context, "Pedido eliminado", Toast.LENGTH_SHORT).show();
                    pedidos.remove(pedido);
                    notifyDataSetChanged();
                } else {
                    Toast.makeText(context, "Error al eliminar el pedido", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return convertView;
    }

    static class ViewHolder {
        TextView textViewPedido;
        ImageButton btnEliminar;
    }
}
