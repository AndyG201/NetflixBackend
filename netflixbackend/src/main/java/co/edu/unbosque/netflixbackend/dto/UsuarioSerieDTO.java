package co.edu.unbosque.netflixbackend.dto;

import java.time.LocalDateTime;

public class UsuarioSerieDTO {
	private int idUsuarioPelicula;
	private LocalDateTime fechaVisualizacion;
	private int idSerie;
	private int idUsuario;
	
	public UsuarioSerieDTO(int idUsuarioPelicula, LocalDateTime fechaVisualizacion, int idSerie, int idUsuario) {
		this.idUsuarioPelicula = idUsuarioPelicula;
		this.fechaVisualizacion = fechaVisualizacion;
		this.idSerie = idSerie;
		this.idUsuario = idUsuario;
	}

	public UsuarioSerieDTO() {
		// TODO Auto-generated constructor stub
	}

	public UsuarioSerieDTO(LocalDateTime fechaVisualizacion, int idSerie, int idUsuario) {
		super();
		this.fechaVisualizacion = fechaVisualizacion;
		this.idSerie = idSerie;
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

	public int getIdSerie() {
		return idSerie;
	}

	public void setIdSerie(int idSerie) {
		this.idSerie = idSerie;
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
				+ ", idSerie=" + idSerie + ", idUsuario=" + idUsuario + "]";
	}
	

}
