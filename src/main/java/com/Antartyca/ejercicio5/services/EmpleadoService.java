package com.Antartyca.ejercicio5.services;

import java.util.List;

import com.Antartyca.ejercicio5.model.Empleado;

public interface EmpleadoService {

	public Empleado guardaEmpleado(Empleado e);
	
	public List<Empleado> getAll();
	
	public List<Empleado> getByFilter(Empleado e);
	
	public void deleteById(Integer id);
	
	public Empleado getById(Integer id);
}
