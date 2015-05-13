package model;

import java.util.List;

public class Reserva {

	private int idReserva;
	private float total;
	private Paquete paquete;
	private Vuelo vuelo;
	private List<Cliente> clientes;
	
	public int getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public Paquete getPaquete() {
		return paquete;
	}
	public void setPaquete(Paquete paquete) {
		this.paquete = paquete;
	}
	public List<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	public Vuelo getVuelo() {
		return vuelo;
	}
	public void setVuelo(Vuelo vuelo) {
		this.vuelo = vuelo;
	}
	
}
