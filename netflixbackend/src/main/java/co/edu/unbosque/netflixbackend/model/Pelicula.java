package co.edu.unbosque.netflixbackend.model;

public class Pelicula {

    private int idPelicula;
    private String nombre;
    private String genero;
    private int duracion;
    private String descripcion;
    private int idClasificacion;

    public Pelicula() {
    }

    public Pelicula(int idPelicula, String nombre, String genero, int duracion, String descripcion, int idClasificacion) {
        this.idPelicula = idPelicula;
        this.nombre = nombre;
        this.genero = genero;
        this.duracion = duracion;
        this.descripcion = descripcion;
        this.idClasificacion = idClasificacion;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdClasificacion() {
        return idClasificacion;
    }

    public void setIdClasificacion(int idClasificacion) {
        this.idClasificacion = idClasificacion;
    }

    @Override
    public String toString() {
        return "Pelicula [idPelicula=" + idPelicula + ", nombre=" + nombre + ", genero=" + genero + ", duracion="
                + duracion + ", descripcion=" + descripcion + ", idClasificacion=" + idClasificacion + "]";
    }
}
