package service;

import java.io.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import model.*;

public class nPaquete {
	private String user = "root";
	private String pass = "";
	private String host = "jdbc:mysql://localhost:3306/";
	private String dbName = "grupo_6_db";
	public boolean grabarPaquete(Paquete paq) throws IOException
	{
		try{
			File f= new File("Paquetes.obj");
			if(f.exists()){
				FileOutputStream fos = new FileOutputStream(f,true);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(paq);
				oos.close();
			}
			else{
				FileOutputStream fos = new FileOutputStream(f);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(paq);
				oos.close();
			}
			
		}
		catch(Exception ex){
			return false;
			
		}
		finally{
			
		}
		
		
		
		
		
		return true;
	}
	
	public int getIDPaquete() throws ClassNotFoundException, IOException
	{
		ObjectInputStream ois = null;
		int max = 0;
		try
		{
			File f = new File("Paquetes.obj");
		
			if(f.exists()){
				FileInputStream fis = new FileInputStream(f);
				ois = new ObjectInputStream(fis);
				while(true)
				{
					Paquete p = (Paquete) ois.readObject();
					if (p.getIdPaquete() > max)
					{
						max=p.getIdPaquete();
					}
				}
				
			}
			return max;
			
		}
		catch(IOException e)
		{
			return max;
			
		}
		finally
		{
			if(ois != null)
				{ois.close();}
		}
	}
	
	public Paquete leerPaquete(int idPaquete) throws IOException
	{	
		Paquete paq = null;
		ObjectInputStream ois = null;
		try {
			File f = new File("Paquetes.obj");
			FileInputStream fis = new FileInputStream(f);
			ois = new ObjectInputStream(fis);
			while(true)
			{
				Paquete p = new Paquete();
				p = (Paquete)ois.readObject();
				
				if(p.getIdPaquete() == idPaquete)
				{
					paq = p;
					return paq;
				}
			}
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return paq;
		}		
		finally
		{
			ois.close();
			
		}
	}
	
	public List<Paquete> listaPaquete()
	{	
		List<Paquete> paquetes = new ArrayList<>();
		ObjectInputStream ois = null;
		try {
			File f = new File("Paquetes.obj");
			FileInputStream fis = new FileInputStream(f);
			ois = new ObjectInputStream(fis);
			while(true)
			{
				Paquete p = new Paquete();
				p = (Paquete)ois.readObject();
				paquetes.add(p);
				
			}
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return paquetes;
		}		
		finally
		{
			try {
				ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public boolean GrabarEnBase(Paquete paquete)
	{
		String parametros;
		
		
		parametros = paquete.getIdPaquete()+", '"+ paquete.getNombre()+"', '"+ paquete.getOrigen()+"', '"+ paquete.getDestino()+"', " + paquete.getPrecio(); 
		
		try{
			
			Connection conn = null;
			
			conn = (Connection) DriverManager.getConnection(host + dbName, user,pass );
			Statement st = (Statement) conn.createStatement();
			
			st.executeUpdate("INSERT INTO PAQUETE(ID_PAQUETE,NOMBRE,DESDE,HACIA,PRECIO) VALUES("+ parametros + ")");
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			
		}
		finally
		{
			
			
		}
		
		return true;
		
	}
	
	
	public List<Paquete> ListaBase() {
		String query = "SELECT ID_PAQUETE,PRECIO,DESDE,HACIA,NOMBRE FROM PAQUETE";
		List<Paquete> paquetes = new ArrayList<Paquete>();
		Connection conn = null;
		Statement st = null;

		try {

			conn = (Connection) DriverManager.getConnection(host + dbName,
					user, pass);
			st = (Statement) conn.createStatement();

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				Paquete p = new Paquete();
			
				p.setDestino(rs.getString("HACIA"));
				p.setOrigen(rs.getString("DESDE"));
				p.setNombre(rs.getString("NOMBRE"));
				p.setPrecio(rs.getFloat("PRECIO"));
				p.setIdPaquete(rs.getInt("ID_PAQUETE"));
				paquetes.add(p);

			}

		} catch (Exception e) {

		} finally {
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
		return paquetes;

	}
	
	
	public List<Paquete> listaParaMigrar() {
		List<Paquete> archivo = null;
		List<Paquete> base = null;
		try{
			archivo = listaPaquete();
			base = ListaBase();
			
			
			//archivo.removeAll(base);
			
			Iterator<Paquete> itArchivo = archivo.iterator();
			Iterator<Paquete> itBase = base.iterator();
			while(itArchivo.hasNext()){
				Paquete paqueteArchivo = (Paquete)itArchivo.next();
				while(itBase.hasNext()){
					Paquete paqueteBase = (Paquete)itBase.next();
					if(paqueteArchivo.getIdPaquete() == paqueteBase.getIdPaquete())
					{
						itArchivo.remove();
						break;
					}
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			return archivo;
		}
		
	}
	
}
