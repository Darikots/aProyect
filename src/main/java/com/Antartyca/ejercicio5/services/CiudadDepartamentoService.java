package com.Antartyca.ejercicio5.services;

import java.util.List;

import com.Antartyca.ejercicio5.model.CiudadDepartamento;

public interface CiudadDepartamentoService {
	
	public CiudadDepartamento guardaCiudad(CiudadDepartamento c);
	
	public List<CiudadDepartamento> getAll();
	
	public List<CiudadDepartamento> getByFilter(CiudadDepartamento c);
	
	public void deleteById(Integer id);
	
	public CiudadDepartamento getById(Integer id);
}
