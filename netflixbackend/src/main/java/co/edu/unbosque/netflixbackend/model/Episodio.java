package co.edu.unbosque.netflixbackend.model;

public class Episodio {

    private int idEpisodio;
    private String nombre;
    private int numeroDeEpisodio;
    private int duracion;
    private int idTemporada;

    public Episodio() {
    }

    public Episodio(int idEpisodio, String nombre, int numeroDeEpisodio, int duracion, int idTemporada) {
        this.idEpisodio = idEpisodio;
        this.nombre = nombre;
        this.numeroDeEpisodio = numeroDeEpisodio;
        this.duracion = duracion;
        this.idTemporada = idTemporada;
    }

    public int getIdEpisodio() {
        return idEpisodio;
    }

    public void setIdEpisodio(int idEpisodio) {
        this.idEpisodio = idEpisodio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroDeEpisodio() {
        return numeroDeEpisodio;
    }

    public void setNumeroDeEpisodio(int numeroDeEpisodio) {
        this.numeroDeEpisodio = numeroDeEpisodio;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getIdTemporada() {
        return idTemporada;
    }

    public void setIdTemporada(int idTemporada) {
        this.idTemporada = idTemporada;
    }

    @Override
    public String toString() {
        return "Episodio [idEpisodio=" + idEpisodio + ", nombre=" + nombre + ", numeroDeEpisodio=" + numeroDeEpisodio
                + ", duracion=" + duracion + ", idTemporada=" + idTemporada + "]";
    }
}
