package co.edu.unbosque.netflixbackend.model;

public class EstadoCuenta {

	private int idEstado;
	private String nombreEstado;
	
	public EstadoCuenta(int idEstado, String nombreEstado) {
		this.idEstado = idEstado;
		this.nombreEstado = nombreEstado;
	}

	public EstadoCuenta() {
		// TODO Auto-generated constructor stub
	}

	public EstadoCuenta(String nombreEstado) {
		super();
		this.nombreEstado = nombreEstado;
	}

	public int getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public String getNombreEstado() {
		return nombreEstado;
	}

	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}
	
	
}
