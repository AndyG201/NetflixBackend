package co.edu.unbosque.netflixbackend.dto;

public class EstadoPago {

	private int idPago;
	private String nombreEstado;
	
	public EstadoPago() {
		// TODO Auto-generated constructor stub
	}

	public EstadoPago(String nombreEstado) {
		super();
		this.nombreEstado = nombreEstado;
	}

	public int getIdPago() {
		return idPago;
	}

	public void setIdPago(int idPago) {
		this.idPago = idPago;
	}

	public String getNombreEstado() {
		return nombreEstado;
	}

	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}
	
	
}
