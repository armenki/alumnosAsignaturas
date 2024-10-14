package servicios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import modelos.Alumno;
import modelos.Materia;
import modelos.MateriaEnum;

public class AlumnoServicioTest {
	
    @InjectMocks
    private AlumnoServicioImp alumnoServicio; 

    @Mock
    private AlumnoServicioImp alumnoServicioMock;

    private Materia matematicas;
    private Materia lenguaje;
    private Alumno alu;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);        
        matematicas = new Materia();
        matematicas.setNombre(MateriaEnum.MATEMATICAS);
        lenguaje = new Materia();
        lenguaje.setNombre(MateriaEnum.LENGUAJE);

        alu = new Alumno();
        alu.setRut("1.111.111-1");
        alu.setNombre("Juanito");
        alu.setApellido("Pérez");
        alu.setDireccion("Calle Falsa 123");
    }

    @Test
    public void crearAlumnoTest() {
    	boolean resultado = alumnoServicio.crearAlumno(alu);
        assertTrue(resultado);
        assertTrue(alumnoServicio.listaAlumno().containsKey(alu.getRut()));
    }
    
    @Test
    public void agregarMateriaTest() {       
        alumnoServicio.crearAlumno(alu);
        alumnoServicio.agregarMateria(alu.getRut(), matematicas);
        List<Materia> materias = alumnoServicio.materiasPorAlumno(alu.getRut());
        assertEquals(1, materias.size());
        assertEquals(MateriaEnum.MATEMATICAS, materias.get(0).getNombre());
    }
    
    @Test
    public void materiasPorAlumnoTest() {
        alumnoServicio.crearAlumno(alu);
        alumnoServicio.agregarMateria(alu.getRut(), matematicas);
        alumnoServicio.agregarMateria(alu.getRut(), lenguaje);
        List<Materia> materias = alumnoServicio.materiasPorAlumno(alu.getRut());
        assertEquals(2, materias.size());
        assertEquals(MateriaEnum.MATEMATICAS, materias.get(0).getNombre());
        assertEquals(MateriaEnum.LENGUAJE, materias.get(1).getNombre());
    }
    
    @Test
    public void listaAlumnoTest() {
        alumnoServicio.crearAlumno(alu);
        Map<String, Alumno> alumnos = alumnoServicio.listaAlumno();
        assertEquals(1, alumnos.size());
        assertTrue(alumnos.containsKey(alu.getRut()));
        assertEquals("Juanito", alumnos.get(alu.getRut()).getNombre());
    }
    
  
}