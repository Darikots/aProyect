package com.Antartyca.ejercicio5.servicesImplement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.Antartyca.ejercicio5.model.Empleado;
import com.Antartyca.ejercicio5.repository.EmpleadoRepository;
import com.Antartyca.ejercicio5.services.EmpleadoService;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{
	
	@Autowired
	EmpleadoRepository empRepo;

	@Override
	public Empleado guardaEmpleado(Empleado e) {
		try {
			empRepo.save(e);
		}catch(Exception ex) {
			System.out.println("Error al guardar producto: " + ex.getMessage());
		}
		return e;
	}

	@Override
	public List<Empleado> getAll() {
		List<Empleado> result = new ArrayList<Empleado>();
		try {
			result = empRepo.findAll();
		}catch(Exception ex) {
			System.out.println("[getAllLibros] exception:" + ex.getMessage());
		}
		return result;
	}

	@Override
	public List<Empleado> getByFilter(Empleado e) {
		List<Empleado> result = new ArrayList<Empleado>();
		try {
			if(e != null) {
				Example<Empleado> exmple = Example.of(e);
				result = empRepo.findAll(exmple);
				
			}else {
				result = empRepo.findAll();
			}
			
		}catch(Exception ex) {
			System.out.println("[getByFilter] exception:" + ex.getMessage());
		}
		return result;
	}

	@Override
	public void deleteById(Integer id) {
		try {
			empRepo.deleteById(id);
		}catch(Exception ex) {
			System.out.println("[deleteById] exception:" + ex.getMessage());
		}
		
	}
	
	@Override
    public Empleado getById(Integer id) {
        Optional < Empleado > optional = empRepo.findById(id);
        Empleado emp = null;
        if (optional.isPresent()) {
            emp = optional.get();
        } else {
            throw new RuntimeException(" Empleado no encontrado con id :: " + id);
        }
        return emp;
    }

}
