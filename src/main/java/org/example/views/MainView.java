package org.example.views;

import org.example.controllers.MainController;
import org.example.models.Producto;
import org.example.models.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {

    private Usuario usuario;
    private MainController controller;
    private JTextArea stockInfoArea;
    private JComboBox<String> productoComboBox;

    public MainView(Usuario usuario, MainController controller) {
        this.usuario = usuario;
        this.controller = controller;
        setTitle("Main View");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        this.stockInfoArea = new JTextArea();

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel usernameLabel = new JLabel("Usuario: " + usuario.getNombreCompleto());
        panel.add(usernameLabel);


        // Botón Agregar Producto
        JButton agregarButton = new JButton("Agregar Producto");
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipoProducto = (String) productoComboBox.getSelectedItem();
                AgregarProductoView agregarProductoView = new AgregarProductoView(controller, MainView.this, tipoProducto);
                agregarProductoView.setVisible(true);

                productoComboBox.setVisible(true);
            }
        });
        panel.add(agregarButton);

        productoComboBox = new JComboBox<>(new String[]{"Teclado", "Mouse", "Monitor", "Notebook", "PC de escritorio"});
        productoComboBox.setVisible(false); // Establecer la visibilidad inicial como false
        panel.add(productoComboBox);

        // Botón Actualizar Producto
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
        panel.add(actualizarButton);

        // Botón Listar Productos
        JButton listarProductosButton = new JButton("Listar Productos");
        listarProductosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarProductos();
            }
        });
        panel.add(listarProductosButton);

        // Botón Listar Producto Individual
        JButton listarProductoIndividualButton = new JButton("Listar Producto Individual");
        listarProductoIndividualButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idProducto = JOptionPane.showInputDialog(MainView.this, "Ingrese el ID del producto a listar:");
                listarProductoIndividual(idProducto);
            }
        });
        panel.add(listarProductoIndividualButton);

        // Botón Eliminar Producto
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
        panel.add(eliminarButton);

        // Botón Buscar Productos por Marca
        JButton buscarProductosButton = new JButton("Buscar Productos por Marca");
        buscarProductosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String marcaProducto = JOptionPane.showInputDialog(MainView.this, "Ingrese la marca del producto a buscar:");
                buscarProductosPorMarca(marcaProducto);
            }
        });
        // JComboBox para mostrar los nombres de los productos
        JComboBox<String> productoComboBox = new JComboBox<>(new String[]{"Teclado", "Mouse", "Monitor", "Notebook", "PC de escritorio"});
        productoComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productoSeleccionado = (String) productoComboBox.getSelectedItem();
                if (productoSeleccionado != null) {
                    agregarProducto(productoSeleccionado);
                }
            }
        });
        panel.add(buscarProductosButton);
        add(panel);
    }
    private void agregarProducto(String tipoProducto) {
        AgregarProductoView agregarProductoView = new AgregarProductoView(controller, MainView.this, tipoProducto);
        agregarProductoView.setVisible(true);
    }


    public void actualizarStockInfo() {
        stockInfoArea.setText("");
        int tecladoStock = controller.getCantidadExistente("Teclado");
        int mouseStock = controller.getCantidadExistente("Mouse");
        int monitorStock = controller.getCantidadExistente("Monitor");
        int notebookStock = controller.getCantidadExistente("Notebook");
        int pcEscritorioStock = controller.getCantidadExistente("PC de escritorio");

        stockInfoArea.append(
                "Teclado: " + tecladoStock + "\n" +
                        "Mouse: " + mouseStock + "\n" +
                        "Monitor: " + monitorStock + "\n" +
                        "Notebook: " + notebookStock + "\n" +
                        "PC de escritorio: " + pcEscritorioStock + "\n"
        );
    }

    private void listarProductos() {
        StringBuilder productosText = new StringBuilder();
        for (Producto producto : controller.obtenerProductos()) {
            productosText.append(producto.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(this, productosText.toString(), "Lista de Productos", JOptionPane.INFORMATION_MESSAGE);
    }

    private void listarProductoIndividual(String idProducto) {
        Producto producto = controller.obtenerProducto(idProducto);
        if (producto != null) {
            JOptionPane.showMessageDialog(this, producto.toString(), "Detalle del Producto", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró el producto con ID: " + idProducto, "Producto no encontrado", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void buscarProductosPorMarca(String marcaProducto) {
        StringBuilder productosText = new StringBuilder();
        for (Producto producto : controller.buscarProductosPorMarca(marcaProducto)) {
            productosText.append(producto.toString()).append("\n");
        }
        if (productosText.length() > 0) {
            JOptionPane.showMessageDialog(this, productosText.toString(), "Productos con Marca: " + marcaProducto, JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No se encontraron productos con la marca: " + marcaProducto, "Productos no encontrados", JOptionPane.WARNING_MESSAGE);
        }
    }
}
