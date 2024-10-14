package servicios;

import java.util.List;
import java.util.Map;

import modelos.Alumno;
import modelos.Materia;

public interface AlumnoServicio {

    public boolean crearAlumno(Alumno alumno);

    public Map<String, Alumno> listaAlumno();
    
    public void agregarMateria(String rutAlumno, Materia currentMate);
    
    public List<Materia> materiasPorAlumno(String rutAlumno);

}
