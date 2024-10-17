package servicios;

import java.util.List;

public interface PromedioServicio {
	
	 /**
     * Calcula el promedio de una lista de notas.
     *
     * @param notas La lista de notas a promediar. Puede contener valores nulos.
     * @return El promedio de las notas, o {null} si la lista está vacía o contiene solo notas nulas.
     */
	 public Double calcularPromedio(List<Double> notas);
}
