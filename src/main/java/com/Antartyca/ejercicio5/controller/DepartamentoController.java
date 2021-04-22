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

import com.Antartyca.ejercicio5.model.CiudadDepartamento;
import com.Antartyca.ejercicio5.model.Departamento;
import com.Antartyca.ejercicio5.model.Federacion;
import com.Antartyca.ejercicio5.services.CiudadDepartamentoService;
import com.Antartyca.ejercicio5.services.DepartamentoService;
import com.Antartyca.ejercicio5.services.FederacionService;

@Controller
@RequestMapping (value = "/departamento")
public class DepartamentoController {
	
	@Autowired
	private DepartamentoService depServ;
	
	@Autowired 
	private CiudadDepartamentoService ciudadServ;
	
	@Autowired 
	private FederacionService fedServ;
	
	@PostMapping(value = "/save")
	@ResponseBody
	public Departamento guardaCiudad(@RequestBody Departamento d) {
		return depServ.guardaDepartamento(d);
	}
	
	@GetMapping(value = "/getAll")
	@ResponseBody
	public List<Departamento> getAll(){
		List<Departamento> result = new ArrayList<Departamento>();
		
		result = depServ.getAll();
		return result;
	}
	
	@PostMapping(value = "/getByFilter")
	@ResponseBody
	public List<Departamento> getCiudadByFilter(@RequestBody Departamento d){
		List<Departamento> result = new ArrayList<Departamento>();
		try {
			result = depServ.getByFilter(d);
		}catch (Exception e) {
			System.out.println("getCiudad exception: "+e.getLocalizedMessage());
		}
		
		return result;
	}
	
	@GetMapping(value = "/borrar/{id}")
	@ResponseBody
	public void borrarDepartamento(@PathVariable Integer id) {
		depServ.deleteById(id);
	}
	
	@GetMapping("/listar")
	public String listar(Model model) {
		List<Departamento>departamentos= depServ.getAll();
		model.addAttribute("departamentos",departamentos);
		List<CiudadDepartamento>ciudades=ciudadServ.getAll();
		model.addAttribute("ciudades",ciudades);
		List<Federacion>federacion=fedServ.getAllFederacion();
		model.addAttribute("federacion",federacion);
		model.addAttribute("departamento",new Departamento());
		return "departamentos";
	}
	
	@PostMapping(value = "/agregar")
	public String agregarDepartamento(@ModelAttribute Departamento d) {
		depServ.guardaDepartamento(d);
		return "redirect:/departamento/listar";
	}
	
	@GetMapping(value = "/delete/{id}")
	public String borrarDepartamento(@PathVariable("id") Integer id, Model model) {
		depServ.deleteById(id);
		return "redirect:/departamento/listar";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") Integer id, Model model) {

        Departamento departamento= depServ.getById(id);

        List<CiudadDepartamento>ciudades=ciudadServ.getAll();
		model.addAttribute("ciudades",ciudades);
		List<Federacion>federacion=fedServ.getAllFederacion();
		model.addAttribute("federacion",federacion);
        model.addAttribute("departamento", departamento);
        return "editar_departamento";
    }

}
