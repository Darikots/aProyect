package com.Antartyca.ejercicio5.services;

import java.sql.Date;
import java.util.List;

import com.Antartyca.ejercicio5.model.Equipos;

public interface EquiposService {
	
	public Equipos guardaEquipos(Equipos equipo);
	
	public void borraEquipoId(Integer idEquipo);
	
	public List<Equipos> getAllEquipos();
		
	public List<Equipos> getByFilter(Equipos filter);
	
	public Equipos getById(Integer idEquipo);

	public List<Equipos> seleccionarAnioFun(Date fechaFunfacion);

}
