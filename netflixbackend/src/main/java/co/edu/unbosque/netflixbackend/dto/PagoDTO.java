package co.edu.unbosque.netflixbackend.dto;

import java.time.LocalDate;

public class PagoDTO {

	private int idPago;
	private LocalDate fechaMaxima;
	private String referencia;
	private int monto;
	private int idUsuario;
	private int idSuscripcion;
	private int idEstadoPago;
	private int idMetodoPago;
	
	
	public PagoDTO(int idPago, LocalDate fechaMaxima, String referencia, int monto, int idUsuario, int idSuscripcion,
			int idEstadoPago, int idMetodoPago) {
		this.idPago = idPago;
		this.fechaMaxima = fechaMaxima;
		this.referencia = referencia;
		this.monto = monto;
		this.idUsuario = idUsuario;
		this.idSuscripcion = idSuscripcion;
		this.idEstadoPago = idEstadoPago;
		this.idMetodoPago = idMetodoPago;
	}

	public PagoDTO() {
		// TODO Auto-generated constructor stub
	}

	public PagoDTO(LocalDate fecha, String referencia, int monto, int idUsuario, int idSuscripcion, int idEstadoPago,
			int idMetodoPago) {
		super();
		this.fechaMaxima = fecha;
		this.referencia = referencia;
		this.monto = monto;
		this.idUsuario = idUsuario;
		this.idSuscripcion = idSuscripcion;
		this.idEstadoPago = idEstadoPago;
		this.idMetodoPago = idMetodoPago;
	}

	public int getIdPago() {
		return idPago;
	}

	public void setIdPago(int idPago) {
		this.idPago = idPago;
	}

	public LocalDate getFecha() {
		return fechaMaxima;
	}

	public void setFecha(LocalDate fecha) {
		this.fechaMaxima = fecha;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public int getMonto() {
		return monto;
	}

	public void setMonto(int monto) {
		this.monto = monto;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdSuscripcion() {
		return idSuscripcion;
	}

	public void setIdSuscripcion(int idSuscripcion) {
		this.idSuscripcion = idSuscripcion;
	}

	public int getIdEstadoPago() {
		return idEstadoPago;
	}

	public void setIdEstadoPago(int idEstadoPago) {
		this.idEstadoPago = idEstadoPago;
	}

	public int getIdMetodoPago() {
		return idMetodoPago;
	}

	public void setIdMetodoPago(int idMetodoPago) {
		this.idMetodoPago = idMetodoPago;
	}

}
