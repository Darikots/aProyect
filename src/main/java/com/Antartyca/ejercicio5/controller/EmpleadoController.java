package com.Antartyca.ejercicio5.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Antartyca.ejercicio5.model.Departamento;
import com.Antartyca.ejercicio5.model.Empleado;
import com.Antartyca.ejercicio5.services.DepartamentoService;
import com.Antartyca.ejercicio5.services.EmpleadoService;

@Controller
@RequestMapping (value = "/empleado")
public class EmpleadoController {
	
	@Autowired 
	private EmpleadoService empServ;
	
	@Autowired
	private DepartamentoService depServ;
	
	@PostMapping(value = "/save")
	@ResponseBody
	public Empleado guardaCiudad(@RequestBody Empleado e) {
		return empServ.guardaEmpleado(e);
	}
	
	@GetMapping(value = "/getAll")
	@ResponseBody
	public List<Empleado> getAll(){
		List<Empleado> result = new ArrayList<Empleado>();
		
		result = empServ.getAll();
		return result;
	}
	
	@PostMapping(value = "/getByFilter")
	@ResponseBody
	public List<Empleado> getCiudadByFilter(@RequestBody Empleado e){
		List<Empleado> result = new ArrayList<Empleado>();
		try {
			result = empServ.getByFilter(e);
		}catch (Exception j) {
			System.out.println("getCiudad exception: "+j.getLocalizedMessage());
		}
		
		return result;
	}
	
	@GetMapping(value = "/borrar/{id}")
	@ResponseBody
	public void borrarCiudad(@PathVariable Integer id) {
		empServ.deleteById(id);
	}
	
	@GetMapping("/listar")
	public String listar(Model model) {
		List<Empleado>empleados= empServ.getAll();
		model.addAttribute("empleados",empleados);
		List<Departamento>departamentos=depServ.getAll();
		model.addAttribute("departamentos",departamentos);
		model.addAttribute("empleado",new Empleado());
		return "empleados";
	}
	
	@PostMapping(value = "/agregar")
	public String agregarEmpleado(@ModelAttribute Empleado e) {
		empServ.guardaEmpleado(e);
		return "redirect:/empleado/listar";
	}
	
	@GetMapping(value = "/delete/{id}")
	public String borrarEmpleado(@PathVariable("id") Integer id, Model model) {
		empServ.deleteById(id);
		return "redirect:/empleado/listar";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") Integer id, Model model) {

        Empleado empleado= empServ.getById(id);

        List<Departamento> departamentos =depServ.getAll();
		model.addAttribute("departamentos",departamentos);
        model.addAttribute("empleado", empleado);
        return "editar_empleado";
    }

}
