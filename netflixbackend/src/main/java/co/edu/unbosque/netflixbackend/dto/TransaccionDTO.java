package co.edu.unbosque.netflixbackend.dto;

import java.time.LocalDateTime;


public class TransaccionDTO {

	private int idTransaccion;
	private int cantidad;
	private LocalDateTime fecha;
	private String descripcion;
	private int idUsuario;
	private int idEstadoTransaccion;
	private int idCriterioTransaccion;
	
	public TransaccionDTO() {
		// TODO Auto-generated constructor stub
	}

	public TransaccionDTO(int cantidad, LocalDateTime fecha, String descripcion, int idUsuario, int idEstadoTransaccion,
			int idCriterioTransaccion) {
		this.cantidad = cantidad;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.idUsuario = idUsuario;
		this.idEstadoTransaccion = idEstadoTransaccion;
		this.idCriterioTransaccion = idCriterioTransaccion;
	}

	public int getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(int idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdEstadoTransaccion() {
		return idEstadoTransaccion;
	}

	public void setIdEstadoTransaccion(int idEstadoTransaccion) {
		this.idEstadoTransaccion = idEstadoTransaccion;
	}

	public int getIdCriterioTransaccion() {
		return idCriterioTransaccion;
	}

	public void setIdCriterioTransaccion(int idCriterioTransaccion) {
		this.idCriterioTransaccion = idCriterioTransaccion;
	}

	@Override
	public String toString() {
		return "Transaccion [idTransaccion=" + idTransaccion + ", cantidad=" + cantidad + ", fecha=" + fecha
				+ ", descripcion=" + descripcion + ", idUsuario=" + idUsuario + ", idEstadoTransaccion="
				+ idEstadoTransaccion + ", idCriterioTransaccion=" + idCriterioTransaccion + "]";
	}
	
}
