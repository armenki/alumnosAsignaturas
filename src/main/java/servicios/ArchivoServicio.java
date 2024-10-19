package servicios;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import modelos.Alumno;
import modelos.Materia;
import utilidades.Utilidad;

/**
 * gestiona la exportación de los datos de los alumnos a un archivo de texto.
 * Incluye un servicio para calcular los promedios y una lista temporal de
 * alumnos mientras se procesan los datos.
 */
public class ArchivoServicio {

	protected PromedioServicio promedio;
	protected List<Alumno> alumnosACargar;

	public ArchivoServicio() {
		promedio = new PromedioServicioImp();
		alumnosACargar = new ArrayList<>();
	}

	/**
	 * Exporta los datos de los alumnos a un archivo de texto en la ruta
	 * especificada. Para cada alumno, se registra su RUT, nombre y las materias con
	 * sus respectivos promedios.
	 *
	 * @param datosAlumno Un mapa que contiene los alumnos, donde la clave es el RUT
	 *                    y el valor es un objeto {Alumno}.
	 * @param ruta        La ruta donde se guardará el archivo de texto.
	 */
	public void exportarDatos(Map<String, Alumno> datosAlumno, String ruta) {
		BufferedWriter writer = null;
		try {
			cargarAlumnos(datosAlumno);
			String rutaCompleta = ruta.endsWith(File.separator) ? ruta + "promedios.txt"
					: ruta + File.separator + "promedios.txt";
			writer = new BufferedWriter(new FileWriter(rutaCompleta));
			for (Alumno alumno : alumnosACargar) {
				writer.write("Alumno : " + alumno.getRut() + " - " + alumno.getNombre() + "\n");
				if (alumno.getListaMateria().isEmpty()) {
					writer.write("  Sin materias registradas\n");
				} else {
					for (Materia materia : alumno.getListaMateria()) {
						if (materia.getNotas().isEmpty()) {
							writer.write(
									"  Materia : " + materia.getNombre() + " - Promedio : Sin notas registradas\n");
						} else {
							String promedioNota = promedio.calcularPromedio(materia.getNotas());
							writer.write("  Materia : " + materia.getNombre() + " - Promedio : " + promedioNota + "\n");
						}
					}
				}
			}
			Utilidad.mostrarExito("Datos exportados correctamente a " + rutaCompleta);
		} catch (IOException e) {
			Utilidad.mostrarError("Error al exportar datos: " + e.getMessage());
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				Utilidad.mostrarError("Error al cerrar el escritor: " + e.getMessage());
			}
		}
	}

	private void cargarAlumnos(Map<String, Alumno> datosAlumno) {
		alumnosACargar.clear();
		alumnosACargar.addAll(datosAlumno.values());
	}
}
