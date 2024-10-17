package vistas;

import java.util.Scanner;
import java.util.InputMismatchException;
import utilidades.Utilidad;

/**
 * Plantilla para la creación de un menú interactivo con opciones predefinidas. 
 * Se encarga de gestionar la interacción con el usuario,
 * mostrando un menú en consola y manejando las diferentes opciones disponibles.
 * 
 * La clase hija debe implementar las siguientes funcionalidades:
 * - Crear un nuevo alumno
 * - Listar alumnos existentes
 * - Agregar una materia a un alumno
 * - Asignar una nota a un alumno en una materia
 * - Exportar datos
 * - Terminar el programa
 */
public abstract class MenuTemplate {

	protected Scanner leer;
 
	public MenuTemplate() {
		leer = new Scanner(System.in);
	}

	public abstract void crearAlumno(); 
	public abstract void listarAlumnos();
	public abstract void agregarMateria();
	public abstract void agregarNotaPasoUno();
	public abstract void exportarDatos();
	public abstract void terminarPrograma();
	
	public final void iniciarMenu() {
		int op = 0;

		do {
			System.out.println("------MENU------");
			System.out.println("1. Crear alumnos");
			System.out.println("2. Listar Alumnos");
			System.out.println("3. Agregar Materias");
			System.out.println("4. Agregar Notas");
			System.out.println("5. Exportar Datos");
			System.out.println("6. Salir");
			try {
				System.out.print("Seleccion: ");
				op = leer.nextInt();
				leer.nextLine(); 

				switch (op) {
				case 1:
					crearAlumno();
					break;
				case 2:
					listarAlumnos();
					break;
				case 3:
					agregarMateria();
					break;
				case 4:
					agregarNotaPasoUno();
					break;
				case 5:
					exportarDatos();
					break;
				case 6:
					terminarPrograma();
					return; 
				default:
					Utilidad.mostrarError("Opción no válida, intente nuevamente.");
					break;
				}
			} catch (InputMismatchException e) {
				Utilidad.mostrarError("Error: Debe ingresar un número. Intente de nuevo.");
				leer.nextLine();
			} catch (Exception e) {
				Utilidad.mostrarError("Error inesperado: " + e.getMessage());
			}
		} while (true); 
	}
}
