package vistas;

import java.util.Map;
import java.util.InputMismatchException;
import java.util.List;

import modelos.Alumno;
import modelos.Materia;
import modelos.MateriaEnum;
import servicios.AlumnoServicio;
import servicios.AlumnoServicioImp;
import servicios.ArchivoServicio;
import utilidades.Utilidad;

public class Menu extends MenuTemplate {
	protected AlumnoServicio alumnoServicio;
	protected ArchivoServicio archivoServicio;

	public Menu() {
		alumnoServicio = new AlumnoServicioImp();
		archivoServicio = new ArchivoServicio();
	}

	@Override
	public void crearAlumno() {
		Alumno a = new Alumno();
		System.out.println("--- Crear Alumno ---");
		Utilidad.mostrarMensaje("Ingresa Rut: ");
		a.setRut(leer.nextLine());
		Utilidad.mostrarMensaje("Ingresa Nombre: ");
		a.setNombre(leer.nextLine());
		Utilidad.mostrarMensaje("Ingresa Apellido: ");
		a.setApellido(leer.nextLine());
		Utilidad.mostrarMensaje("Ingresa Dirección: ");
		a.setDireccion(leer.nextLine());

		
		if (!alumnoServicio.crearAlumno(a)) {
			Utilidad.mostrarAdvertencia("Por favor, vuelve a ingresar los datos del alumno.");			
			Utilidad.pausar(); 
			crearAlumno();
		} else {
			Utilidad.mostrarExito("Alumno creado con éxito.");
			Utilidad.pausar();
			Utilidad.regresar();
		}
	}

	@Override
	public void listarAlumnos() {
		Map<String, Alumno> alumnos = alumnoServicio.listaAlumno();
		System.out.println("--- Listar Alumnos ---");

		if (alumnos.isEmpty()) {
			Utilidad.mostrarAdvertencia("No hay alumnos registrados.");
			Utilidad.pausar();
			Utilidad.regresar();
			return;
		}

		for (Map.Entry<String, Alumno> entry : alumnos.entrySet()) {
			Alumno alumno = entry.getValue();
			mostrarDatosAlumno(alumno);
		}
		Utilidad.regresar();
	}

	private void mostrarDatosAlumno(Alumno alumno) {
		System.out.println("Datos Alumno:");
		System.out.println("\tRUT: " + alumno.getRut());
		System.out.println("\tNombre: " + alumno.getNombre());
		System.out.println("\tApellido: " + alumno.getApellido());
		System.out.println("\tDirección: " + alumno.getDireccion());
		mostrarMateriasAlumno(alumno);
	}

	private void mostrarMateriasAlumno(Alumno alumno) {
		System.out.println("\tMaterias:");
	    if (alumno.getListaMateria().isEmpty()) {
	    	System.out.println("\t\tNo hay materias registradas");
	        return;
	    }
		
		for (Materia materia : alumno.getListaMateria()) {
			System.out.println("\t\t" + materia.getNombre());
			System.out.println("\t\t\tNotas: ");
			if (materia.getNotas().isEmpty()) {
				System.out.println("\t\t\tNo hay notas registradas.");
			} else {
				System.out.println("\t\t\t" + materia.getNotas());
			}
		}
	}

	@Override
	public void agregarMateria() {
		System.out.println("--- Agregar Materia ---");
		Utilidad.mostrarMensaje("Ingresa RUT del alumno: ");
		String aux = leer.nextLine();

		if (Utilidad.validarRut(aux) && alumnoServicio.listaAlumno().containsKey(aux)) {
			seleccionarMateria(aux);
		} else {
			Utilidad.mostrarError("Alumno con ese RUT no existe");
			Utilidad.pausar(); 
			agregarMateria(); 
		}
	}

