package service;

import java.util.ArrayList;
import java.util.List;

import model.Cliente;

public class niCliente extends Archivo<Cliente> {

	public niCliente() {
		super(Cliente.class);
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Cliente> Listado()
	{
		return (ArrayList<Cliente>) this.ListadoArchivo();
		
	}
	
}
