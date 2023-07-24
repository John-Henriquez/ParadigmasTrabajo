package org.example.models;

public class Monitor extends Producto {
    private int tamaño;

    public Monitor(String id, String marca, String modelo, int tamaño) {
        super(id, marca, modelo);
        this.tamaño = tamaño;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }
    public String toString() {
        return "Monitor = " +
                "id: " + getId()  +
                "/ marca: " + getMarca() +
                "/ modelo: " + getModelo() +
                "/ tamaño: " + getTamaño();
    }
}
