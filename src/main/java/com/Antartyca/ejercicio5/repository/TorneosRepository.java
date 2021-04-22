package com.Antartyca.ejercicio5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Antartyca.ejercicio5.model.Torneos;

@Repository
public interface TorneosRepository extends JpaRepository<Torneos,Integer> {

}
