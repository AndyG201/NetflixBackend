package co.edu.unbosque.netflixbackend.dto;

public class EstadoCuentaDTO {

	public EstadoCuentaDTO(int idPago, String nombreEstado) {
		this.idPago = idPago;
		this.nombreEstado = nombreEstado;
	}

	private int idPago;
	private String nombreEstado;

	public EstadoCuentaDTO() {
		// TODO Auto-generated constructor stub
	}

	public EstadoCuentaDTO(String nombreEstado) {
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
