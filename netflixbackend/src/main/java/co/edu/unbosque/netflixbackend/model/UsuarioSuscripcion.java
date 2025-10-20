package co.edu.unbosque.netflixbackend.model;

import java.time.LocalDateTime;

public class UsuarioSuscripcion {
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;
	private String estado;
	private int idUsuario;
	private int idSuscripción;
	
	public UsuarioSuscripcion() {
	}
	
	public UsuarioSuscripcion(LocalDateTime fechaInicio, LocalDateTime fechaFin, String estado, int idUsuario,
			int idSuscripción) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.estado = estado;
		this.idUsuario = idUsuario;
		this.idSuscripción = idSuscripción;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdSuscripción() {
		return idSuscripción;
	}

	public void setIdSuscripción(int idSuscripción) {
		this.idSuscripción = idSuscripción;
	}

	@Override
	public String toString() {
		return "UsuarioSuscripcion [fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", estado=" + estado
				+ ", idUsuario=" + idUsuario + ", idSuscripción=" + idSuscripción + "]";
	}
	


	

}
