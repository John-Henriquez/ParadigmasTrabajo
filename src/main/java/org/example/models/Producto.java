package org.example.models;

public abstract class Producto {
    private String id;
    private String marca;
    private String modelo;
    private int cantidadExistente;

    public Producto(String id, String marca, String modelo) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.cantidadExistente = 0;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getCantidadExistente() {
        return cantidadExistente;
    }
    public void setCantidadExistente(int cantidadExistente) {
        this.cantidadExistente = cantidadExistente;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    @Override
    public String toString(){
        return "ID: " + getId() + ", Marca: " + getMarca() + ", Modelo: " + getModelo();
    }
}
