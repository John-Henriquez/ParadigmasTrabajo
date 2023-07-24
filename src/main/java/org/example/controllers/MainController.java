package org.example.controllers;

import org.example.models.Producto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainController {
    private List<Producto> productos;
    private Map<String, Integer> stock;

    public MainController() {
        productos = new ArrayList<>();
        stock = new HashMap<>();
    }
    public List<Producto> buscarProductosPorMarca(String marca) {
        List<Producto> productosMarca = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getMarca().equalsIgnoreCase(marca)) {
                productosMarca.add(producto);
            }
        }
        return productosMarca;
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
        actualizarStock(producto.getModelo(), 1);
    }

    public void eliminarProducto(Producto producto) {
        productos.remove(producto);
        actualizarStock(producto.getModelo(), -1);
    }
    public void actualizarProducto(Producto producto) {
        // Busca el producto en la lista y actualiza sus datos
        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            if (p.getId().equals(producto.getId())) {
                productos.set(i, producto);
                break;
            }
        }
    }
    public Producto obtenerProducto(String id) {
        for (Producto producto : productos) {
            if (producto.getId().equals(id)) {
                return producto;
            }
        }
        return null;
    }
    public List<Producto> obtenerProductos() {
        return productos;
    }

    public int getCantidadExistente(String modelo) {
        return stock.getOrDefault(modelo, 0);
    }
    private void actualizarStock(String modelo, int cantidad) {
        int stockActual = stock.getOrDefault(modelo, 0);
        int nuevoStock = Math.max(stockActual + cantidad, 0); // Evitar stock negativo
        stock.put(modelo, nuevoStock);
    }
    
}
