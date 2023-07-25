package org.example.views;

import org.example.controllers.MainController;
import org.example.models.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainView extends JFrame {

    private Usuario usuario;
    private MainController controller;
    private JTextArea stockInfoArea;
    private JComboBox<String> productoComboBox;

    public MainView(Usuario usuario, MainController controller) {
        this.usuario = usuario;
        this.controller = controller;
        setTitle("Main View");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        this.stockInfoArea = new JTextArea();

        initComponents();
        actualizarStockInfo();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel usernameLabel = new JLabel("Usuario: " + usuario.getNombreCompleto());
        panel.add(usernameLabel, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel(new GridLayout(0, 1, 10, 5));

        JButton agregarButton = new JButton("Agregar Producto");
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productoComboBox.setVisible(true);
            }
        });
        buttonsPanel.add(agregarButton);

        productoComboBox = new JComboBox<>(new String[]{"Teclado", "Mouse", "Monitor", "Notebook", "PC de escritorio"});
        productoComboBox.setVisible(false);
        productoComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipoProducto = (String) productoComboBox.getSelectedItem();
                String nombreProducto = JOptionPane.showInputDialog(MainView.this, "Ingrese el nombre del producto:");

                if (nombreProducto != null && !nombreProducto.isEmpty()) {
                    if (tipoProducto.equals("Teclado")) {
                        String id = "teclado-id";
                        String idioma = JOptionPane.showInputDialog(MainView.this, "Ingrese el idioma del teclado:");
                        Teclado teclado = new Teclado(id, nombreProducto, tipoProducto, idioma);
                        controller.agregarTeclado(teclado);
                    } else if (tipoProducto.equals("Mouse")) {
                        String id = "mouse-id";
                        Mouse mouse = new Mouse(id, nombreProducto, tipoProducto);
                        controller.agregarMouse(mouse);
                    } else if (tipoProducto.equals("Monitor")) {
                        String id = "monitor-id";
                        int tamañoMonitor = Integer.parseInt(JOptionPane.showInputDialog(MainView.this, "Ingrese el tamaño del monitor:"));
                        Monitor monitor = new Monitor(id, nombreProducto, tipoProducto, tamañoMonitor);
                        controller.agregarMonitor(monitor);
                    } else if (tipoProducto.equals("Notebook")) {
                        String id = "notebook-id";
                        int cantidadRAM = Integer.parseInt(JOptionPane.showInputDialog(MainView.this, "Ingrese la cantidad de RAM del notebook:"));
                        String procesador = JOptionPane.showInputDialog(MainView.this, "Ingrese el procesador del notebook:");
                        int tamañoPantalla = Integer.parseInt(JOptionPane.showInputDialog(MainView.this, "Ingrese el tamaño de pantalla del notebook:"));
                        Notebook notebook = new Notebook(id, nombreProducto, tipoProducto, cantidadRAM, procesador, tamañoPantalla);
                        controller.agregarNotebook(notebook);
                    } else if (tipoProducto.equals("PC de escritorio")) {
                        String id = "pc-id";
                        int cantidadRAMPC = Integer.parseInt(JOptionPane.showInputDialog(MainView.this, "Ingrese la cantidad de RAM de la PC de escritorio:"));
                        String procesadorPC = JOptionPane.showInputDialog(MainView.this, "Ingrese el procesador de la PC de escritorio:");
                        PCDeEscritorio pc = new PCDeEscritorio(id, nombreProducto, tipoProducto, cantidadRAMPC, procesadorPC, null, null, null);
                        controller.agregarPCDeEscritorio(pc);
                    }
                    productoComboBox.setVisible(false);
                    actualizarStockInfo();
                } else {
                    JOptionPane.showMessageDialog(MainView.this, "Por favor, ingrese el nombre del producto.");
                }
            }
        });
        buttonsPanel.add(productoComboBox);

        JButton actualizarButton = new JButton("Actualizar Producto");
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idProducto = JOptionPane.showInputDialog(MainView.this, "Ingrese el ID del producto a actualizar:");
                Producto producto = controller.obtenerProducto(idProducto);
                if (producto != null) {
                    ActualizarProductoView actualizarProductoView = new ActualizarProductoView(controller, MainView.this, producto);
                    actualizarProductoView.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(MainView.this, "No se encontró el producto con ID: " + idProducto);
                }
            }
        });
        buttonsPanel.add(actualizarButton);

        JButton listarProductosButton = new JButton("Listar Productos");
        listarProductosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarProductos();
            }
        });
        buttonsPanel.add(listarProductosButton);

        JButton eliminarButton = new JButton("Eliminar Producto");
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idProducto = JOptionPane.showInputDialog(MainView.this, "Ingrese el ID del producto a eliminar:");
                Producto producto = controller.obtenerProducto(idProducto);
                if (producto != null) {
                    int confirm = JOptionPane.showConfirmDialog(MainView.this, "¿Está seguro de eliminar el producto?");
                    if (confirm == JOptionPane.YES_OPTION) {
                        controller.eliminarProducto(producto);
                        actualizarStockInfo();
                        JOptionPane.showMessageDialog(MainView.this, "Producto eliminado correctamente.");
                    }
                } else {
                    JOptionPane.showMessageDialog(MainView.this, "No se encontró el producto con ID: " + idProducto);
                }
            }
        });
        buttonsPanel.add(eliminarButton);

        JButton buscarProductosButton = new JButton("Buscar Productos por Marca");
        buscarProductosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String marcaProducto = JOptionPane.showInputDialog(MainView.this, "Ingrese la marca del producto a buscar:");
                buscarProductosPorMarca(marcaProducto);
            }
        });
        buttonsPanel.add(buscarProductosButton);
        panel.add(buttonsPanel, BorderLayout.CENTER);
        add(panel);
    }

    private void listarProductos() {
        StringBuilder productosText = new StringBuilder();
        List<Producto> productos = controller.obtenerProductos();
        for (Producto producto : productos) {
            productosText.append(producto.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(this, productosText.toString(), "Lista de Productos", JOptionPane.INFORMATION_MESSAGE);
    }

    private void buscarProductosPorMarca(String marcaProducto) {
        StringBuilder productosText = new StringBuilder();
        List<Producto> productos = controller.buscarProductosPorMarca(marcaProducto);
        for (Producto producto : productos) {
            productosText.append(producto.toString()).append("\n");
        }
        if (productosText.length() > 0) {
            JOptionPane.showMessageDialog(this, productosText.toString(), "Productos con Marca: " + marcaProducto, JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No se encontraron productos con la marca: " + marcaProducto, "Productos no encontrados", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void actualizarStockInfo() {
        List<Producto> productos = controller.obtenerProductos();
        if (!productos.isEmpty()) {
            StringBuilder stockInfo = new StringBuilder();
            for (Producto producto : productos) {
                int cantidad = controller.getCantidadExistente(producto.getModelo());
                stockInfo.append(producto.getMarca()).append(" - ").append(producto.getModelo()).append(": ").append(cantidad).append("\n");
            }
            stockInfoArea.setText(stockInfo.toString());
        } else {
            stockInfoArea.setText("No hay productos registrados.");
        }
    }
}
