package com.Antartyca.ejercicio5.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Component
@Table(name = "Federacion")
public class Federacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idFederacion;
	
	private String CIF;
	
	private String direccionFederacion;
	
	private String telefono;
	
	@OneToMany(mappedBy = "federacion", cascade = {CascadeType.ALL})
	@JsonIgnoreProperties({"federacion","torneos"})
	private List<Departamento> departamentos;
	
	@OneToMany(mappedBy = "federacion", cascade = {CascadeType.ALL})
	@JsonIgnoreProperties("federacion")
	private List<Torneos> torneos;

	public Integer getIdFederacion() {
		return idFederacion;
	}

	public void setIdFederacion(Integer idFederacion) {
		this.idFederacion = idFederacion;
	}

	public String getCIF() {
		return CIF;
	}

	public void setCIF(String cIF) {
		CIF = cIF;
	}

	public String getDireccionFederacion() {
		return direccionFederacion;
	}

	public void setDireccionFederacion(String direccionFederacion) {
		this.direccionFederacion = direccionFederacion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

	public List<Torneos> getTorneos() {
		return torneos;
	}

	public void setTorneos(List<Torneos> torneos) {
		this.torneos = torneos;
	}
	
	

}
