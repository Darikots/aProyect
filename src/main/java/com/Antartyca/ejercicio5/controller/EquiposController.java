package com.Antartyca.ejercicio5.controller;

import java.sql.Date;

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

import com.Antartyca.ejercicio5.model.Equipos;
import com.Antartyca.ejercicio5.services.EquiposService;


@Controller
@RequestMapping(value="/equipos")
public class EquiposController {
	
	@Autowired
	private EquiposService equipoService;
	
	
	@RequestMapping(method = RequestMethod.POST, value= "/save" )
	@ResponseBody
	public Equipos guardaEqipos(@RequestBody Equipos equipo) {
		
		return equipoService.guardaEquipos(equipo);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value= "/getAll" )
	@ResponseBody
	public List<Equipos> getAllEquipos() {
		
		List<Equipos> result= new ArrayList<Equipos>();
		result= equipoService.getAllEquipos();
		return result;
		
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/getByFilter")
	@ResponseBody
    public List<Equipos> getEquiposByFilter(@RequestBody Equipos filter){
        List<Equipos> result = new ArrayList<Equipos>();
        try {
            result = equipoService.getByFilter(filter);
        } catch (Exception e) {
            System.out.println("getEquipos exception: "+e.getLocalizedMessage());
        }

        return result;
	}
	
	@GetMapping( value= "/delete/{idEquipo}" )
	@ResponseBody
	public ModelAndView borraEquipoId(@ModelAttribute @PathVariable Integer idEquipo) {
		ModelMap model = new ModelMap();
		equipoService.borraEquipoId(idEquipo);
		return new ModelAndView(
			       new RedirectView("/equipos/listar", true),
			       model
			    );
	}
	
	@RequestMapping(method = RequestMethod.GET, value= "/listar" )
	public String listar(Model model) {
		List<Equipos> equipo = equipoService.getAllEquipos();
		model.addAttribute("equipos",equipo);
		
		model.addAttribute("equipo",new Equipos());
		return "equipos";
	}
	
	@PostMapping(value = "/agregar")
	public String agregarEquipo(@ModelAttribute Equipos equipo) {
		equipoService.guardaEquipos(equipo);
		return "redirect:/equipos/listar";
	}
	 @GetMapping("/showFormForUpdate/{idEquipo}")
	    public String showFormForUpdate(@PathVariable(value = "idEquipo") Integer idEquipo, Model model) {

	        Equipos equipo= equipoService.getById(idEquipo);
	        model.addAttribute("equipo", equipo);
	        return "editar_equipo";
	    }
	 @GetMapping(value= "/seleccionarAnioFun/{fechaFundacion}")
		@ResponseBody
		public List<Equipos> seleccionarAnioFun(@PathVariable(value = "fechaFundacion") Date fechaFundacion) {
			List<Equipos> lista = new ArrayList<Equipos>();
			lista = equipoService.seleccionarAnioFun(fechaFundacion);
			return lista;

		}



}
