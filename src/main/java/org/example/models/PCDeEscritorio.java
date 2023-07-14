package org.example.models;

public class PCDeEscritorio extends Producto {
    private int cantidadRAM;
    private String procesador;
    private Teclado teclado;
    private Mouse mouse;
    private Monitor monitor;

    public PCDeEscritorio(String id, String marca, String modelo, int cantidadRAM, String procesador, Teclado teclado, Mouse mouse, Monitor monitor) {
        super(id, marca, modelo);
        this.cantidadRAM = cantidadRAM;
        this.procesador = procesador;
        this.teclado = teclado;
        this.mouse = mouse;
        this.monitor = monitor;
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

    public Teclado getTeclado() {
        return teclado;
    }

    public void setTeclado(Teclado teclado) {
        this.teclado = teclado;
    }

    public Mouse getMouse() {
        return mouse;
    }

    public void setMouse(Mouse mouse) {
        this.mouse = mouse;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }
}
