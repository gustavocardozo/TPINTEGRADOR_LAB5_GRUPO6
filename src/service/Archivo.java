package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import model.Cliente;

public class Archivo<T> implements IArchivo<T> {

	protected String Path;
	protected Class<T> Clase;

	public Class<T> getClase() {
		return Clase;
	}

	public void setClase(Class<T> clase) {
		Clase = clase;
	}

	public String getPath() {
		return Path;
	}

	public void setPath(String path) {
		Path = path;
	}

	public Archivo(Class<T> typeParameterClass) {
		this.Clase = typeParameterClass;
		this.setPath(this.getClase().getSimpleName() + ".obj");

	}

	@Override
	public Collection<T> ListadoArchivo() {

		List<T> registros = new ArrayList<T>();

		ObjectInputStream ois = null;
		try {

			File f = new File(this.getPath());
			FileInputStream fis = new FileInputStream(f);
			ois = new ObjectInputStream(fis);

			while (true) {
				T registro;
				registro = (T) ois.readObject();

				registros.add(registro);
			}

		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return registros;
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public T GetByIdArchivo(double Id) {
		ObjectInputStream ois = null;
		double idO = 0;
		T obj = null;
		try {

			File f = new File(this.getPath());
			FileInputStream fis = new FileInputStream(f);
			ois = new ObjectInputStream(fis);
			while (true) {
				T objeto = (T) ois.readObject();
				Method getId = objeto.getClass().getMethod("getId");
				idO = (double) getId.invoke(objeto);
				if (Id == idO) {
					return objeto;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return obj;
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public Boolean InsertarArchivo(T t) {

		ObjectOutputStream oos = null;

		try {

			File archivo = new File(this.getPath());
			if (archivo.exists() == true) {
				// en el constructor del FileOutputStream le paso el parametro
				// true, para que agregué objetos al final.
				oos = new ObjectOutputStream((new FileOutputStream(
						this.getPath(), true))) {
					// sobre-escribo el metodo que escribe el encabezado //del
					// archivo si no es el primer registro, lo cual hace que
					// pueda seguir //agregando objetos al final del archivo
					@Override
					protected void writeStreamHeader() throws IOException {
					}

				};

				oos.writeUnshared(t);
				return true;

			} else { // si el archivo no existe
				oos = new ObjectOutputStream((new FileOutputStream(
						this.getPath())));

				oos.writeObject(t);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {

			try {
				oos.close();
			} catch (IOException e) {d
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public Boolean ModificarArchivo(T t) {

		try {

			this.GetByIdArchivo((double) t.getClass().getMethod("getId")
					.invoke(t));
		} catch (Exception e) {

		}

	}

	@Override
	public Boolean DeleteArchivo(T t) {

		try {
			
			Predicate<T> filter
			
			this.ListadoArchivo().removeIf();
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Override
	public double GetID() {

		ObjectInputStream ois = null;
		double max = 0;

		try {

			File f = new File(this.getPath());
			FileInputStream fis = new FileInputStream(f);
			ois = new ObjectInputStream(fis);
			while (true) {
				T objeto = (T) ois.readObject();
				Method getId = objeto.getClass().getMethod("getId");
				double id = (double) getId.invoke(objeto);
				if (id > max) {
					max = id;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return max;

		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
