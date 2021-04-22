package com.Antartyca.ejercicio5.servicesImplement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.Antartyca.ejercicio5.model.Federacion;
import com.Antartyca.ejercicio5.repository.FederacionRepository;
import com.Antartyca.ejercicio5.services.FederacionService;

@Service
public class FederacionServiceImpl implements FederacionService {

	@Autowired
	FederacionRepository repoFederacion;
	
	@Override
	public Federacion guardaFederacion(Federacion federacion) {
		Federacion result=new Federacion();
		try {
			result=repoFederacion.save(federacion);
		}
		catch(Exception ex) {
			System.out.println("Error al guardar federacion "+ex.getMessage());
		}
		return result;
	}

	@Override
	public void borraFederacionId(Integer idFederacion) {
		repoFederacion.deleteById(idFederacion);
		
	}

	@Override
	public List<Federacion> getAllFederacion() {
		
		List<Federacion> result= new ArrayList<Federacion>();
		
		try {
			result=repoFederacion.findAll();
		}
		catch(Exception ex) {
			System.out.println("Error: "+ex);
		}
		return result;
	}

	@Override
	public List<Federacion> getByFilter(Federacion filter) {
		List<Federacion> result = new ArrayList<Federacion>();

        try {
            if(filter != null) {
                Example<Federacion> example = Example.of(filter);
                result = repoFederacion.findAll(example);
            }else {
                result = repoFederacion.findAll();
            }

        } catch (Exception e) {
            System.out.println("[getByFilter] exception: "+e.getMessage());
        }

        return result;
	}
	
	@Override
    public Federacion getById(Integer id) {
        Optional < Federacion > optional = repoFederacion.findById(id);
        Federacion fed = null;
        if (optional.isPresent()) {
            fed = optional.get();
        } else {
            throw new RuntimeException(" Federacion no encontrado con id :: " + id);
        }
        return fed;
    }

}