	private void seleccionarMateria(String rut) {
		for (int i = 0; i < MateriaEnum.values().length; i++) {
			System.out.println((i + 1) + ". " + MateriaEnum.values()[i]);
		}
		int opcion = leerOpcionValida("Selecciona una materia: ", MateriaEnum.values().length);
		Materia nuevaMateria = new Materia();
		nuevaMateria.setNombre(MateriaEnum.values()[opcion - 1]);
		alumnoServicio.agregarMateria(rut, nuevaMateria);
		Utilidad.pausar();
	}

	@Override
	public void agregarNotaPasoUno() {
		System.out.println("--- Agregar Nota ---");
		Utilidad.mostrarMensaje("Ingresa el RUT del Alumno: ");
		String rutAlumno = leer.nextLine();		
		List<Materia> listaMaterias = alumnoServicio.materiasPorAlumno(rutAlumno);		
		
		if (listaMaterias.isEmpty()) {
			Utilidad.mostrarError("El alumno no tiene materias agregadas");
			Utilidad.pausar();
			return;
		}

		mostrarMateriasAlumno(listaMaterias);
		int opcionMateria = leerOpcionValida("Seleccionar materia: ", listaMaterias.size());
		Materia materiaSeleccionada = listaMaterias.get(opcionMateria - 1);
		agregarNota(materiaSeleccionada);
	}

	private void mostrarMateriasAlumno(List<Materia> listaMaterias) {
		System.out.println("Alumno tiene las siguientes materias agregadas:");
		for (int i = 0; i < listaMaterias.size(); i++) {
			System.out.println((i + 1) + ". " + listaMaterias.get(i).getNombre());
		}
	}

	private void agregarNota(Materia materiaSeleccionada) {
		double nota = leerNotaValida("Ingresar nota: ");
		if (nota == -1) {
			Utilidad.mostrarError("Nota inválida. Debe ingresar un número válido.");
			Utilidad.pausar();
			return;
		}
		materiaSeleccionada.getNotas().add(nota);
		Utilidad.mostrarExito("Nota agregada a " + materiaSeleccionada.getNombre());
		Utilidad.pausar();
	}

	@Override
	public void terminarPrograma() {
		System.out.println("¡Gracias por utilizar el sistema! Hasta pronto.");
		if (leer != null) {
			leer.close(); 
		}
		System.exit(0);
	}

	@Override
	public void exportarDatos() {
		System.out.print("Ingresa la ruta donde se guardará el archivo (promedios.csv): ");
		String ruta = leer.nextLine();
		archivoServicio.exportarDatos(alumnoServicio.listaAlumno(), ruta);		
		Utilidad.pausar(); 
	}

	private int leerOpcionValida(String mensaje, int maxOpciones) {
		int opcion = 0;
		do {
			Utilidad.mostrarMensaje(mensaje);
			try {
				opcion = leer.nextInt();
				leer.nextLine(); // Limpiar el buffer
				if (opcion < 1 || opcion > maxOpciones) {
					Utilidad.mostrarError("Error: Opción fuera de rango. Intente nuevamente.");
					Utilidad.pausar(); // Pausa para que el usuario lea el mensaje
				}
			} catch (InputMismatchException e) {
				Utilidad.mostrarError("Error: Debe ingresar un número válido.");
				leer.nextLine(); 
				Utilidad.pausar(); 
			}
		} while (opcion < 1 || opcion > maxOpciones);
		return opcion;
	}

	private double leerNotaValida(String mensaje) {
		double nota = -1;
		do {
			Utilidad.mostrarMensaje(mensaje);
			try {
				nota = leer.nextDouble();
				leer.nextLine();
				if (nota < 1.0 || nota > 7.0) {
					Utilidad.mostrarError("Error: La nota debe estar entre 1.0 y 7.0.");
					nota = -1; 
					Utilidad.pausar(); 
				}
			} catch (InputMismatchException e) {
				Utilidad.mostrarError("Error: Debe ingresar un número válido.");
				leer.nextLine(); 
				Utilidad.pausar(); 
			}
		} while (nota == -1);
		return nota;
	}
}
