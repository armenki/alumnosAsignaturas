package modelos;

import java.util.ArrayList;
import java.util.List;

public class Materia {
	protected MateriaEnum nombre;
	protected List<Double> notas;
	
	public Materia() {
		notas = new ArrayList<Double>();
	}

	public MateriaEnum getNombre() {
		return nombre;
	}

	public void setNombre(MateriaEnum nombre) {
		this.nombre = nombre;
	}

	public List<Double> getNotas() {
		return notas;
	}

	public void setNotas(List<Double> notas) {
		this.notas = notas;
	}
	
	

}
