package co.edu.unbosque.netflixbackend.model;

public class Clasificacion {
	private int idClasificacion;
	private String nombre;
	private int edadMinima;
	private String descripcion;
	
	public Clasificacion() {
		// TODO Auto-generated constructor stub
	}

	public Clasificacion(String nombre, int edadMinima, String descripcion) {
		super();
		this.nombre = nombre;
		this.edadMinima = edadMinima;
		this.descripcion = descripcion;
	}

	public int getIdClasificacion() {
		return idClasificacion;
	}

	public void setIdClasificacion(int idClasificacion) {
		this.idClasificacion = idClasificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdadMinima() {
		return edadMinima;
	}

	public void setEdadMinima(int edadMinima) {
		this.edadMinima = edadMinima;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Clasificacion [idClasificacion=" + idClasificacion + ", nombre=" + nombre + ", edadMinima=" + edadMinima
				+ ", descripcion=" + descripcion + "]";
	}
	
}
