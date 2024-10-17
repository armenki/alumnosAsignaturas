package servicios;

import java.util.List;

public class PromedioServicioImp implements PromedioServicio{
    
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
