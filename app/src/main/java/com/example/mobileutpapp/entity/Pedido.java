package com.example.mobileutpapp.entity;

public class Pedido {
    private int id;
    private int idProducto;
    private double precio;
    private int cantidad;
    private double costoTotal;
    private int mesa;

    public Pedido(int id, int idProducto, double precio, int cantidad, double costoTotal, int mesa) {
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

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }
}
