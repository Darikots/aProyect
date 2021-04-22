package com.Antartyca.ejercicio5.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.Antartyca.ejercicio5.model.CiudadTorneos;
import com.Antartyca.ejercicio5.model.Torneos;
import com.Antartyca.ejercicio5.services.CiudadTorneosService;
import com.Antartyca.ejercicio5.services.TorneosService;

@Controller
@RequestMapping (value = "/ciudadTor")
public class CiudadTorneosController {
	
	@Autowired
	private CiudadTorneosService ciudadServ;
	
	@Autowired
	private TorneosService torneosService;
	
	@PostMapping(value = "/save" )
	@ResponseBody
	public CiudadTorneos guardaCiudad(@RequestBody CiudadTorneos c) {
		return ciudadServ.guardaCiudad(c);
	}
	
	@GetMapping(value = "/getAll")
	@ResponseBody
	public List<CiudadTorneos> getAll(){
		List<CiudadTorneos> result = new ArrayList<CiudadTorneos>();
		
		result = ciudadServ.getAll();
		return result;
	}
	
	@PostMapping(value = "/getByFilter")
	@ResponseBody
	public List<CiudadTorneos> getCiudadByFilter(@RequestBody CiudadTorneos c){
		List<CiudadTorneos> result = new ArrayList<CiudadTorneos>();
		try {
			result = ciudadServ.getByFilter(c);
		}catch (Exception e) {
			System.out.println("getCiudad exception: "+e.getLocalizedMessage());
		}
		
		return result;
	}
	
	@GetMapping( value= "/delete/{codigoCiudad}" )
	@ResponseBody
	public ModelAndView borraCiudadId(@ModelAttribute @PathVariable Integer codigoCiudad) {
		ModelMap model = new ModelMap();
		ciudadServ.deleteById(codigoCiudad);
		return new ModelAndView(
			       new RedirectView("/ciudadTor/listar", true),
			       model
			    );
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value= "/listar" )
	public String listar(Model model) {
		List<CiudadTorneos> ciudadesTorneo = ciudadServ.getAll();
		model.addAttribute("ciudadTorneo",ciudadesTorneo);
		List<Torneos> torneos =torneosService.getAllTorneos();
		model.addAttribute("torneos",torneos);
		model.addAttribute("ciudadesTorneo",new CiudadTorneos());
		return "CiudadTorneos";
	}
	
	@PostMapping(value = "/agregar")
	public String agregarCiudad(@ModelAttribute CiudadTorneos ciudadTorneo) {
		ciudadServ.guardaCiudad(ciudadTorneo);
		return "redirect:/ciudadTor/listar";
	}
	
	@GetMapping("/showFormForUpdate/{codigoCiudad}")
    public String showFormForUpdate(@PathVariable(value = "codigoCiudad") Integer codigoCiudad, Model model) {

		CiudadTorneos ciudadesTorneo= ciudadServ.getById(codigoCiudad);
        model.addAttribute("ciudadTorneo",ciudadesTorneo);
        return "editar_ciudadTorneo";
    }


}
