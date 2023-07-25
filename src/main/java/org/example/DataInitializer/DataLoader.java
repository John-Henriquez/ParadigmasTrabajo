package org.example.DataInitializer;

import org.example.controllers.MainController;
import org.example.models.*;

import java.util.ArrayList;
import java.util.List;

public class DataLoader {
    private static List<Usuario> listaUsuarios = new ArrayList<>();
    private static List<Teclado> listaTeclados = new ArrayList<>();
    private static List<Mouse> listaMouses = new ArrayList<>();
    private static List<Monitor> listaMonitores = new ArrayList<>();
    private static List<Notebook> listaNotebooks = new ArrayList<>();
    private static List<PCDeEscritorio> listaPCs = new ArrayList<>();

    public static void cargarDatos() {
        // Precarga de datos de Teclados
        Teclado teclado1 = new Teclado("1", "Logitech", "G413", "Español");
        Teclado teclado2 = new Teclado("2", "Corsair", "K95 RGB Platinum", "Inglés");
        Teclado teclado3 = new Teclado("3", "Razer", "BlackWidow", "Español");
        listaTeclados.add(teclado1);
        listaTeclados.add(teclado2);
        listaTeclados.add(teclado3);

        // Precarga de datos de Mouse
        Mouse mouse1 = new Mouse("4", "Razer", "DeathAdder V2");
        Mouse mouse2 = new Mouse("5", "Logitech", "G Pro Wireless");
        Mouse mouse3 = new Mouse("6", "SteelSeries", "Rival 600");
        listaMouses.add(mouse1);
        listaMouses.add(mouse2);
        listaMouses.add(mouse3);

        // Precarga de datos de Monitores
        Monitor monitor1 = new Monitor("7", "Dell", "S2419HGF", 24);
        Monitor monitor2 = new Monitor("8", "Asus", "VG279QM", 27);
        Monitor monitor3 = new Monitor("9", "LG", "27GL850-B", 27);
        listaMonitores.add(monitor1);
        listaMonitores.add(monitor2);
        listaMonitores.add(monitor3);

        // Precarga de datos de Notebooks
        Notebook notebook1 = new Notebook("10", "HP", "Pavilion 15", 8, "Intel Core i5", 15);
        Notebook notebook2 = new Notebook("11", "Dell", "XPS 13", 16, "Intel Core i7", 13);
        Notebook notebook3 = new Notebook("12", "Lenovo", "ThinkPad X1 Carbon", 16, "Intel Core i7", 14);
        listaNotebooks.add(notebook1);
        listaNotebooks.add(notebook2);
        listaNotebooks.add(notebook3);

        // Precarga de datos de PCs de Escritorio
        PCDeEscritorio pc1 = new PCDeEscritorio("13", "HP", "Pavilion Desktop", 16, "Intel Core i5", teclado1, mouse1, monitor1);
        PCDeEscritorio pc2 = new PCDeEscritorio("14", "CyberPowerPC", "Gamer Xtreme VR", 32, "Intel Core i9", teclado2, mouse2, monitor2);
        PCDeEscritorio pc3 = new PCDeEscritorio("15", "Lenovo", "IdeaCentre 510A", 16, "AMD Ryzen 7", teclado3, mouse3, monitor3);
        listaPCs.add(pc1);
        listaPCs.add(pc2);
        listaPCs.add(pc3);

        // Precarga de datos de Usuarios
        Usuario usuario = new Usuario("1", "admin", "admin123", "John", "Doe", "Smith");
        listaUsuarios.add(usuario);
    }
    public static void initController(MainController controller) {
        // Agregar los productos precargados al controlador
        for (Teclado teclado : listaTeclados) {
            controller.agregarTeclado(teclado);
        }
        for (Mouse mouse : listaMouses) {
            controller.agregarMouse(mouse);
        }
        for (Monitor monitor : listaMonitores) {
            controller.agregarMonitor(monitor);
        }
        for (Notebook notebook : listaNotebooks) {
            controller.agregarNotebook(notebook);
        }
        for (PCDeEscritorio pc : listaPCs) {
            controller.agregarPCDeEscritorio(pc);
        }
    }
    public static void listarProductos() {
        System.out.println("Listado de Teclados:");
        for (Teclado teclado : listaTeclados) {
            System.out.println(teclado.toString());
        }

        System.out.println("Listado de Mouses:");
        for (Mouse mouse : listaMouses) {
            System.out.println(mouse.toString());
        }

        System.out.println("Listado de Monitores:");
        for (Monitor monitor : listaMonitores) {
            System.out.println(monitor.toString());
        }

        System.out.println("Listado de Notebooks:");
        for (Notebook notebook : listaNotebooks) {
            System.out.println(notebook.toString());
        }

        System.out.println("Listado de PCs de Escritorio:");
        for (PCDeEscritorio pc : listaPCs) {
            System.out.println(pc.toString());
        }
    }

}