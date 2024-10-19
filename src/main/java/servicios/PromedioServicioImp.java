package servicios;

import java.text.DecimalFormat;
import java.util.List;

public class PromedioServicioImp implements PromedioServicio {

	public String calcularPromedio(List<Double> notas) {
		if (notas == null || notas.isEmpty()) {
			return "0,00";
		}
		double suma = 0.0;
		int contador = 0;
		for (Double nota : notas) {
			if (nota != null && nota >= 0) {
				suma += nota;
				contador++;
			}
		}
		double promedio = contador > 0 ? suma / contador : 0.0;
		DecimalFormat df = new DecimalFormat("#.00");
		return (df.format(promedio));
	}
}
