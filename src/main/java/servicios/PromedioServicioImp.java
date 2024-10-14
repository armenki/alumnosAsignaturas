package servicios;

import java.util.List;

public class PromedioServicioImp {
    
    /**
     * Calcula el promedio de una lista de notas.
     *
     * @param notas La lista de notas a promediar.
     * @return El promedio de las notas, o 0.0 si la lista está vacía.
     */
    public Double calcularPromedio(List<Double> notas) {
        if (notas == null || notas.isEmpty()) {
            return 0.0; 
        }

        double suma = 0.0;
        int contador = 0;

        for (Double nota : notas) {
            if (nota != null && nota >= 0) { 
                suma += nota;
                contador++;
            }
        }

        return contador > 0 ? suma / contador : 0.0;
    }
}
