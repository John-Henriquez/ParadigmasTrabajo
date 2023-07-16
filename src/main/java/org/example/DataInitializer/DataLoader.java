package org.example.DataInitializer;

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
        Teclado teclado1 = new Teclado("1", "Marca1", "Modelo1", "Español");
        Teclado teclado2 = new Teclado("2", "Marca2", "Modelo2", "Inglés");
        Teclado teclado3 = new Teclado("3", "Marca3", "Modelo3", "Español");
        listaTeclados.add(teclado1);
        listaTeclados.add(teclado2);
        listaTeclados.add(teclado3);

        // Precarga de datos de Mouse
        Mouse mouse1 = new Mouse("4", "Marca4", "Modelo4");
        Mouse mouse2 = new Mouse("5", "Marca5", "Modelo5");
        Mouse mouse3 = new Mouse("6", "Marca6", "Modelo6");
        listaMouses.add(mouse1);
        listaMouses.add(mouse2);
        listaMouses.add(mouse3);

        // Precarga de datos de Monitores
        Monitor monitor1 = new Monitor("7", "Marca7", "Modelo7", 24);
        Monitor monitor2 = new Monitor("8", "Marca8", "Modelo8", 27);
        Monitor monitor3 = new Monitor("9", "Marca9", "Modelo9", 22);
        listaMonitores.add(monitor1);
        listaMonitores.add(monitor2);
        listaMonitores.add(monitor3);

        // Precarga de datos de Notebooks
        Notebook notebook1 = new Notebook("10", "Marca10", "Modelo10", 8, "Intel i5", 15);
        Notebook notebook2 = new Notebook("11", "Marca11", "Modelo11", 16, "AMD Ryzen 7", 14);
        Notebook notebook3 = new Notebook("12", "Marca12", "Modelo12", 12, "Intel i7", 17);
        listaNotebooks.add(notebook1);
        listaNotebooks.add(notebook2);
        listaNotebooks.add(notebook3);

        // Precarga de datos de PCs de Escritorio
        PCDeEscritorio pc1 = new PCDeEscritorio("13", "Marca13", "Modelo13", 16, "Intel i5", teclado1, mouse1, monitor1);
        PCDeEscritorio pc2 = new PCDeEscritorio("14", "Marca14", "Modelo14", 32, "AMD Ryzen 9", teclado2, mouse2, monitor2);
        PCDeEscritorio pc3 = new PCDeEscritorio("15", "Marca15", "Modelo15", 16, "Intel i7", teclado3, mouse3, monitor3);
        listaPCs.add(pc1);
        listaPCs.add(pc2);
        listaPCs.add(pc3);

        // Precarga de datos de Usuarios
        Usuario usuario = new Usuario("1", "admin", "admin123", "John", "Doe", "Smith");
        listaUsuarios.add(usuario);
    }

    public static List<Teclado> getListaTeclados() {
        return listaTeclados;
    }

    public static List<Mouse> getListaMouses() {
        return listaMouses;
    }

    public static List<Monitor> getListaMonitores() {
        return listaMonitores;
    }

    public static List<Notebook> getListaNotebooks() {
        return listaNotebooks;
    }

    public static List<PCDeEscritorio> getListaPCs() {
        return listaPCs;
    }
    public static List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }
}
