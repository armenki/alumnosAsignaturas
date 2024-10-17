package principal;

import vistas.Menu;

/**
 * clase principal del programa que contiene el método principal que sirve como punto de entrada al programa.
 * @param args Argumentos de línea de comandos (no utilizados en este programa).
 */

public class Main {
    public static void main(String[] args) {       
        Menu menu = new Menu(); 
        menu.iniciarMenu();
    }

}