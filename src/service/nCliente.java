package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;



import model.*;

public class nCliente {

	public boolean grabarCliente(Cliente clie) throws IOException
	{
		ObjectOutputStream oos = null;

		try {

			File archivo = new File("Clientes.obj");
			if (archivo.exists() == true) {
			//en el constructor del FileOutputStream le paso el parametro true, para que agregué objetos al final.
			oos = new ObjectOutputStream((new FileOutputStream("Clientes.obj", true))) {
			//sobre-escribo el metodo que escribe el encabezado //del archivo si no es el primer registro, lo cual hace que pueda seguir //agregando objetos al final del archivo
				@Override
				protected void writeStreamHeader() throws IOException {
				}
	
			};
	
			oos.writeUnshared(clie);
			return true;
	
			} else { //si el archivo no existe
				oos = new ObjectOutputStream((new FileOutputStream("Clientes.obj")));
		
				oos.writeObject(clie);
				return true;
			}
		}
		finally{
			oos.close();
			
		}
		// OLD
		/*File f= new File("Clientes.obj");
		ObjectOutputStream oos= null;
		if(f.exists())
		{
			oos = new ObjectOutputStream(new FileOutputStream(f,true));
			
		}
		else
		{
			
			oos = new ObjectOutputStream(new FileOutputStream(f));
		}
		
		oos.writeObject(clie);
		oos.reset();
		oos.close();
		
		return true;*/
	}
	
	public int getIDPaquete() throws ClassNotFoundException, IOException
	{
		ObjectInputStream ois = null;
		int max = 0;
		try
		{
			File f = new File("Clientes.obj");
			FileInputStream fis = new FileInputStream(f);
			ois = new ObjectInputStream(fis);
			while(true)
			{
				Cliente c = (Cliente) ois.readObject();
				
				if (c.getId() > max)
				{
					max=(int) c.getId();
				}
			}
				
		}
		catch(IOException e)
		{
			return max;
			
		}
		finally
		{
			ois.close();
		}
	}
	
	public Cliente leerCliente(int idCliente) throws IOException
	{	
		Cliente clie = null;
		ObjectInputStream ois = null;
		try {
			File f = new File("Clientes.obj");
			FileInputStream fis = new FileInputStream(f);
			ois = new ObjectInputStream(fis);
			while(true)
			{
				Cliente c = null;
				c = (Cliente)ois.readObject();
				
				if(c.getId() == idCliente)
				{
					clie = c;
					return clie;
				}
			}
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return clie;
		}		
		finally
		{
			ois.close();
			
		}
	}
	
	public List<Cliente> listaClientes()
	{	
		List<Cliente> clientes = new ArrayList<>();
		
		ObjectInputStream ois = null;
		try {
			File f = new File("Clientes.obj");
			FileInputStream fis = new FileInputStream(f);
			ois = new ObjectInputStream(fis);
			Cliente c = new Cliente();
			while(true)
			{
				c = null;
				c = (Cliente)ois.readObject();
				clientes.add(c);
			}
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return clientes;
		}		
		finally
		{
			if (ois!=null)
			{
				try {
					ois.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}
	
	
	
}
