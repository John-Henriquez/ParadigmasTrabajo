package org.example.views;

import org.example.controllers.MainController;
import org.example.models.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginView extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private MainController controller;

    public LoginView(MainController controller) {
        this.controller = controller;
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        initComponents();
        initListeners();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(20, 30, 80, 25);
        panel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(100, 30, 160, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20, 70, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(100, 70, 160, 25);
        panel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(100, 120, 100, 25);
        panel.add(loginButton);

        add(panel);
    }

    private void initListeners() {
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Aquí puedes implementar la lógica de validación de usuario y contraseña
                if (validarCredenciales(username, password)) {
                    Usuario usuario = obtenerUsuario(username);
                    if (usuario != null) {
                        String mensajeBienvenida = "Bienvenido, " + usuario.getNombreCompleto() + "!";
                        JOptionPane.showMessageDialog(null, mensajeBienvenida);

                        // Abrir la siguiente vista (MainView) si la validación es exitosa
                        MainView mainView = new MainView(usuario, controller);
                        mainView.setVisible(true);

                        // Cerrar la vista actual (LoginView)
                        dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Credenciales inválidas!");
                    // Aquí puedes implementar acciones adicionales en caso de credenciales inválidas
                }
            }
        });
    }

    private boolean validarCredenciales(String username, String password) {
        // Aquí puedes implementar la lógica para validar las credenciales ingresadas

        // Ejemplo de validación básica con usuario precargado
        return username.equals("admin") && password.equals("admin123");
    }

    private Usuario obtenerUsuario(String username) {
        // Aquí puedes implementar la lógica para obtener el usuario por el nombre de usuario

        // Ejemplo de obtención de usuario precargado
        if (username.equals("admin")) {
            return new Usuario("1", "admin", "admin123", "John", "Doe", "Smith");
        }

        return null;
    }

}
