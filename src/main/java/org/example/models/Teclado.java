package org.example.models;

public class Teclado extends Producto {
    private String idioma;

    public Teclado(String id, String marca, String modelo, String idioma) {
        super(id, marca, modelo);
        this.idioma = idioma;
    }

    public String getIdioma() {
        return idioma;
    }
    public String toString() {
        return "Teclado = " +
                "id: " + getId() +
                "/ marca: " + getMarca() +
                "/ modelo: " + getModelo() +
                "/ idioma: '" + getIdioma();
    }
}
