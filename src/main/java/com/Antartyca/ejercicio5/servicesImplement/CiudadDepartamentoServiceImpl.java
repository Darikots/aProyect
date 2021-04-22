package com.Antartyca.ejercicio5.servicesImplement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.Antartyca.ejercicio5.model.CiudadDepartamento;
import com.Antartyca.ejercicio5.repository.CiudadDepartamentoRepository;
import com.Antartyca.ejercicio5.services.CiudadDepartamentoService;

@Service
public class CiudadDepartamentoServiceImpl implements CiudadDepartamentoService{
	
	@Autowired
	CiudadDepartamentoRepository ciudadRepo;

	@Override
	public CiudadDepartamento guardaCiudad(CiudadDepartamento c) {
		try {
			ciudadRepo.save(c);
		}catch(Exception ex) {
			System.out.println("Error al guardar producto: " + ex.getMessage());
		}
		return c;
	}

	@Override
	public List<CiudadDepartamento> getAll() {
		List<CiudadDepartamento> result = new ArrayList<CiudadDepartamento>();
		try {
			result = ciudadRepo.findAll();
		}catch(Exception ex) {
			System.out.println("[getAllLibros] exception:" + ex.getMessage());
		}
		return result;
	}

	@Override
	public List<CiudadDepartamento> getByFilter(CiudadDepartamento c) {
		List<CiudadDepartamento> result = new ArrayList<CiudadDepartamento>();
		try {
			if(c != null) {
				Example<CiudadDepartamento> exmple = Example.of(c);
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
    public CiudadDepartamento getById(Integer id) {
        Optional < CiudadDepartamento > optional = ciudadRepo.findById(id);
        CiudadDepartamento ciudad = null;
        if (optional.isPresent()) {
            ciudad = optional.get();
        } else {
            throw new RuntimeException(" Ciudad no encontrado con id :: " + id);
        }
        return ciudad;
    }

}
