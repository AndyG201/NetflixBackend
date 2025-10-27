package co.edu.unbosque.netflixbackend.dto;

public class SerieDTO {

	private int idSerie;
	private String titulo;
	private String descripcion;
	private String fechaEstreno;
	private String poster;
	private double calificacion;
	private int popularidad;

	public SerieDTO(int idSerie, String titulo, String descripcion, String fechaEstreno, String poster,
			double calificacion, int popularidad) {
		this.idSerie = idSerie;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.fechaEstreno = fechaEstreno;
		this.poster = poster;
		this.calificacion = calificacion;
		this.popularidad = popularidad;
	}

	public SerieDTO() {
			// TODO Auto-generated constructor stub
		}

	public SerieDTO(String titulo, String descripcion, String fechaEstreno, String poster, double calificacion,
				int popularidad) {
			super();
			this.titulo = titulo;
			this.descripcion = descripcion;
			this.fechaEstreno = fechaEstreno;
			this.poster = poster;
			this.calificacion = calificacion;
			this.popularidad = popularidad;
		}

	public int getIdSerie() {
		return idSerie;
	}

	public void setIdSerie(int idSerie) {
		this.idSerie = idSerie;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFechaEstreno() {
		return fechaEstreno;
	}

	public void setFechaEstreno(String fechaEstreno) {
		this.fechaEstreno = fechaEstreno;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public double getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(double calificacion) {
		this.calificacion = calificacion;
	}

	public int getPopularidad() {
		return popularidad;
	}

	public void setPopularidad(int popularidad) {
		this.popularidad = popularidad;
	}

}
