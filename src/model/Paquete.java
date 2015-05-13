package model;

import java.io.Serializable;

public class Paquete implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int idPaquete;
	protected String Nombre;
	protected float Precio;
	protected String Destino;
	protected String Origen;
	
	public int getIdPaquete() {
		return idPaquete;
	}
	public void setIdPaquete(int idPaquete) {
		this.idPaquete = idPaquete;
	}
	public float getPrecio() {
		return Precio;
	}
	public void setPrecio(float precio) {
		Precio = precio;
	}
	public String getDestino() {
		return Destino;
	}
	public void setDestino(String destino) {
		Destino = destino;
	}
	public String getOrigen() {
		return Origen;
	}
	public void setOrigen(String origen) {
		Origen = origen;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	
	
	
}
