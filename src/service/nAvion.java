package service;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import model.*;

public class nAvion {
	private String user = "root";
	private String pass = "";
	private String host = "jdbc:mysql://localhost:3306/";
	private String dbName = "grupo_6_db";
	public List<Avion> listaAviones() throws SQLException
	{
		
		List<Avion> lista = new ArrayList<Avion>();
		
		Connection conn = null;
		try{
			conn = (Connection) DriverManager.getConnection(host + dbName, user, pass);
			Statement st = (Statement) conn.createStatement();
			
			
			ResultSet rs = st.executeQuery("Select ID_AVION,NOMBRE,CAPACIDAD FROM AVION");
			while(rs.next()){
				Avion avion = new Avion();
				
				avion.setCapacidad(rs.getInt("CAPACIDAD"));
				avion.setId(rs.getInt("ID_AVION"));
				avion.setNombre(rs.getString("NOMBRE"));
				
				lista.add(avion);
				
			}
		}catch(SQLException e){
			System.out.println("se fue todo a la mierda");
			e.printStackTrace();
		}finally{
			conn.close();
		
		}
		
		return lista;
		
	}
	public boolean modificarAvion(Avion data) throws SQLException{
		
		boolean flag = false;
		
		Connection conn = null;
		
		try{
			conn = (Connection) DriverManager.getConnection(host + dbName, user, pass);
			Statement st = (Statement) conn.createStatement();
			if(st.executeUpdate("update avion set NOMBRE = '"+data.getNombre()+"',CAPACIDAD = "+data.getCapacidad()+" where ID_AVION = "+data.getId()) >= 0){
				
				flag = true;
				
			}else{
				flag = false;
				
			}
			
		}catch(SQLException e){
			System.out.println(e);
			e.printStackTrace();
		}finally{
			conn.close();
		
		}
		return flag;
		
		
	}
	public boolean GrabarAvion(Avion a)
	{
		String query = "";
		
		String parametros = "'"+a.getNombre()+"', " + a.getCapacidad();
		
		Connection conn = null;
		try{
			conn = (Connection) DriverManager.getConnection(host + dbName, user, pass);
			Statement st = (Statement) conn.createStatement();
			
			query = "INSERT INTO AVION (NOMBRE, MARCA, CAPACIDAD) VALUES("+parametros+")";
			
			
			st.execute(query);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			if(conn!= null)
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
	
	public Avion GetAvionByID(int ID)
	{

		String query = "";
		Avion avion = new Avion();
		int parametros =  ID;
		
		Connection conn = null;
		
		try{
			conn = (Connection) DriverManager.getConnection(host + dbName, user, pass);
			Statement st = (Statement) conn.createStatement();
			
			query = "SELECT NOMBRE, MARCA, CAPACIDAD FROM AVION WHERE ID_AVION="+parametros;
			
			ResultSet rs = st.executeQuery(query);
			
			if(rs!= null)
			{
				avion.setId(ID);
				avion.setNombre(rs.getString("NOMBRE"));
				avion.setCapacidad(rs.getInt("CAPACIDAD"));
				
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		finally
		{
			if(conn!= null)
			{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		}
		
		return avion;
		
	}
	
	public boolean eliminarAvion(Avion data) throws SQLException{
		
		boolean flag = false;
		
		Connection conn = null;
		
		try{
			conn = (Connection) DriverManager.getConnection(host + dbName, user, pass);
			Statement st = (Statement) conn.createStatement();
			if(st.executeUpdate("delete from avion where ID_AVION = "+data.getId()) >= 0){
				
				flag = true;
				
			}else{
				flag = false;
				
			}
			
		}catch(SQLException e){
			System.out.println(e);
			e.printStackTrace();
		}finally{
			conn.close();
		
		}
		return flag;
		
		
	}
}
