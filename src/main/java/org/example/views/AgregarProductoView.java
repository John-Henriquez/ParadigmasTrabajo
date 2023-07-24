package org.example.views;

import org.example.controllers.MainController;
import org.example.models.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgregarProductoView extends JFrame {
    private MainController controller;
    private MainView mainView;
    private JTextField idField;
    private JTextField marcaField;
    private JTextField modeloField;

    public AgregarProductoView(MainController controller, MainView mainView) {
        this.controller = controller;
        this.mainView = mainView;
        setTitle("Agregar Producto");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(20, 30, 80, 25);
        panel.add(idLabel);

        idField = new JTextField();
        idField.setBounds(100, 30, 160, 25);
        panel.add(idField);

        JLabel marcaLabel = new JLabel("Marca:");
        marcaLabel.setBounds(20, 70, 80, 25);
        panel.add(marcaLabel);

        marcaField = new JTextField();
        marcaField.setBounds(100, 70, 160, 25);
        panel.add(marcaField);

        JLabel modeloLabel = new JLabel("Modelo:");
        modeloLabel.setBounds(20, 110, 80, 25);
        panel.add(modeloLabel);

        modeloField = new JTextField();
        modeloField.setBounds(100, 110, 160, 25);
        panel.add(modeloField);

        JButton agregarButton = new JButton("Agregar");
        agregarButton.setBounds(100, 150, 100, 25);
        panel.add(agregarButton);

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarProducto();
            }
        });

        add(panel);
    }

    private void agregarProducto() {
        String id = idField.getText();
        String marca = marcaField.getText();
        String modelo = modeloField.getText();

        if (!id.isEmpty() && !marca.isEmpty() && !modelo.isEmpty()) {
            // Crear el nuevo producto y agregarlo al controlador
            Producto producto = new ProductoBase(id, marca, modelo);
            controller.agregarProducto(producto);

            // Actualizar la información del stock en la vista principal
            mainView.actualizarStockInfo();

            // Cerrar la ventana de agregar producto
            dispose();

            JOptionPane.showMessageDialog(mainView, "Producto agregado correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
        }
    }

    // Clase auxiliar para representar un producto base
    // Puedes modificarlo según tus necesidades, o usar las clases existentes Teclado, Mouse, Monitor, Notebook, PCDeEscritorio, etc.
    private static class ProductoBase extends Producto {
        public ProductoBase(String id, String marca, String modelo) {
            super(id, marca, modelo);
        }
    }
}
