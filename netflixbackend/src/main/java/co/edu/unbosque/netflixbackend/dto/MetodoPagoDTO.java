package co.edu.unbosque.netflixbackend.dto;

public class MetodoPagoDTO {

	private int idMetodoPago;
	private String metodo;
	
	public MetodoPagoDTO(int idMetodoPago, String metodo) {
		this.idMetodoPago = idMetodoPago;
		this.metodo = metodo;
	}

	public MetodoPagoDTO() {
		// TODO Auto-generated constructor stub
	}

	public MetodoPagoDTO(int idMetodoPago, String metodo) {
		super();
		this.idMetodoPago = idMetodoPago;
		this.metodo = metodo;
	}

	public MetodoPagoDTO(String metodo) {
		this.metodo = metodo;
	}

	public int getIdMetodoPago() {
		return idMetodoPago;
	}

	public void setIdMetodoPago(int idMetodoPago) {
		this.idMetodoPago = idMetodoPago;
	}

	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}
	
	
}
