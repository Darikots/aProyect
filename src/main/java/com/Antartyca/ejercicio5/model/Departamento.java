package com.Antartyca.ejercicio5.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Component
public class Departamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigoDepartamento;
	
	@Column
	private String nombreDepartamento;
	
	@Column String Descripcion;
	
	@OneToMany(mappedBy = "departamento", cascade = {CascadeType.ALL})
	@JsonIgnoreProperties("departamento")
	private List<Empleado> empleados;
	
	
	@ManyToOne
	@JoinColumn(name="idFederacion")
	@JsonIgnoreProperties("departamentos")
	private Federacion federacion;
	
	@ManyToOne
	@JoinColumn(name="codigoCiudad")
	@JsonIgnoreProperties("departamento")
	private CiudadDepartamento ciudad;
	

	public CiudadDepartamento getCiudad() {
		return ciudad;
	}

	public void setCiudad(CiudadDepartamento ciudad) {
		this.ciudad = ciudad;
	}

	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public Federacion getFederacion() {
		return federacion;
	}

	public void setFederacion(Federacion federacion) {
		this.federacion = federacion;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	public Integer getCodigoDepartamento() {
		return codigoDepartamento;
	}

	public void setCodigoDepartamento(Integer codigoDepartamento) {
		this.codigoDepartamento = codigoDepartamento;
	}

	public String getNombreDepartamento() {
		return nombreDepartamento;
	}

	public void setNombreDepartamento(String nombreDepartamento) {
		this.nombreDepartamento = nombreDepartamento;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}


}
