package com.example.mobileutpapp.entity;

public class Pedido {
    private int id;
    private int idProducto;
    private double precio;
    private int cantidad;
    private double costoTotal;
    private String mesa;
    private long fecha;  // Campo para almacenar la fecha en milisegundos

    public Pedido(int id, int idProducto, double precio, int cantidad, double costoTotal, String mesa, long fecha) {
        this.id = id;
        this.idProducto = idProducto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.costoTotal = costoTotal;
        this.mesa = mesa;
        this.fecha = fecha;
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

    public String getMesa() {
        return mesa;
    }

    public void setMesa(String mesa) {
        this.mesa = mesa;
    }

    public long getFecha() {
        return fecha;
    }

    public void setFecha(long fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", idProducto=" + idProducto +
                ", precio=" + precio +
                ", cantidad=" + cantidad +
                ", costoTotal=" + costoTotal +
                ", mesa='" + mesa + '\'' +
                ", fecha=" + fecha +
                '}';
    }
}
