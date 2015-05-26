package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;






import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import model.*;

public class nCliente {
	private String user = "root";
	private String pass = "";
	private String host = "jdbc:mysql://localhost:3306/";
	private String dbName = "grupo_6_db";
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
	
	public boolean GrabarEnBase(Cliente cliente)
	{
		String parametros;
		Connection conn = null;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
		parametros = "null,'"+cliente.getDireccion()+"', '"+cliente.getEmail()+"', '"+cliente.getTelefono()+"','"+cliente.getDni()+"','"+cliente.getNombre()+"','"+cliente.getApellido()+"','"+ sdf.format(cliente.getFechaNac())+"'"; 
		
		try{
			
			
			
			conn = (Connection) DriverManager.getConnection(host + dbName, user,pass );
			Statement st = (Statement) conn.createStatement();
			
			st.executeUpdate("INSERT INTO CLIENTE(ID_CLIENTE,DIRECCION,EMAIL,TELEFONO,DNI,NOMBRE,APELLIDO,FEC_NACIMIENTO) VALUES("+ parametros + ")");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			if(conn != null)
			{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
		
		return true;
		
	}
	public List<Cliente> ListaBase() 
	{
		String query;
		
		query = "SELECT * FROM CLIENTE";
		
		List<Cliente> clientes = new ArrayList<>();
		Connection conn= null;
		Statement st = null;
		
		try{			
			conn= (Connection) DriverManager.getConnection(host+dbName,user,pass);
			st = (Statement) conn.createStatement();
			
			ResultSet rs = st.executeQuery(query);
			
			while (rs.next())
			{
				Cliente clie = new Cliente();
				
				clie.setId(rs.getInt("ID_CLIENTE"));
				clie.setDni(rs.getString("DNI"));
				clie.setNombre(rs.getString("NOMBRE"));
				clie.setApellido(rs.getString("APELLIDO"));
				clie.setDireccion(rs.getString("DIRECCION"));
				clie.setEmail(rs.getString("EMAIL"));
				clie.setFechaNac(rs.getDate("FEC_NACIMIENTO"));
				clie.setTelefono(rs.getString("TELEFONO"));
				
				clientes.add(clie);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return clientes;
	}
	
	
}
