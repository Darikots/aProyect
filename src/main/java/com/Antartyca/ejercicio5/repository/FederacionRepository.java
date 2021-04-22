package com.Antartyca.ejercicio5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Antartyca.ejercicio5.model.Federacion;

@Repository
public interface FederacionRepository extends JpaRepository<Federacion,Integer>{

}
