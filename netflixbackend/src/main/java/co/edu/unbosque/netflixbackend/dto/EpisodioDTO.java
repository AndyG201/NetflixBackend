package co.edu.unbosque.netflixbackend.dto;

public class EpisodioDTO {
	
	private int idEpisodio;
	private String titulo;
	private int numeroEpisodio;
	private String descripcion;
	private String poster;
	private String urlEpisodio;
	private int idTemporada;

	public EpisodioDTO() {
	}

	public EpisodioDTO(int idEpisodio, String titulo, int numeroEpisodio, String descripcion, String poster,
			String urlEpisodio, int idTemporada) {
		this.idEpisodio = idEpisodio;
		this.titulo = titulo;
		this.numeroEpisodio = numeroEpisodio;
		this.descripcion = descripcion;
		this.poster = poster;
		this.urlEpisodio = urlEpisodio;
		this.idTemporada = idTemporada;
	}

	// Getters y Setters
	public int getIdEpisodio() {
		return idEpisodio;
	}

	public void setIdEpisodio(int idEpisodio) {
		this.idEpisodio = idEpisodio;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getNumeroEpisodio() {
		return numeroEpisodio;
	}

	public void setNumeroEpisodio(int numeroEpisodio) {
		this.numeroEpisodio = numeroEpisodio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getUrlEpisodio() {
		return urlEpisodio;
	}

	public void setUrlEpisodio(String urlEpisodio) {
		this.urlEpisodio = urlEpisodio;
	}

	public int getIdTemporada() {
		return idTemporada;
	}

	public void setIdTemporada(int idTemporada) {
		this.idTemporada = idTemporada;
	}

}
