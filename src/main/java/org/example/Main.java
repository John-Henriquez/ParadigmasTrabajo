package org.example;

import org.example.DataInitializer.DataLoader;
import org.example.controllers.MainController;
import org.example.views.LoginView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        DataLoader.cargarDatos();
        MainController controller = new MainController(); // Crear una instancia de MainController

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                LoginView loginView = new LoginView(controller); // Pasar el controlador al constructor
                loginView.setVisible(true);
            }
        });
    }
}

