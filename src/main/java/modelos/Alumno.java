package modelos;

import java.util.ArrayList;
import java.util.List;

public class Alumno {
	protected String rut;
	protected String nombre;
	protected String apellido;
	protected String direccion;
	protected List<Materia> listaMateria;
	
	public Alumno() {
		 listaMateria = new ArrayList<Materia>();
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public List<Materia> getListaMateria() {
		return listaMateria;
	}

	public void setListaMateria(List<Materia> listaMateria) {
		this.listaMateria = listaMateria;
	}
	

	
	
	

}
