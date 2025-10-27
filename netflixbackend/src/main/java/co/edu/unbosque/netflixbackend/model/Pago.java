package co.edu.unbosque.netflixbackend.model;

import java.time.LocalDate;


public class Pago {

	private int idPago;
	private LocalDate fecha;
	private String referencia;
	private int monto;
	private int idUsuario;
	private int idSuscripcion;
	private int idEstadoPago;
	private int idMetodoPago;
	
	public Pago(int idPago, LocalDate fecha, String referencia, int monto, int idUsuario, int idSuscripcion,
			int idEstadoPago, int idMetodoPago) {
		this.idPago = idPago;
		this.fecha = fecha;
		this.referencia = referencia;
		this.monto = monto;
		this.idUsuario = idUsuario;
		this.idSuscripcion = idSuscripcion;
		this.idEstadoPago = idEstadoPago;
		this.idMetodoPago = idMetodoPago;
	}

	public Pago() {
		// TODO Auto-generated constructor stub
	}

	public Pago(LocalDate fecha, String referencia, int monto, int idUsuario, int idSuscripcion, int idEstadoPago,
			int idMetodoPago) {
		super();
		this.fecha = fecha;
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
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
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
