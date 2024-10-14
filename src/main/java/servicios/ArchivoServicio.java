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

public class ArchivoServicio {
	protected List<Alumno> alumnosACargar;
	protected PromedioServicioImp promedio;

	public ArchivoServicio() {
		alumnosACargar = new ArrayList<>();
		promedio = new PromedioServicioImp();
	}

	public void exportarDatos(Map<String, Alumno> datosAlumno, String ruta) {
		   BufferedWriter writer = null;
		    try {
		        // Cambia la extensión del archivo a ".txt"
		        String rutaCompleta = ruta.endsWith(File.separator) ? ruta + "promedios.txt"
		                : ruta + File.separator + "promedios.txt";
		        writer = new BufferedWriter(new FileWriter(rutaCompleta));

		        // Iterar sobre los alumnos
		        for (Alumno alumno : datosAlumno.values()) {
		            // Escribir la información del alumno
		            writer.write("Alumno : " + alumno.getRut() + " - " + alumno.getNombre() + "\n");

		            if (alumno.getListaMateria().isEmpty()) {
		                // Si el alumno no tiene materias registradas
		                writer.write("  Sin materias registradas\n");
		            } else {
		                // Iterar sobre las materias del alumno
		                for (Materia materia : alumno.getListaMateria()) {
		                    if (materia.getNotas().isEmpty()) {
		                        // Si no hay notas en la materia
		                        writer.write("  Materia : " + materia.getNombre() + " - Promedio : Sin notas registradas\n");
		                    } else {
		                        // Calcular el promedio de las notas y escribir la materia con el promedio
		                        double promedioNota = promedio.calcularPromedio(materia.getNotas());
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
}
