package com.Antartyca.ejercicio5.services;

import java.util.List;

import com.Antartyca.ejercicio5.model.Departamento;

public interface DepartamentoService {
	
	public Departamento guardaDepartamento(Departamento d);
	
	public List<Departamento> getAll();
	
	public List<Departamento> getByFilter(Departamento d);
	
	public void deleteById(Integer id);
	
	public Departamento getById(Integer id);

}
