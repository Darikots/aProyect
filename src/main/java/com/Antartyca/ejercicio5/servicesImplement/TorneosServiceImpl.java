package com.Antartyca.ejercicio5.servicesImplement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.Antartyca.ejercicio5.model.Torneos;
import com.Antartyca.ejercicio5.repository.TorneosRepository;
import com.Antartyca.ejercicio5.services.TorneosService;

@Service
public class TorneosServiceImpl implements TorneosService {

	
	@Autowired
	TorneosRepository repoTorneos;
	
	@Override
	public Torneos guardaTorneos(Torneos torneo) {
		Torneos result=new Torneos();
		try {
			result=repoTorneos.save(torneo);
		}
		catch(Exception ex) {
			System.out.println("Error al guardar torneo"+ex.getMessage());
		}
		return result;
	}

	@Override
	public void borraTorneoId(Integer idTorneo) {
		repoTorneos.deleteById(idTorneo);
		
	}

	@Override
	public List<Torneos> getAllTorneos() {
		
		List<Torneos> result= new ArrayList<Torneos>();
		
		try {
			result=repoTorneos.findAll();
		}
		catch(Exception ex) {
			System.out.println("Error: "+ex);
		}
		return result;
	}

	@Override
	public List<Torneos> getByFilter(Torneos filter) {
		List<Torneos> result = new ArrayList<Torneos>();

        try {
            if(filter != null) {
                Example<Torneos> example = Example.of(filter);
                result = repoTorneos.findAll(example);
            }else {
                result = repoTorneos.findAll();
            }

        } catch (Exception e) {
            System.out.println("[getByFilter] exception: "+e.getMessage());
        }

        return result;
	}

	@Override
	public Torneos getById(Integer idTorneo) {
		Optional < Torneos > optional = repoTorneos.findById(idTorneo);
        Torneos torneos= null;
        if (optional.isPresent()) {
            torneos = optional.get();
        } else {
            throw new RuntimeException(" Torneo no encontrado con id :: " + idTorneo);
        }
        return torneos;
	}

}
