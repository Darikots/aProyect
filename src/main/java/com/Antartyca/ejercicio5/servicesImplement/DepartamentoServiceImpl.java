package com.Antartyca.ejercicio5.servicesImplement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.Antartyca.ejercicio5.model.Departamento;
import com.Antartyca.ejercicio5.repository.DepartamentoRepository;
import com.Antartyca.ejercicio5.services.DepartamentoService;

@Service
public class DepartamentoServiceImpl implements DepartamentoService{
	
	@Autowired
	DepartamentoRepository depRepo;

	@Override
	public Departamento guardaDepartamento(Departamento d) {
		try {
			depRepo.save(d);
		}catch(Exception ex) {
			System.out.println("Error al guardar producto: " + ex.getMessage());
		}
		return d;
	}

	@Override
	public List<Departamento> getAll() {
		List<Departamento> result = new ArrayList<Departamento>();
		try {
			result = depRepo.findAll();
		}catch(Exception ex) {
			System.out.println("[getAllLibros] exception:" + ex.getMessage());
		}
		return result;
	}

	@Override
	public List<Departamento> getByFilter(Departamento d) {
		List<Departamento> result = new ArrayList<Departamento>();
		try {
			if(d != null) {
				Example<Departamento> exmple = Example.of(d);
				result = depRepo.findAll(exmple);
				
			}else {
				result = depRepo.findAll();
			}
			
		}catch(Exception ex) {
			System.out.println("[getByFilter] exception:" + ex.getMessage());
		}
		return result;
	}

	@Override
	public void deleteById(Integer id) {
		try {
			depRepo.deleteById(id);
		}catch(Exception ex) {
			System.out.println("[deleteById] exception:" + ex.getMessage());
		}
		
	}
	
	@Override
    public Departamento getById(Integer id) {
        Optional < Departamento > optional = depRepo.findById(id);
        Departamento dep = null;
        if (optional.isPresent()) {
            dep = optional.get();
        } else {
            throw new RuntimeException(" Departamento no encontrado con id :: " + id);
        }
        return dep;
    }
	
	

}
