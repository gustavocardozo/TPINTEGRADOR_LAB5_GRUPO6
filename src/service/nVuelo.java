package service;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;

import model.Vuelo;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class nVuelo {
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "";
	private String dbName = "grupo_6_db";
	public List<Vuelo> ListaVuelos()
	{
		
		
		List<Vuelo> lista = new ArrayList<Vuelo>();
		
		Connection conn = null;
		
		try{
			conn = (Connection) DriverManager.getConnection(host + dbName, user, pass);
			Statement st = (Statement) conn.createStatement();
			
			
			ResultSet rs = st.executeQuery("SELECT ID_VUELO, ID_AVION, HORARIO_PARTIDA, HORARIO_LLEGADA FROM VUELO");
			while(rs.next()){
				
				Vuelo vuelo = new Vuelo();
				nAvion nA = new nAvion();
				
				vuelo.setAvion(nA.GetAvionByID(rs.getInt("ID_AVION")));
				vuelo.setIdVuelo(rs.getInt("ID_VUELO"));
				vuelo.setLlegada(rs.getDate("HORARIO_LLEGADA"));
				vuelo.setPartida(rs.getDate("HORARIO PARTIDA"));
				
				lista.add(vuelo);
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				if(conn!= null)
				{
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
		return lista;
	}
	
	
	public boolean GrabarVuelo(Vuelo v)
	{
		String query = "";
		String parametros = v.getAvion().getId()+",'"+v.getPartida()+"','"+v.getLlegada()+"'";
		
		Connection conn = null;
		try{
			conn = (Connection) DriverManager.getConnection(host + dbName, user, pass);
			Statement st = (Statement) conn.createStatement();
			
			query = "INSERT INTO VUELO (ID_AVION, HORARIO_PARTIDA, HORARIO_LLEGADA) VALUES("+parametros+")";
			
			
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
	
	public Vuelo GetAvionByID(int ID)
	{
		String query = "";
		Vuelo vuelo = new Vuelo();
		int parametros =  ID;
		
		Connection conn = null;
		
		try{
			conn = (Connection) DriverManager.getConnection(host + dbName, user, pass);
			Statement st = (Statement) conn.createStatement();
			
			query = "SELECT ID_VUELO, ID_AVION, HORARIO_PARTIDA, HORARIO_LLEGADA FROM VUELO WHERE ID_VUELO ="+parametros;
			
			ResultSet rs = st.executeQuery(query);
			
			if(rs!= null)
			{
				nAvion nA = new nAvion();
				
				vuelo.setAvion(nA.GetAvionByID(rs.getInt("ID_AVION")));
				vuelo.setIdVuelo(rs.getInt("ID_VUELO"));
				vuelo.setLlegada(rs.getDate("HORARIO_LLEGADA"));
				vuelo.setPartida(rs.getDate("HORARIO_PARTIDA"));
				
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
		
		return vuelo;
		
	}
	
	
	public boolean UpdateVuelo(Vuelo v)
	{
		String query = "";
		
		Connection conn = null;
		try{
			conn = (Connection) DriverManager.getConnection(host + dbName, user, pass);
			Statement st = (Statement) conn.createStatement();
			
			query = "UPDATE VUELO SET ID_AVION="+v.getAvion().getId()+", HORARIO_PARTIDA="+v.getPartida()+", HORARIO_LLEGADA="+v.getLlegada();
			
			
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
	
	
	
}
