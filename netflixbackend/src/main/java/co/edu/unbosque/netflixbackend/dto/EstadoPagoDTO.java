package co.edu.unbosque.netflixbackend.dto;

public class EstadoPagoDTO {

	private int idPago;
	private String nombreEstado;
	
	public EstadoPagoDTO() {
		// TODO Auto-generated constructor stub
	}

	public EstadoPagoDTO(String nombreEstado) {
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
