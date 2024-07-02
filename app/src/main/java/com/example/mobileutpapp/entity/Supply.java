package com.example.mobileutpapp.entity;

public class Supply {
    private int id;
    private String insumo;
    private double precio;
    private double peso;

    public Supply(int id, String insumo, double precio, double peso) {
        this.id = id;
        this.insumo = insumo;
        this.precio = precio;
        this.peso = peso;
    }

    public Supply(String insumo, double precio, double peso) {
        this.insumo = insumo;
        this.precio = precio;
        this.peso = peso;
    }

    public int getId() {
        return id;
    }

    public String getInsumo() {
        return insumo;
    }

    public double getPrecio() {
        return precio;
    }

    public double getPeso() {
        return peso;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setInsumo(String insumo) {
        this.insumo = insumo;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
}
