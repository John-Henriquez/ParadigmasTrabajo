package org.example.controllers;

import org.example.models.Producto;
import org.example.models.Usuario;
import org.example.models.Teclado;
import org.example.models.Mouse;
import org.example.models.Monitor;
import org.example.models.Notebook;
import org.example.models.PCDeEscritorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainController {
    private Map<String, Producto> productos;
    private Map<String, Integer> stock;
    private Usuario usuario;

    public MainController(Usuario usuario) {
        this.usuario = usuario;
        productos = new HashMap<>();
        stock = new HashMap<>();
    }

    public List<Producto> obtenerProductos() {
        return new ArrayList<>(productos.values());
    }
    public void actualizarProducto(Producto producto) {
        if (productos.containsKey(producto.getId())) {
            productos.put(producto.getId(), producto);
        }
    }
    public void agregarProducto(Producto producto) {
        if (producto != null) {
            productos.put(producto.getId(), producto);
            actualizarStock(producto.getModelo(), 1);
        }
    }
    public void eliminarProducto(Producto producto) {
        if (productos.containsKey(producto.getId())) {
            productos.remove(producto.getId());
            actualizarStock(producto.getModelo(), -1);
        }
    }
    public void agregarTeclado(Teclado teclado) {
        productos.put(teclado.getId(), teclado);
        actualizarStock(teclado.getModelo(), 1);
    }

    public void agregarMouse(Mouse mouse) {
        productos.put(mouse.getId(), mouse);
        actualizarStock(mouse.getModelo(), 1);
    }

    public void agregarMonitor(Monitor monitor) {
        productos.put(monitor.getId(), monitor);
        actualizarStock(monitor.getModelo(), 1);
    }

    public void agregarNotebook(Notebook notebook) {
        productos.put(notebook.getId(), notebook);
        actualizarStock(notebook.getModelo(), 1);
    }

    public void agregarPCDeEscritorio(PCDeEscritorio pc) {
        productos.put(pc.getId(), pc);
        actualizarStock(pc.getModelo(), 1);
    }

    public int getCantidadExistente(String modelo) {
        int cantidadTotal = 0;
        for (Producto producto : productos.values()) {
            if (producto.getModelo().equals(modelo)) {
                cantidadTotal++;
            }
        }
        return cantidadTotal;
    }

    private void actualizarStock(String modelo, int cantidad) {
        stock.put(modelo, stock.getOrDefault(modelo, 0) + cantidad);
    }
    public Producto obtenerProducto(String idProducto) {
        return productos.get(idProducto);
    }
    public List<Producto> buscarProductosPorMarca(String marca) {
        List<Producto> productosConMarca = new ArrayList<>();
        for (Producto producto : productos.values()) {
            if (producto.getMarca().equalsIgnoreCase(marca)) {
                productosConMarca.add(producto);
            }
        }
        return productosConMarca;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
