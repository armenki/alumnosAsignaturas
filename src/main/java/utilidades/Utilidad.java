package utilidades;

import java.util.Scanner;

public class Utilidad {

	private static Scanner scanner;

	public static void mostrarMensaje(String mensaje) {
		System.out.print(mensaje);
	}

	public static void mostrarError(String mensajeError) {
		System.out.println("Error: " + mensajeError);
	}

	public static void mostrarAdvertencia(String mensajeAdvertencia) {
		System.out.println("Advertencia: " + mensajeAdvertencia);
	}

	public static void mostrarExito(String mensajeExito) {
		System.out.println("--- ¡" + mensajeExito + "! ---");
	}

	public static void regresar() {
		System.out.println("Regresando al menú principal...");
	}

	public static boolean validarRut(String rut) {
		return rut.matches("^[0-9]{1,2}(\\.[0-9]{3}){2}-[0-9kK]$");
	}

	/**
	 * Pausa la ejecución del programa para permitir que el usuario lea el mensaje
	 * en pantalla. El programa continuará cuando el usuario presione la tecla
	 * Enter.
	 */
	public static void pausar() {
		System.out.println("Presione Enter para continuar...");
		scanner = new Scanner(System.in);
		scanner.nextLine();
	}

	public static boolean validarNombre(String nombre) {
		return nombre.matches("^[A-Za-zÑñÁáÉéÍíÓóÚúü ]+$");
	}

}
