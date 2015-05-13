package model;

import java.io.Serializable;
import java.util.Date;

public class Cliente implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected double id;
	protected String dni;
	protected String nombre;
	protected String direccion;
	protected String email;
	protected String telefono;
	protected Date fechaNac;
	protected String apellido;
	
	
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public double getId() {
		return id;
	}
	public void setId(double id) {
		this.id = id;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Date getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	}
