package co.edu.unbosque.netflixbackend.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;


public class UsuarioPelicula {
	private int idUsuarioPelicula;
	private LocalDateTime fechaVisualizacion;
	private int idPelicula;
	private int idUsuario;
	
	public UsuarioPelicula() {
		// TODO Auto-generated constructor stub
	}

	public UsuarioPelicula(LocalDateTime fechaVisualizacion, int idPelicula, int idUsuario) {
		super();
		this.fechaVisualizacion = fechaVisualizacion;
		this.idPelicula = idPelicula;
		this.idUsuario = idUsuario;
	}

	public int getIdUsuarioPelicula() {
		return idUsuarioPelicula;
	}

	public void setIdUsuarioPelicula(int idUsuarioPelicula) {
		this.idUsuarioPelicula = idUsuarioPelicula;
	}

	public LocalDateTime getFechaVisualizacion() {
		return fechaVisualizacion;
	}

	public void setFechaVisualizacion(LocalDateTime fechaVisualizacion) {
		this.fechaVisualizacion = fechaVisualizacion;
	}

	public int getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(int idPelicula) {
		this.idPelicula = idPelicula;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Override
	public String toString() {
		return "UsuarioPelicula [idUsuarioPelicula=" + idUsuarioPelicula + ", fechaVisualizacion=" + fechaVisualizacion
				+ ", idPelicula=" + idPelicula + ", idUsuario=" + idUsuario + "]";
	}
	

}
