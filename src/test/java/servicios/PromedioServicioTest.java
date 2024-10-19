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
		promedioServicio = new PromedioServicioImp();
	}
	
    @Test
    public void calcularPromedioConNotasValidasTest() {
        List<Double> notas = Arrays.asList(5.0, 6.0, 7.0);
        String promedio = promedioServicio.calcularPromedio(notas);
        assertEquals("6,00", promedio);
    }
    
    @Test
    public void calcularPromedioConUnaNotaTest() {
        List<Double> notas = Collections.singletonList(7.5);
        String promedio = promedioServicio.calcularPromedio(notas);
        assertEquals("7,50", promedio);
    }
    
    @Test
    public void calcularPromedioConListaVaciaTest() {
        List<Double> notas = Collections.emptyList();
        String promedio = promedioServicio.calcularPromedio(notas);
        assertEquals("0,00", promedio);
    }
    
    @Test
    public void calcularPromedioConListaNulaTest() {
        String promedio = promedioServicio.calcularPromedio(null);
        assertEquals("0,00", promedio);
    }
    
    @Test
    public void calcularPromedioIgnorandoNotasNulasTest() {
        List<Double> notas = Arrays.asList(5.0, null, 8.0, null, 6.0);
        String promedio = promedioServicio.calcularPromedio(notas);
        assertEquals("6,33", promedio);
    }
    
    @Test
    public void calcularPromedioIgnorandoNotasNegativasTest() {
        List<Double> notas = Arrays.asList(5.0, -4.0, 6.0);
        String promedio = promedioServicio.calcularPromedio(notas);
        assertEquals("5,50", promedio);
    }
    
    @Test
    public void calcularPromedioConCerosTest() {
        List<Double> notas = Arrays.asList(0.0, 0.0, 5.0, 10.0);
        String promedio = promedioServicio.calcularPromedio(notas);
        assertEquals("3,75", promedio);
    }
}
