package model;

public class Avion {
	protected Integer Id;
	protected String Nombre;
	protected Integer Capacidad;
	protected String Marca;
	
	
	public String getMarca() {
		return Marca;
	}
	public void setMarca(String marca) {
		Marca = marca;
	}
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public Integer getCapacidad() {
		return Capacidad;
	}
	public void setCapacidad(Integer capacidad) {
		Capacidad = capacidad;
	}


}
