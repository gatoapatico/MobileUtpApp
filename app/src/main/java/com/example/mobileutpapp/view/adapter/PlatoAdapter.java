package com.example.mobileutpapp.view.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileutpapp.R;
import com.example.mobileutpapp.entity.Plato;

import java.util.ArrayList;
import java.util.List;

public class PlatoAdapter extends RecyclerView.Adapter<PlatoAdapter.PlatoViewHolder> {
    private Context context;
    private List<Plato> platoList;

    public PlatoAdapter(Context context) {
        this.context = context;
        this.platoList = new ArrayList<>();
    }

    public void setPlatoList(List<Plato> platoList) {
        this.platoList = platoList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlatoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_plato, parent, false);
        return new PlatoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlatoViewHolder holder, int position) {
        Plato plato = platoList.get(position);
        holder.textViewNombre.setText(plato.getNombre());
        holder.textViewPrecio.setText(String.valueOf(plato.getPrecio()));
        if (plato.getImagen() != null) {
            holder.imageViewPlato.setImageBitmap(BitmapFactory.decodeByteArray(plato.getImagen(), 0, plato.getImagen().length));
        } else {
            holder.imageViewPlato.setImageResource(R.drawable.placeholder);
        }
    }

    @Override
    public int getItemCount() {
        return platoList.size();
    }

    public static class PlatoViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNombre;
        TextView textViewPrecio;
        ImageView imageViewPlato;

        public PlatoViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.textViewNombre);
            textViewPrecio = itemView.findViewById(R.id.textViewPrecio);
            imageViewPlato = itemView.findViewById(R.id.imageViewPlato);
        }
    }
}
