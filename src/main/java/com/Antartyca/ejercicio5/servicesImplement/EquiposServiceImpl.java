package com.Antartyca.ejercicio5.servicesImplement;

import java.sql.Date;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.Antartyca.ejercicio5.model.Equipos;
import com.Antartyca.ejercicio5.repository.EquiposRepository;
import com.Antartyca.ejercicio5.services.EquiposService;


@Service
public class EquiposServiceImpl implements EquiposService {

	@Autowired
	private EquiposRepository repoEquipos;
	@Autowired
	EntityManager em;
	
	@Override
	public Equipos guardaEquipos(Equipos equipo) {
		Equipos result=new Equipos();
		try {
			result=repoEquipos.save(equipo);
		}
		catch(Exception ex) {
			System.out.println("Error al guardar equipo "+ex.getMessage());
		}
		return result;
	}

	@Override
	public void borraEquipoId(Integer idEquipo) {
		repoEquipos.deleteById(idEquipo);
		
	}

	@Override
	public List<Equipos> getAllEquipos() {
		
		return (List<Equipos>)repoEquipos.findAll();
	}

	

	@Override
	public List<Equipos> getByFilter(Equipos filter) {
		 List<Equipos> result = new ArrayList<Equipos>();

	        try {
	            if(filter != null) {
	                Example<Equipos> example = Example.of(filter);
	                result = repoEquipos.findAll(example);
	            }else {
	                result = repoEquipos.findAll();
	            }

	        } catch (Exception e) {
	            System.out.println("[getByFilter] exception: "+e.getMessage());
	        }

	        return result;
	}

	@Override
	public Equipos getById(Integer idEquipo) {
		Optional < Equipos > optional = repoEquipos.findById(idEquipo);
        Equipos equipos = null;
        if (optional.isPresent()) {
            equipos = optional.get();
        } else {
            throw new RuntimeException(" Equipo no encontrado con id :: " + idEquipo);
        }
        return equipos;
	}

	@Override
	public List<Equipos> seleccionarAnioFun(Date fechaFundacion){
		
		
		TypedQuery<Equipos> query = em.createQuery("SELECT e from Equipos e where e.fechaFundacion >= '" +  fechaFundacion + "' ", Equipos.class);
		List<Equipos> listaAnioFun= query.getResultList();
		return listaAnioFun;
	    
	}

}
