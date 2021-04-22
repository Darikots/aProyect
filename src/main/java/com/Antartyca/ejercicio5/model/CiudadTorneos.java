package com.Antartyca.ejercicio5.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Component
public class CiudadTorneos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigoCiudad;
	
	@Column
	private String nombreCiudad;
	
	
	@OneToMany(mappedBy = "ciudad", cascade = {CascadeType.ALL})
	@JsonIgnoreProperties("ciudad")
	private List<Torneos> torneos;

	
	
	

	public List<Torneos> getTorneos() {
		return torneos;
	}

	public void setTorneos(List<Torneos> torneos) {
		this.torneos = torneos;
	}


	public Integer getCodigoCiudad() {
		return codigoCiudad;
	}

	public void setCodigoCiudad(Integer codigoCiudad) {
		this.codigoCiudad = codigoCiudad;
	}

	public String getNombreCiudad() {
		return nombreCiudad;
	}

	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}

}
