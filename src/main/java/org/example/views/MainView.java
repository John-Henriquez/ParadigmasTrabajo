package org.example.views;

import org.example.DataInitializer.DataLoader;
import org.example.controllers.MainController;
import org.example.models.Producto;
import org.example.models.Usuario;

import javax.swing.*;
import java.awt.*;
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

        int tecladoStock = DataLoader.getListaTeclados().size();
        int mouseStock = DataLoader.getListaMouses().size();
        int monitorStock = DataLoader.getListaMonitores().size();
        int notebookStock = DataLoader.getListaNotebooks().size();
        int pcEscritorioStock = DataLoader.getListaPCs().size();

        JTextArea stockInfoArea = new JTextArea();
        stockInfoArea.setEditable(false);
        stockInfoArea.setMargin(new Insets(10, 10, 10, 10));
        stockInfoArea.setText(
                "Teclado: " + tecladoStock + "\n" +
                        "Mouse: " + mouseStock + "\n" +
                        "Monitor: " + monitorStock + "\n" +
                        "Notebook: " + notebookStock + "\n" +
                        "PC de escritorio: " + pcEscritorioStock);
        panel.add(stockInfoArea);

        add(panel);
    }
    public void addButtonListener(Producto producto, ActionListener listener) {
        JButton detailButton = new JButton("Detalle");
        detailButton.addActionListener(listener);
        stockInfoArea.append(producto.getNombre() + " - " + producto.getModelo() + ": " + producto.getStock() + "\n");
        stockInfoArea.add(detailButton);
    }

    public void mostrarDetalle(String detalle) {
        JOptionPane.showMessageDialog(this, detalle, "Detalle", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Usuario usuario = new Usuario("1", "admin", "admin123", "John", "Doe", "Smith");
                MainController controller = new MainController(); // Supongamos que tienes un controlador llamado MainController
                MainView mainView = new MainView(usuario, controller);
                mainView.setVisible(true);
            }
        });
    }
}
