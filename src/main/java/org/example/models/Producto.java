package org.example.models;

public abstract class Producto {
    private String id;
    private String nombre;
    private String modelo;
    private int stock;

    public Producto(String id, String nombre, String modelo) {
        this.id = id;
        this.nombre = nombre;
        this.modelo = modelo;
        this.stock = 0;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getModelo() {
        return modelo;
    }
}

