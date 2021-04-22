package com.Antartyca.ejercicio5.services;

import java.util.List;
import com.Antartyca.ejercicio5.model.Torneos;

public interface TorneosService {
	
	public Torneos guardaTorneos(Torneos torneo);
	
	public void borraTorneoId(Integer idTorneo);
	
	public List<Torneos> getAllTorneos();
		
	public List<Torneos> getByFilter(Torneos filter);
	
	public Torneos getById(Integer idTorneo);


}
