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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Antartyca.ejercicio5.model.Federacion;
import com.Antartyca.ejercicio5.services.FederacionService;

@Controller
@RequestMapping(value="/federacion")
public class FederacionController {
	
	@Autowired
	public FederacionService federacionService;
	
	@RequestMapping(method = RequestMethod.POST, value= "/save" )
	@ResponseBody
	public Federacion guardaFederacion(@RequestBody Federacion federacion) {
		
		return federacionService.guardaFederacion(federacion);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value= "/getAll" )
	@ResponseBody
	public List<Federacion> getAllFederacion() {
		
		List<Federacion> result= new ArrayList<Federacion>();
		result= federacionService.getAllFederacion();
		return result;
		
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/getByFilter")
	@ResponseBody
    public List<Federacion> getFederacionByFilter(@RequestBody Federacion filter){
        List<Federacion> result = new ArrayList<Federacion>();
        try {
            result = federacionService.getByFilter(filter);
        } catch (Exception e) {
            System.out.println("getFederacion exception: "+e.getLocalizedMessage());
        }

        return result;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value= "/borrar/{idFederacion}" )
	@ResponseBody
	public void borraFederacionId(@PathVariable Integer idFederacion) {
		
		federacionService.borraFederacionId(idFederacion);
		System.out.println("Borrado la Federacion con id: "+idFederacion);
	}
	
	@GetMapping("/listar")
	public String listar(Model model) {
		List<Federacion>federacion= federacionService.getAllFederacion();
		model.addAttribute("federacion",federacion);
		model.addAttribute("feder",new Federacion());
		return "federacion";
	}
	
	@PostMapping(value = "/agregar")
	public String agregarFederacion(@ModelAttribute Federacion f) {
		federacionService.guardaFederacion(f);
		return "redirect:/federacion/listar";
	}
	
	@GetMapping(value = "/delete/{id}")
	public String borrarFederacion(@PathVariable("id") Integer id, Model model) {
		federacionService.borraFederacionId(id);
		return "redirect:/federacion/listar";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") Integer id, Model model) {

        Federacion federacion= federacionService.getById(id);

        model.addAttribute("federacion", federacion);
        return "editar_federacion";
    }


}
