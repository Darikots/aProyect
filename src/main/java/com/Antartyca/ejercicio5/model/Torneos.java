package com.Antartyca.ejercicio5.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Component
@Table(name = "Torneos")
public class Torneos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTorneo;
	
	
	private String nombreTorneo;
	
	private String descripcionTorneo;
	
	@ManyToOne
	@JoinColumn(name="codigoCiudad")
	@JsonIgnoreProperties("torneos")
	private CiudadTorneos ciudad;
	
	@ManyToOne
	@JoinColumn(name="idFederacion")
	@JsonIgnoreProperties("torneos")
	private Federacion federacion;
	
	
	@ManyToMany
	@JoinTable(
	        name="torneos_equipos",
	        joinColumns=@JoinColumn(name="idTorneo"),
	        inverseJoinColumns=@JoinColumn(name="idEquipo")
	    )
	@JsonIgnoreProperties("torneos")
	
	private List<Equipos> equipos;

	public List<Equipos> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<Equipos> equipos) {
		this.equipos = equipos;
	}

	public Integer getIdTorneo() {
		return idTorneo;
	}

	public void setIdTorneo(Integer idTorneo) {
		this.idTorneo = idTorneo;
	}

	public String getNombreTorneo() {
		return nombreTorneo;
	}

	public void setNombreTorneo(String nombreTorneo) {
		this.nombreTorneo = nombreTorneo;
	}

	public String getDescripcionTorneo() {
		return descripcionTorneo;
	}

	public void setDescripcionTorneo(String descripcionTorneo) {
		this.descripcionTorneo = descripcionTorneo;
	}

	public CiudadTorneos getCiudad() {
		return ciudad;
	}

	public void setCiudad(CiudadTorneos ciudad) {
		this.ciudad = ciudad;
	}

	public Federacion getFederacion() {
		return federacion;
	}

	public void setFederacion(Federacion federacion) {
		this.federacion = federacion;
	}
	
}
