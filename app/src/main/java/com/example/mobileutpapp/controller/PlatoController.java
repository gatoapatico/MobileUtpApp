package com.example.mobileutpapp.controller;

import android.content.Context;

import com.example.mobileutpapp.entity.Plato;
import com.example.mobileutpapp.model.PlatoModel;

import java.util.List;

public class PlatoController {
    private PlatoModel platoModel;

    public PlatoController(Context context) {
        platoModel = new PlatoModel(context);
    }

    public void addPlato(String nombre, String descripcion, double precio, byte[] imagen) {
        Plato plato = new Plato(0, nombre, descripcion, precio, imagen); // El ID se autogenerará en la base de datos
        platoModel.addPlato(plato);
    }

    public Plato getPlatoById(int id) {
        return platoModel.getPlatoById(id);
    }

    public List<Plato> getAllPlatos() {
        return platoModel.getAllPlatos();
    }

    public void updatePlato(int id, String nombre, String descripcion, double precio, byte[] imagen) {
        Plato plato = new Plato(id, nombre, descripcion, precio, imagen);
        platoModel.updatePlato(plato);
    }

    public void deletePlato(int id) {
        platoModel.deletePlato(id);
    }
}
