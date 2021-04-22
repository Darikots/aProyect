package com.Antartyca.ejercicio5.services;

import java.util.List;

import com.Antartyca.ejercicio5.model.Federacion;

public interface FederacionService {
	
	public Federacion guardaFederacion(Federacion federacion);
	
	public void borraFederacionId(Integer idFederacion);
	
	public List<Federacion> getAllFederacion();
		
	public List<Federacion> getByFilter(Federacion filter);
	
	public Federacion getById(Integer id);

}
