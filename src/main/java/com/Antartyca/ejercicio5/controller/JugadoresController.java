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

import com.Antartyca.ejercicio5.model.Equipos;
import com.Antartyca.ejercicio5.model.Jugadores;
import com.Antartyca.ejercicio5.services.EquiposService;
import com.Antartyca.ejercicio5.services.JugadoresService;

@Controller
@RequestMapping(value="/jugadores")
public class JugadoresController {
	
	@Autowired
	public JugadoresService jugadoresService;
	
	@Autowired
	public EquiposService equipoService;
	
	
	
	
	
	
	@RequestMapping(method = RequestMethod.POST, value= "/save" )
	@ResponseBody
	public Jugadores guardaJugadores(@RequestBody Jugadores jugador) {
		
		return jugadoresService.guardaJugadores(jugador);
		
	}
	
	@GetMapping(value= "/jugadoresActivos/{activo}")
	@ResponseBody
	public List<Jugadores> jugadoresActivos(@PathVariable(value = "activo") boolean activo) {
		List<Jugadores> lista =new ArrayList<Jugadores>();
		lista = jugadoresService.seleccionarJugadorActivo(true);
		return lista;
		
	}
	
	@GetMapping(value= "/seleccionarGolTar/{numGoles}/{tarjetas}")
	@ResponseBody
	public List<Jugadores> jugadoresGolTar(@PathVariable(value = "numGoles") int numGoles, @PathVariable(value = "tarjetas") int tarjetas) {
		List<Jugadores> lista =new ArrayList<Jugadores>();
		lista = jugadoresService.seleccionarGolTar(numGoles, tarjetas);
		return lista;
		
	}
	
	@GetMapping(value= "/seleccionarGolPos/{numGoles}/{puesto}")
	@ResponseBody
	public List<Jugadores> jugadoresGolPos(@PathVariable(value = "numGoles") int numGoles, @PathVariable(value = "puesto") String puesto) {
		List<Jugadores> lista =new ArrayList<Jugadores>();
		lista = jugadoresService.seleccionarGolPos(numGoles, puesto);
		return lista;
		
	}

	@GetMapping(value= "/seleccionarAltura/{altura}")
	@ResponseBody
	public List<Jugadores> jugadoresAltura(@PathVariable(value = "altura") double altura){
		List<Jugadores> lista =new ArrayList<Jugadores>();
		lista = jugadoresService.seleccionarAltura(altura);
		return lista;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value= "/getAll" )
	@ResponseBody
	public List<Jugadores> getAllJugadores() {
		
		List<Jugadores> result= new ArrayList<Jugadores>();
		result= jugadoresService.getAllJugadores();
		return result;
		
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/getByFilter")
	@ResponseBody
    public List<Jugadores> getJugadoresByFilter(@RequestBody Jugadores filter){
        List<Jugadores> result = new ArrayList<Jugadores>();
        try {
            result = jugadoresService.getByFilter(filter);
        } catch (Exception e) {
            System.out.println("getJugadores exception: "+e.getLocalizedMessage());
        }

        return result;
	}
	
	@GetMapping( value= "/delete/{idJugadores}" )
	@ResponseBody
	public ModelAndView borraJugadorId(@ModelAttribute @PathVariable Integer idJugadores) {
		ModelMap model = new ModelMap();
		jugadoresService.borraJugadorId(idJugadores);
		return new ModelAndView(
			       new RedirectView("/jugadores/listar", true),
			       model
			    );
	}
	
	@RequestMapping(method = RequestMethod.GET, value= "/listar" )
	public String listar(Model model) {
		List<Jugadores> jugadores = jugadoresService.getAllJugadores();
		model.addAttribute("jugadores",jugadores);
		List<Equipos> equipos =equipoService.getAllEquipos();
		model.addAttribute("equipos",equipos);
		model.addAttribute("jugador",new Jugadores());
		return "jugadores";
	}
	
	

	@PostMapping(value = "/agregar")
	public String agregarJugador(Jugadores jugador ,Model model) {
		jugadoresService.guardaJugadores(jugador);
		return "redirect:/jugadores/listar";
	}
	
	
	 @GetMapping("/showFormForUpdate/{idJugadores}")
	    public String showFormForUpdate(@PathVariable(value = "idJugadores") Integer idJugadores, Model model) {

	        Jugadores jugador= jugadoresService.getById(idJugadores);

	        List<Equipos> equipos =equipoService.getAllEquipos();
			model.addAttribute("equipos",equipos);
	        model.addAttribute("jugador", jugador);
	        return "editar_jugador";
	    }
	 
	 @GetMapping("/getById/{idJugadores}")
	 @ResponseBody
	    public Jugadores getById(@PathVariable(value = "idJugadores") Integer idJugadores) {

	        Jugadores jugador= jugadoresService.getById(idJugadores);
	        return jugador;
	 }

}
