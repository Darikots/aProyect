package com.Antartyca.ejercicio5.servicesImplement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.Antartyca.ejercicio5.model.CiudadTorneos;
import com.Antartyca.ejercicio5.repository.CiudadTorneosRepository;
import com.Antartyca.ejercicio5.services.CiudadTorneosService;

@Service
public class CiudadTorneosServiceImpl implements CiudadTorneosService{
	
	@Autowired
	CiudadTorneosRepository ciudadRepo;

	@Override
	public CiudadTorneos guardaCiudad(CiudadTorneos c) {
		try {
			ciudadRepo.save(c);
		}catch(Exception ex) {
			System.out.println("Error al guardar producto: " + ex.getMessage());
		}
		return c;
	}

	@Override
	public List<CiudadTorneos> getAll() {
		List<CiudadTorneos> result = new ArrayList<CiudadTorneos>();
		try {
			result = ciudadRepo.findAll();
		}catch(Exception ex) {
			System.out.println("[getAllLibros] exception:" + ex.getMessage());
		}
		return result;
	}

	@Override
	public List<CiudadTorneos> getByFilter(CiudadTorneos c) {
		List<CiudadTorneos> result = new ArrayList<CiudadTorneos>();
		try {
			if(c != null) {
				Example<CiudadTorneos> exmple = Example.of(c);
				result = ciudadRepo.findAll(exmple);
				
			}else {
				result = ciudadRepo.findAll();
			}
			
		}catch(Exception ex) {
			System.out.println("[getByFilter] exception:" + ex.getMessage());
		}
		return result;
	}

	@Override
	public void deleteById(Integer id) {
		try {
			ciudadRepo.deleteById(id);
		}catch(Exception ex) {
			System.out.println("[deleteById] exception:" + ex.getMessage());
		}
		
	}

	@Override
	public CiudadTorneos getById(Integer codigoCiudad) {
		Optional < CiudadTorneos > optional = ciudadRepo.findById(codigoCiudad);
		CiudadTorneos ciudad = null;
        if (optional.isPresent()) {
            ciudad = optional.get();
        } else {
            throw new RuntimeException(" Ciudad no encontrada con id :: " + codigoCiudad);
        }
        return ciudad;
	}
	

}
