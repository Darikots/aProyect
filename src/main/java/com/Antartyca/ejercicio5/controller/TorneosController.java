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
import com.Antartyca.ejercicio5.model.Equipos;
import com.Antartyca.ejercicio5.model.Federacion;
import com.Antartyca.ejercicio5.model.Torneos;
import com.Antartyca.ejercicio5.services.CiudadTorneosService;
import com.Antartyca.ejercicio5.services.EquiposService;
import com.Antartyca.ejercicio5.services.FederacionService;
import com.Antartyca.ejercicio5.services.TorneosService;

@Controller
@RequestMapping(value="/torneos")
public class TorneosController {
	
	@Autowired
	public TorneosService torneosService;
	
	@Autowired
	public CiudadTorneosService ciudadTorneosService;
	
	@Autowired
	public EquiposService equipoService;
	
	@Autowired
	public FederacionService federacionService;
	
	@RequestMapping(method = RequestMethod.POST, value= "/save" )
	@ResponseBody
	public Torneos guardaTorneos(@RequestBody Torneos torneo) {
		
		return torneosService.guardaTorneos(torneo);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value= "/getAll" )
	@ResponseBody
	public List<Torneos> getAllTorneos() {
		
		List<Torneos> result= new ArrayList<Torneos>();
		result= torneosService.getAllTorneos();
		return result;
		
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/getByFilter")
	@ResponseBody
    public List<Torneos> getTorneosByFilter(@RequestBody Torneos filter){
        List<Torneos> result = new ArrayList<Torneos>();
        try {
            result = torneosService.getByFilter(filter);
        } catch (Exception e) {
            System.out.println("getTorneos exception: "+e.getLocalizedMessage());
        }

        return result;
	}
	
	@GetMapping( value= "/delete/{idTorneo}" )
	@ResponseBody
	public ModelAndView borraTorneoId(@ModelAttribute @PathVariable Integer idTorneo) {
		ModelMap model = new ModelMap();
		torneosService.borraTorneoId(idTorneo);
		return new ModelAndView(
			       new RedirectView("/torneos/listar", true),
			       model
			    );
	}
	
	@RequestMapping(method = RequestMethod.GET, value= "/listar" )
	public String listar(Model model) {
		List<Torneos> torneo = torneosService.getAllTorneos();
		model.addAttribute("torneos",torneo);
		List<CiudadTorneos>ciudad=ciudadTorneosService.getAll();
		model.addAttribute("ciudad",ciudad);
		List<Equipos> equipos =equipoService.getAllEquipos();
		model.addAttribute("equipos",equipos);
		List<Federacion> federacion =federacionService.getAllFederacion();
		model.addAttribute("federacion",federacion);
		model.addAttribute("torneo",new Torneos());
		return "torneos";
	}
	
	@PostMapping(value = "/agregar")
	public String agregarTorneo(@ModelAttribute Torneos torneo) {
		torneosService.guardaTorneos(torneo);
		return "redirect:/torneos/listar";
	}
	
	 @GetMapping("/showFormForUpdate/{idTorneo}")
	    public String showFormForUpdate(@PathVariable(value = "idTorneo") Integer idTorneo, Model model) {

	        Torneos torneo= torneosService.getById(idTorneo);
	        
	        List<CiudadTorneos>ciudad=ciudadTorneosService.getAll();
			model.addAttribute("ciudad",ciudad);

	        List<Equipos> equipos =equipoService.getAllEquipos();
			model.addAttribute("equipos",equipos);
			List<Federacion> federacion =federacionService.getAllFederacion();
			model.addAttribute("federacion",federacion);
	        model.addAttribute("torneos", torneo);
	        return "editar_torneo";
	    }


}
