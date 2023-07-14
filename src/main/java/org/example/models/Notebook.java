package org.example.models;

public class Notebook extends Producto {
    private int cantidadRAM;
    private String procesador;
    private int tamañoPantalla;

    public Notebook(String id, String marca, String modelo, int cantidadRAM, String procesador, int tamañoPantalla) {
        super(id, marca, modelo);
        this.cantidadRAM = cantidadRAM;
        this.procesador = procesador;
        this.tamañoPantalla = tamañoPantalla;
    }

    public int getCantidadRAM() {
        return cantidadRAM;
    }

    public void setCantidadRAM(int cantidadRAM) {
        this.cantidadRAM = cantidadRAM;
    }

    public String getProcesador() {
        return procesador;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public int getTamañoPantalla() {
        return tamañoPantalla;
    }

    public void setTamañoPantalla(int tamañoPantalla) {
        this.tamañoPantalla = tamañoPantalla;
    }
}
