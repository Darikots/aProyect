package com.Antartyca.ejercicio5.services;

import java.util.List;

import com.Antartyca.ejercicio5.model.CiudadTorneos;

public interface CiudadTorneosService {
	
	public CiudadTorneos guardaCiudad(CiudadTorneos c);
	
	public List<CiudadTorneos> getAll();
	
	public List<CiudadTorneos> getByFilter(CiudadTorneos c);
	
	public void deleteById(Integer id);
	
	public CiudadTorneos getById(Integer codigoCiudad);

}
