package org.example.views;

import org.example.controllers.MainController;
import org.example.models.Producto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActualizarProductoView extends JFrame {
    private MainController controller;
    private MainView mainView;
    private Producto producto;
    private JTextField marcaField;
    private JTextField modeloField;

    public ActualizarProductoView(MainController controller, MainView mainView, Producto producto) {
        this.controller = controller;
        this.mainView = mainView;
        this.producto = producto;
        setTitle("Actualizar Producto");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel marcaLabel = new JLabel("Marca:");
        marcaLabel.setBounds(20, 30, 80, 25);
        panel.add(marcaLabel);

        marcaField = new JTextField(producto.getMarca());
        marcaField.setBounds(100, 30, 160, 25);
        panel.add(marcaField);

        JLabel modeloLabel = new JLabel("Modelo:");
        modeloLabel.setBounds(20, 70, 80, 25);
        panel.add(modeloLabel);

        modeloField = new JTextField(producto.getModelo());
        modeloField.setBounds(100, 70, 160, 25);
        panel.add(modeloField);

        JButton actualizarButton = new JButton("Actualizar");
        actualizarButton.setBounds(100, 110, 100, 25);
        panel.add(actualizarButton);

        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarProducto();
            }
        });

        add(panel);
    }

    private void actualizarProducto() {
        String nuevaMarca = marcaField.getText();
        String nuevoModelo = modeloField.getText();

        if (!nuevaMarca.isEmpty() && !nuevoModelo.isEmpty()) {
            // Actualizar los datos del producto
            producto.setMarca(nuevaMarca);
            producto.setModelo(nuevoModelo);

            // Actualizar el producto en el controlador
            controller.actualizarProducto(producto);

            // Actualizar la información del stock en la vista principal
            mainView.actualizarStockInfo();

            // Cerrar la ventana de actualizar producto
            dispose();

            JOptionPane.showMessageDialog(mainView, "Producto actualizado correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
        }
    }
}
