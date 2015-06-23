package service;

import java.util.Collection;

public interface IArchivo<T> {
	
	Collection<T> ListadoArchivo();

	T GetByIdArchivo(double Id);

	Boolean InsertarArchivo(T t);

	Boolean ModificarArchivo(T t);

	Boolean DeleteArchivo(T t);
	
	double GetID();

}
