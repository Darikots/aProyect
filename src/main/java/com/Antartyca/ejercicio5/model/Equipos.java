package com.Antartyca.ejercicio5.model;



import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;


@Entity
@Component
@Table(name = "Equipos")
public class Equipos{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEquipo;
	
	@NotNull
	private String nombreEquipo;
	
	
	private String direccionEquipo;
	

	private Date fechaFundacion;
	

	@OneToMany(mappedBy = "equipo", cascade= {CascadeType.ALL})
	@JsonIgnoreProperties("equipo")
	private List<Jugadores> jugadores;


	
	@ManyToMany(mappedBy="equipos",cascade = {CascadeType.ALL})
	@JsonIgnoreProperties("equipos")
	private Set<Torneos> torneos;



	public Set<Torneos> getTorneos() {
		return torneos;
	}

	public void setTorneos(Set<Torneos> torneos) {
		this.torneos = torneos;
	}

	public List<Jugadores> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<Jugadores> jugadores) {
		this.jugadores = jugadores;
	}

	public Integer getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(Integer idEquipo) {
		this.idEquipo = idEquipo;
	}

	public String getNombreEquipo() {
		
			return nombreEquipo;
		
	}

	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}

	public String getDireccionEquipo() {
		return direccionEquipo;
	}

	public void setDireccionEquipo(String direccionEquipo) {
		this.direccionEquipo = direccionEquipo;
	}

	public Date getFechaFundacion() {
		return fechaFundacion;
	}

	public void setFechaFundacion(Date fechaFundacion) {
		this.fechaFundacion = fechaFundacion;
	}
	
	

}
