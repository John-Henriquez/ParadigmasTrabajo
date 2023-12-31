package org.example.models;

public class Mouse extends Producto {
    public Mouse(String id, String marca, String modelo) {
        super(id, marca, modelo);
    }
    @Override
    public String toString() {
        return "Mouse = " +
                "id:" + getId() +
                "/ marca: " + getMarca() +
                "/ modelo: " + getModelo();
    }
}
