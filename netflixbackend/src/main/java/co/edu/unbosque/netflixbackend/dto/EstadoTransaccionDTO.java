package co.edu.unbosque.netflixbackend.dto;

public class EstadoTransaccionDTO {
	
	private int idEstadoTransaccion;
	private String estado;
	
	public EstadoTransaccionDTO(int idEstadoTransaccion, String estado) {
		this.idEstadoTransaccion = idEstadoTransaccion;
		this.estado = estado;
	}

	public EstadoTransaccionDTO() {
		// TODO Auto-generated constructor stub
	}

	public EstadoTransaccionDTO(String estado) {
		super();
		this.estado = estado;
	}

	public int getIdEstadoTransaccion() {
		return idEstadoTransaccion;
	}

	public void setIdEstadoTransaccion(int idEstadoTransaccion) {
		this.idEstadoTransaccion = idEstadoTransaccion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "EstadoTransaccion [idEstadoTransaccion=" + idEstadoTransaccion + ", estado=" + estado + "]";
	}
	
	
	
	

}
