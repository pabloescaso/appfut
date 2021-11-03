package com.pmaresc.app.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "partidos")
public class Partido implements Serializable {
	
	private static final long serialVersionUID = -9217350626575075226L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPartido;
	
	private String nombre;
	private String homeClub;
	private String awayClub;
	private String descripcion;
	private String resultado;
	private Date fecha;
	
	public Partido(String nombre, String homeClub, String awayClub, String descripcion, String resultado, Date fecha) {
		super();
		this.nombre = nombre;
		this.homeClub = homeClub;
		this.awayClub = awayClub;
		this.descripcion = descripcion;
		this.resultado = resultado;
		this.fecha = fecha;
	}

	public Partido(Long idPartido, String nombre, String homeClub, String awayClub, String descripcion,
			String resultado,Date fecha) {
		super();
		this.idPartido = idPartido;
		this.nombre = nombre;
		this.homeClub = homeClub;
		this.awayClub = awayClub;
		this.descripcion = descripcion;
		this.resultado = resultado;
		this.fecha = fecha;
	}

	public Partido() {
		super();
	}

	public Long getIdPartido() {
		return idPartido;
	}

	public void setIdPartido(Long idPartido) {
		this.idPartido = idPartido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getHomeClub() {
		return homeClub;
	}

	public void setHomeClub(String homeClub) {
		this.homeClub = homeClub;
	}

	public String getAwayClub() {
		return awayClub;
	}

	public void setAwayClub(String awayClub) {
		this.awayClub = awayClub;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
	
	
	
	
	
}
