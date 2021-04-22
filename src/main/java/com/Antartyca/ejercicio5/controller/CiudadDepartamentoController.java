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
import com.Antartyca.ejercicio5.services.CiudadDepartamentoService;

@Controller
@RequestMapping (value = "/ciudadDep")
public class CiudadDepartamentoController {
	
	@Autowired
	private CiudadDepartamentoService ciudadServ;
	
	@PostMapping(value = "/save")
	@ResponseBody
	public CiudadDepartamento guardaCiudad(@RequestBody CiudadDepartamento c) {
		return ciudadServ.guardaCiudad(c);
	}
	
	@GetMapping(value = "/getAll")
	@ResponseBody
	public List<CiudadDepartamento> getAll(){
		List<CiudadDepartamento> result = new ArrayList<CiudadDepartamento>();
		
		result = ciudadServ.getAll();
		return result;
	}
	
	@PostMapping(value = "/getByFilter")
	@ResponseBody
	public List<CiudadDepartamento> getCiudadByFilter(@RequestBody CiudadDepartamento c){
		List<CiudadDepartamento> result = new ArrayList<CiudadDepartamento>();
		try {
			result = ciudadServ.getByFilter(c);
		}catch (Exception e) {
			System.out.println("getCiudad exception: "+e.getLocalizedMessage());
		}
		
		return result;
	}
	
	@GetMapping(value = "/borrar/{id}")
	@ResponseBody
	public void borrarCiudad(@PathVariable Integer id) {
		ciudadServ.deleteById(id);
	}
	
	@GetMapping("/listar")
	public String listar(Model model) {
		List<CiudadDepartamento>ciudadDep= ciudadServ.getAll();
		model.addAttribute("ciudadDep",ciudadDep);
		model.addAttribute("ciudad",new CiudadDepartamento());
		return "ciudadDep";
	}
	
	@PostMapping(value = "/agregar")
	public String agregarCiudad(@ModelAttribute CiudadDepartamento c) {
		ciudadServ.guardaCiudad(c);
		return "redirect:/ciudadDep/listar";
	}
	
	@GetMapping(value = "/delete/{id}")
	public String borrarCiudad(@PathVariable("id") Integer id, Model model) {
		ciudadServ.deleteById(id);
		return "redirect:/ciudadDep/listar";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") Integer id, Model model) {

        CiudadDepartamento ciudad= ciudadServ.getById(id);

        model.addAttribute("ciudad", ciudad);
        return "editar_ciudadDep";
    }
}
