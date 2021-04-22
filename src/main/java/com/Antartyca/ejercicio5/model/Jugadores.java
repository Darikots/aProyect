package com.Antartyca.ejercicio5.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

@Entity
@Component
@Table(name = "Jugadores")
public class Jugadores {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idJugadores;
	
	@NotNull
	private String dni;
	
	
	private String nombreJugador;
	private int telefono;	
	private double altura;
	private int numGoles;
	private boolean activo;
	private int tarjetas;
	private String puesto;
	
	@ManyToOne
	@JoinColumn(name="idEquipo")
	@JsonIgnoreProperties("jugadores")
	private Equipos equipo;

	public Equipos getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipos equipo) {
		this.equipo = equipo;
	}

	public Integer getIdJugadores() {
		return idJugadores;
	}

	public void setIdJugadores(Integer idJugadores) {
		this.idJugadores = idJugadores;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombreJugador() {
		return nombreJugador;
	}

	public void setNombreJugador(String nombreJugador) {
		this.nombreJugador = nombreJugador;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public int getNumGoles() {
		return numGoles;
	}

	public void setNumGoles(int numGoles) {
		this.numGoles = numGoles;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public int getTarjetas() {
		return tarjetas;
	}

	public void setTarjetas(int tarjetas) {
		this.tarjetas = tarjetas;
	}
	
	

}
