package service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


import model.*;

public class nPaquete {

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
	
}
