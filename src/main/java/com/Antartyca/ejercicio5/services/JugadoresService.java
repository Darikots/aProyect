package com.Antartyca.ejercicio5.services;

import java.util.List;


import com.Antartyca.ejercicio5.model.Jugadores;

public interface JugadoresService {
	
	public Jugadores guardaJugadores(Jugadores jugador);

	public void borraJugadorId(Integer idJugador);
	
	public List<Jugadores> getAllJugadores();
	
	public List<Jugadores> seleccionarJugadorActivo(boolean Activo);
		
	public List<Jugadores> getByFilter(Jugadores filter);
	
	public Jugadores getById(Integer idJugadores);

	public List<Jugadores> seleccionarGolTar(Integer numGoles, Integer tarjetas);

	List<Jugadores> seleccionarAltura(Double altura);

	List<Jugadores> seleccionarGolPos(Integer numGoles, String puesto);
	
}
