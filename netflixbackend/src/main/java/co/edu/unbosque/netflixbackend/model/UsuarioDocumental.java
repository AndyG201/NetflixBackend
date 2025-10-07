package co.edu.unbosque.netflixbackend.model;

import java.time.LocalDateTime;

public class UsuarioDocumental {
	private int idUsuarioPelicula;
	private LocalDateTime fechaVisualizacion;
	private int idDocumental;
	private int idUsuario;
	
	public UsuarioDocumental() {
		// TODO Auto-generated constructor stub
	}

	public UsuarioDocumental(LocalDateTime fechaVisualizacion, int idDocumental, int idUsuario) {
		super();
		this.fechaVisualizacion = fechaVisualizacion;
		this.idDocumental = idDocumental;
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

	public int getIdDocumental() {
		return idDocumental;
	}

	public void setIdDocumental(int idDocumental) {
		this.idDocumental = idDocumental;
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
				+ ", idDocumental=" + idDocumental + ", idUsuario=" + idUsuario + "]";
	}
	

}
