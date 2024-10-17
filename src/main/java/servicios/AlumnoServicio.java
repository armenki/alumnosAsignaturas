package servicios;

import java.util.List;
import java.util.Map;

import modelos.Alumno;
import modelos.Materia;

/**
 * La interfaz {AlumnoServicio} define los métodos que deben implementar las clases
 * encargadas de la gestión de alumnos
 */

public interface AlumnoServicio {

	/**
	 * Crea un nuevo alumno y lo agrega al sistema.	 *
	 * @param alumno El objeto de tipo {Alumno} que se va a crear.
	 * @return {true} si el alumno se creó correctamente, {false} en caso contrario.
	 */
	public boolean crearAlumno(Alumno alumno);
	
	/**
     * Devuelve un mapa con la lista de todos los alumnos registrados.
     * Las claves son los RUT de los alumnos y los valores son los objetos de tipo {Alumno}.     *
     * @return Un mapa que contiene todos los alumnos, donde la clave es el RUT y el valor es el alumno.
     */
	public Map<String, Alumno> listaAlumno();
	
	/**
     * Agrega una materia a un alumno específico.
     *
     * @param rutAlumno El RUT del alumno al que se le va a agregar la materia.
     * @param currentMate El objeto de tipo {Materia} que se va a agregar al alumno.
     */
	public void agregarMateria(String rutAlumno, Materia currentMate);

	/**
     * Devuelve una lista de las materias asociadas a un alumno específico.
     *
     * @param rutAlumno El RUT del alumno del que se desean obtener las materias.
     * @return Una lista de objetos {Materia} asociados al alumno, o una lista vacía si no tiene materias.
     */
	public List<Materia> materiasPorAlumno(String rutAlumno);

}
