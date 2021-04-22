package com.Antartyca.ejercicio5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Antartyca.ejercicio5.model.Jugadores;

@Repository
public interface JugadoresRepository extends JpaRepository<Jugadores, Integer> {

	
}
