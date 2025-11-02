package co.edu.unbosque.netflixbackend.model;

import java.time.LocalDateTime;

public class UsuarioSuscripcion {
	private int idUsuarioSuscripcion;
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;
	private int idEstado;
	private int idUsuario;
	private int idSuscripcion;
	
	public UsuarioSuscripcion() {
	}

	public UsuarioSuscripcion(LocalDateTime fechaInicio, LocalDateTime fechaFin, int idEstado, int idUsuario,
			int idSuscripcion) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.idEstado = idEstado;
		this.idUsuario = idUsuario;
		this.idSuscripcion = idSuscripcion;
	}

	public int getIdUsuarioSuscripcion() {
		return idUsuarioSuscripcion;
	}

	public void setIdUsuarioSuscripcion(int idUsuarioSuscripcion) {
		this.idUsuarioSuscripcion = idUsuarioSuscripcion;
	}

	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDateTime getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDateTime fechaFin) {
		this.fechaFin = fechaFin;
	}

	public int getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdSuscripción() {
		return idSuscripcion;
	}

	public void setIdSuscripción(int idSuscripción) {
		this.idSuscripcion = idSuscripción;
	}

}
