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
    private JPanel panel;
    private JTextField idiomaField;
    private JTextField tamañoMonitorField;
    private JTextField cantidadRAMField;
    private JTextField procesadorField;
    private JTextField tamañoPantallaField;
    private String tipoProducto;

    public AgregarProductoView(MainController controller, MainView mainView, String tipoProducto) {
        this.controller = controller;
        this.mainView = mainView;
        this.tipoProducto = tipoProducto;
        setTitle("Agregar " + tipoProducto);
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        initComponents();
    }

    private void initComponents() {
        panel = new JPanel();
        panel.setLayout(null);

        // Agregamos el campo para el idioma del teclado (si es un teclado)
        if (tipoProducto.equals("Teclado")) {
            JLabel idiomaLabel = new JLabel("Idioma:");
            idiomaLabel.setBounds(20, 150, 80, 25);
            panel.add(idiomaLabel);

            idiomaField = new JTextField();
            idiomaField.setBounds(100, 150, 160, 25);
            panel.add(idiomaField);
        }

        // Agregamos el campo para el tamaño del monitor (si es un monitor)
        if (tipoProducto.equals("Monitor")) {
            JLabel tamañoMonitorLabel = new JLabel("Tamaño Monitor:");
            tamañoMonitorLabel.setBounds(20, 150, 120, 25);
            panel.add(tamañoMonitorLabel);

            tamañoMonitorField = new JTextField();
            tamañoMonitorField.setBounds(140, 150, 120, 25);
            panel.add(tamañoMonitorField);
        }

        // Agregamos el campo para la cantidad de RAM en notebook y PC de escritorio
        if (tipoProducto.equals("Notebook") || tipoProducto.equals("PC de escritorio")) {
            JLabel cantidadRAMLabel = new JLabel("Cantidad de RAM:");
            cantidadRAMLabel.setBounds(20, 150, 120, 25);
            panel.add(cantidadRAMLabel);

            cantidadRAMField = new JTextField();
            cantidadRAMField.setBounds(140, 150, 120, 25);
            panel.add(cantidadRAMField);
        }

        // Agregamos el campo para el procesador en notebook y PC de escritorio
        if (tipoProducto.equals("Notebook") || tipoProducto.equals("PC de escritorio")) {
            JLabel procesadorLabel = new JLabel("Procesador:");
            procesadorLabel.setBounds(20, 190, 80, 25);
            panel.add(procesadorLabel);

            procesadorField = new JTextField();
            procesadorField.setBounds(100, 190, 160, 25);
            panel.add(procesadorField);
        }

        // Agregamos el campo para el tamaño de pantalla en notebook
        if (tipoProducto.equals("Notebook")) {
            JLabel tamañoPantallaLabel = new JLabel("Tamaño Pantalla:");
            tamañoPantallaLabel.setBounds(20, 230, 120, 25);
            panel.add(tamañoPantallaLabel);

            tamañoPantallaField = new JTextField();
            tamañoPantallaField.setBounds(140, 230, 120, 25);
            panel.add(tamañoPantallaField);
        }

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
        agregarButton.setBounds(100, 260, 100, 25);
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
            Producto producto = null;

            switch (tipoProducto) {
                case "Teclado":
                    String idioma = idiomaField.getText();
                    producto = new Teclado(id, marca, modelo, idioma);
                    break;
                case "Mouse":
                    producto = new Mouse(id, marca, modelo);
                    break;
                case "Monitor":
                    try {
                        int tamañoMonitor = Integer.parseInt(tamañoMonitorField.getText());
                        producto = new Monitor(id, marca, modelo, tamañoMonitor);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "El tamaño del monitor debe ser un valor numérico válido.");
                        return;
                    }
                    break;
                case "Notebook":
                    try {
                        int cantidadRAM = Integer.parseInt(cantidadRAMField.getText());
                        String procesador = procesadorField.getText();
                        int tamañoPantalla = Integer.parseInt(tamañoPantallaField.getText());
                        producto = new Notebook(id, marca, modelo, cantidadRAM, procesador, tamañoPantalla);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "La cantidad de RAM y el tamaño de pantalla deben ser valores numéricos válidos.");
                        return;
                    }
                    break;
                case "PC de escritorio":
                    try {
                        int cantidadRAMPC = Integer.parseInt(cantidadRAMField.getText());
                        String procesadorPC = procesadorField.getText();
                        producto = new PCDeEscritorio(id, marca, modelo, cantidadRAMPC, procesadorPC, null, null, null);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "La cantidad de RAM debe ser un valor numérico válido.");
                        return;
                    }
                    break;
            }

            if (producto != null) {
                controller.agregarProducto(producto);
                mainView.actualizarStockInfo();
                dispose();
                JOptionPane.showMessageDialog(mainView, "Producto agregado correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "Tipo de producto inválido.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
        }
    }
}
