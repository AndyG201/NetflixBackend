package co.edu.unbosque.netflixbackend.model;

import java.time.LocalDateTime;

public class Pago {

	private int idPago;
	private LocalDateTime fecha;
	private String referencia;
	private int monto;
	private int idUsuario;
	private int idSuscripcion;
	private int idEstadoPago;
	private int metodoPago;
	
	public Pago() {
		// TODO Auto-generated constructor stub
	}

	public Pago(LocalDateTime fecha, String referencia, int monto, int idUsuario, int idSuscripcion, int idEstadoPago,
			int metodoPago) {
		super();
		this.fecha = fecha;
		this.referencia = referencia;
		this.monto = monto;
		this.idUsuario = idUsuario;
		this.idSuscripcion = idSuscripcion;
		this.idEstadoPago = idEstadoPago;
		this.metodoPago = metodoPago;
	}

	public int getIdPago() {
		return idPago;
	}

	public void setIdPago(int idPago) {
		this.idPago = idPago;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
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

	public int getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(int metodoPago) {
		this.metodoPago = metodoPago;
	}

	@Override
	public String toString() {
		return "Pago [idPago=" + idPago + ", fecha=" + fecha + ", referencia=" + referencia + ", monto=" + monto
				+ ", idUsuario=" + idUsuario + ", idSuscripcion=" + idSuscripcion + ", idEstadoPago=" + idEstadoPago
				+ ", metodoPago=" + metodoPago + "]";
	}
	

}
