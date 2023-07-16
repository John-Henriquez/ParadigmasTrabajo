package org.example.views;

import org.example.controllers.MainController;
import org.example.models.Producto;
import org.example.models.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {

    private Usuario usuario;
    private MainController controller;
    private JTextArea stockInfoArea;

    public MainView(Usuario usuario, MainController controller) {
        this.usuario = usuario;
        this.controller = controller;
        setTitle("Main View");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel usernameLabel = new JLabel("Usuario: " + usuario.getNombreCompleto());
        panel.add(usernameLabel);

        JLabel stockLabel = new JLabel("              Stock");
        panel.add(stockLabel);

        stockInfoArea = new JTextArea();
        stockInfoArea.setEditable(false);
        stockInfoArea.setMargin(new Insets(10, 10, 10, 10));
        panel.add(stockInfoArea);

        actualizarStockInfo();

        /*
             // Botón Agregar
        JButton agregarButton = new JButton("Agregar");
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AgregarProductoView agregarProductoView = new AgregarProductoView(controller, MainView.this);
                agregarProductoView.setVisible(true);
            }
        });
        panel.add(agregarButton);

        // Botón Actualizar
        JButton actualizarButton = new JButton("Actualizar");
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
        */

        // Botón Eliminar
        JButton eliminarButton = new JButton("Eliminar");
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

        // Botón Buscar
        JButton buscarButton = new JButton("Buscar");
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String marcaProducto = JOptionPane.showInputDialog(MainView.this, "Ingrese la marca del producto a buscar:");
                // Realiza la búsqueda en el controlador
                // Actualiza la información de stock en la interfaz con los resultados obtenidos
                // Utiliza stockInfoArea.append() para agregar los resultados a la información de stock
            }
        });
        panel.add(buscarButton);

        // Botón Listar
        JButton listarButton = new JButton("Listar");
        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtén la lista completa de productos del controlador
                // Actualiza la información de stock en la interfaz con los resultados obtenidos
                // Utiliza stockInfoArea.append() para agregar los resultados a la información de stock
            }
        });
        panel.add(listarButton);

        add(panel);
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

    public void mostrarDetalle(String detalle) {
        JOptionPane.showMessageDialog(this, detalle, "Detalle", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Usuario usuario = new Usuario("1", "admin", "admin123", "John", "Doe", "Smith");
                MainController controller = new MainController();
                MainView mainView = new MainView(usuario, controller);
                mainView.setVisible(true);
            }
        });
    }
}
