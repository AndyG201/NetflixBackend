package co.edu.unbosque.netflixbackend.model;

public class EstadoTransaccion {
	
	private int idEstadoTransaccion;
	private String estado;
	
	public EstadoTransaccion() {
		// TODO Auto-generated constructor stub
	}

	public EstadoTransaccion(String estado) {
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
