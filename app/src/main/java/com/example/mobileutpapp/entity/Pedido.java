package com.example.mobileutpapp.entity;

public class Pedido {
    private int id;
    private int idProducto;
    private double precio;
    private int cantidad;
    private double costoTotal;
    private String mesa;

    public Pedido(int id, int idProducto, double precio, int cantidad, double costoTotal, String mesa) {
        this.id = id;
        this.idProducto = idProducto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.costoTotal = costoTotal;
        this.mesa = mesa;
    }

    public int getId() {
        return id;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public String getMesa() {
        return mesa;
    }
}
