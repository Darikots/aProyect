package com.Antartyca.ejercicio5.servicesImplement;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.Antartyca.ejercicio5.model.Jugadores;
import com.Antartyca.ejercicio5.repository.JugadoresRepository;
import com.Antartyca.ejercicio5.services.JugadoresService;

@Service
public class JugadoresServiceImpl implements JugadoresService {

	@Autowired
	JugadoresRepository repoJugadores;
	
	@Autowired
	EntityManager em;
	
	
	@Override
	public List<Jugadores> seleccionarJugadorActivo(boolean activo) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
 		CriteriaQuery<Jugadores> criteriaQ = cb.createQuery(Jugadores.class);
 		Root<Jugadores> root = criteriaQ.from(Jugadores.class);
 		
 		criteriaQ.select(root).where(cb.equal(root.get("activo"), activo));
 		TypedQuery<Jugadores> query = em.createQuery(criteriaQ);
 		
		return query.getResultList();
	}
	
	
	
	@Override
	public List<Jugadores> seleccionarGolTar(Integer numGoles, Integer tarjetas) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
 		CriteriaQuery<Jugadores> criteriaQ = cb.createQuery(Jugadores.class);
 		Root<Jugadores> root = criteriaQ.from(Jugadores.class);
 		
 		criteriaQ.select(root).where(cb.equal(root.get("numGoles"), numGoles), cb.equal(root.get("tarjetas"), tarjetas));
 		TypedQuery<Jugadores> query = em.createQuery(criteriaQ);
 		
		return query.getResultList();
	}
	
	
	@Override
	public List<Jugadores> seleccionarGolPos(Integer numGoles, String puesto){
		
		
		TypedQuery<Jugadores> query = em.createQuery("SELECT j from Jugadores j where j.numGoles >= " +  numGoles + " and j.puesto = '" + puesto + "' ", Jugadores.class);
		List<Jugadores> listaAltura= query.getResultList();
		return listaAltura;
	    
	}
	@Override
	public List<Jugadores> seleccionarAltura(Double altura){
		
		
		TypedQuery<Jugadores> query = em.createQuery("SELECT j from Jugadores j where j.altura <= " +  altura + " ", Jugadores.class);
		List<Jugadores> listaAltura= query.getResultList();
		return listaAltura;
	    
	}
	
	
	
	@Override
	public Jugadores guardaJugadores(Jugadores jugador) {
		Jugadores result = new Jugadores();
		try {
			result=repoJugadores.save(jugador);
		}
		catch(Exception ex) {
			System.out.println("Error al guardar jugador" + ex.getMessage());
		}
		return result;
	}

	@Override
	public void borraJugadorId(Integer idJugadores) {
		
		repoJugadores.deleteById(idJugadores);
		
		
	}

	@Override
	public List<Jugadores> getAllJugadores() {
		
		List<Jugadores> result= new ArrayList<Jugadores>();
		
		try {
			result=repoJugadores.findAll();
		}
		catch(Exception ex) {
			System.out.println("Error: "+ex);
		}
		return result;
	}

	
	
	@Override
	public List<Jugadores> getByFilter(Jugadores filter) {
		List<Jugadores> result = new ArrayList<Jugadores>();

        try {
            if(filter != null) {
                Example<Jugadores> example = Example.of(filter);
                result = repoJugadores.findAll(example);
            }else {
                result = repoJugadores.findAll();
            }

        } catch (Exception e) {
            System.out.println("[getByFilter] exception: "+e.getMessage());
        }

        return result;
	}

	@Override
	public Jugadores getById(Integer idJugadores) {
		Optional < Jugadores > optional = repoJugadores.findById(idJugadores);
        Jugadores jugadores = null;
        if (optional.isPresent()) {
            jugadores = optional.get();
        } else {
            throw new RuntimeException(" Jugador no encontrado con id :: " + idJugadores);
        }
        return jugadores;
	}

	

}
