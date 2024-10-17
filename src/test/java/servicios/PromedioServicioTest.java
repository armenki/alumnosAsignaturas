package servicios;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PromedioServicioTest {
	private PromedioServicio promedioServicio;

	@Before
	public void setup() {
		// Inicializa la implementación del servicio
		promedioServicio = new PromedioServicioImp();
	}
	
    @Test
    public void calcularPromedioConNotasValidasTest() {
        List<Double> notas = Arrays.asList(5.0, 6.0, 7.0);
        Double promedio = promedioServicio.calcularPromedio(notas);
        assertEquals(6.0, promedio, 0.001);
    }
    
    @Test
    public void calcularPromedioConUnaNotaTest() {
        List<Double> notas = Collections.singletonList(7.5);
        Double promedio = promedioServicio.calcularPromedio(notas);
        assertEquals(7.5, promedio, 0.001);
    }
    
    @Test
    public void calcularPromedioConListaVaciaTest() {
        List<Double> notas = Collections.emptyList();
        Double promedio = promedioServicio.calcularPromedio(notas);
        assertEquals(0.0, promedio, 0.001);
    }
    
    @Test
    public void calcularPromedioConListaNulaTest() {
        Double promedio = promedioServicio.calcularPromedio(null);
        assertEquals(0.0, promedio, 0.001);
    }
    
    @Test
    public void calcularPromedioIgnorandoNotasNulasTest() {
        List<Double> notas = Arrays.asList(5.0, null, 8.0, null, 6.0);
        Double promedio = promedioServicio.calcularPromedio(notas);
        assertEquals(6.33, promedio, 0.01); // margen de error pequeño
    }
    
    @Test
    public void calcularPromedioIgnorandoNotasNegativasTest() {
        List<Double> notas = Arrays.asList(5.0, -4.0, 6.0);
        Double promedio = promedioServicio.calcularPromedio(notas);
        assertEquals(5.5, promedio, 0.001);
    }
    
    @Test
    public void calcularPromedioConCerosTest() {
        List<Double> notas = Arrays.asList(0.0, 0.0, 5.0, 10.0);
        Double promedio = promedioServicio.calcularPromedio(notas);
        assertEquals(3.75, promedio, 0.001);
    }
}
