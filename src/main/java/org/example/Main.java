package org.example;

import org.example.DataInitializer.DataLoader;
import org.example.controllers.MainController;
import org.example.models.Usuario;
import org.example.views.LoginView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Usuario usuario = new Usuario("1", "admin", "admin123", "John", "Doe", "Smith");
        MainController controller = new MainController(usuario); // Crear una instancia de MainController

        DataLoader.cargarDatos();
        DataLoader.initController(controller);
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                LoginView loginView = new LoginView(controller); // Pasar el controlador al constructor
                loginView.setVisible(true);
            }
        });
    }
}

