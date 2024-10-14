package servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelos.Alumno;
import modelos.Materia;
import utilidades.Utilidad;

public class AlumnoServicioImp implements AlumnoServicio {
    protected Map<String, Alumno> listaAlumno;

    public AlumnoServicioImp() {
        listaAlumno = new HashMap<>();
    }

    public boolean crearAlumno(Alumno alumno) {
        StringBuilder errores = new StringBuilder();
        if (!validarDatosAlumno(alumno, errores)) {
            Utilidad.mostrarMensaje(errores.toString());
            return false;
        }

        if (listaAlumno.containsKey(alumno.getRut())) {
            Utilidad.mostrarError("Ya existe un alumno con el RUT " + alumno.getRut());
            return false;
        }

        listaAlumno.put(alumno.getRut(), alumno);
        Utilidad.mostrarExito("Alumno agregado exitosamente");
        return true;
    }

    public Map<String, Alumno> listaAlumno() {
        return listaAlumno;
    }

    public void agregarMateria(String rutAlumno, Materia currentMate) {
        Alumno alumno = listaAlumno.get(rutAlumno);
        if (alumno == null) {
            Utilidad.mostrarError("No se encontró un alumno con el RUT " + rutAlumno);
            return;
        }
       
        if (alumno.getListaMateria().stream().anyMatch(m -> m.getNombre().equals(currentMate.getNombre()))) {
            Utilidad.mostrarError("El alumno ya tiene la materia " + currentMate.getNombre() + ".");
            return;
        }

        alumno.getListaMateria().add(currentMate);
        Utilidad.mostrarExito("Materia " + currentMate.getNombre() + " agregada con éxito.");
    }

    public List<Materia> materiasPorAlumno(String rutAlumno) {
        Alumno alumno = listaAlumno.get(rutAlumno);
        if (alumno == null) {
            Utilidad.mostrarError("No se encontró un alumno con el RUT " + rutAlumno);
            return new ArrayList<>(); // Devuelve una lista vacía si no se encuentra el alumno
        }
        return alumno.getListaMateria();
    }

    private boolean validarTexto(String texto) {
        return texto != null && texto.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+");
    }

    private boolean validarDatosAlumno(Alumno alumno, StringBuilder errores) {
        boolean esValido = true;

        if (alumno == null) {
            errores.append("Error: El objeto alumno es nulo.\n");
            return false;
        }

        if (alumno.getRut() == null || !Utilidad.validarRut(alumno.getRut())) {
            errores.append("Error: El RUT del alumno es inválido. Debe seguir el formato 1.111.111-1.\n");
            esValido = false;
        }

        if (alumno.getNombre() == null || alumno.getNombre().isEmpty() || !validarTexto(alumno.getNombre())) {
            errores.append("Error: El nombre del alumno es inválido. Solo se permiten letras y espacios.\n");
            esValido = false;
        }

        if (alumno.getApellido() == null || alumno.getApellido().isEmpty() || !validarTexto(alumno.getApellido())) {
            errores.append("Error: El apellido del alumno es inválido. Solo se permiten letras y espacios.\n");
            esValido = false;
        }

        if (alumno.getDireccion() == null || alumno.getDireccion().isEmpty()) {
            errores.append("Error: La dirección del alumno no puede estar vacía.\n");
            esValido = false;
        } else if (alumno.getDireccion().matches("\\d+")) {
            errores.append("Error: La dirección no puede contener solo números.\n");
            esValido = false;
        }

        return esValido;
    }
}
