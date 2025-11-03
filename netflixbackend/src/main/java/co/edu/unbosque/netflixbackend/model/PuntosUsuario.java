package co.edu.unbosque.netflixbackend.model;

import java.time.LocalDateTime;

public class PuntosUsuario {

    private int idUsuario;
    private int puntosTotales;
    private LocalDateTime ultimaActualizacion;

    public PuntosUsuario() {
    }

    public PuntosUsuario(int idUsuario, int puntosTotales, LocalDateTime ultimaActualizacion) {
        this.idUsuario = idUsuario;
        this.puntosTotales = puntosTotales;
        this.ultimaActualizacion = ultimaActualizacion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getPuntosTotales() {
        return puntosTotales;
    }

    public void setPuntosTotales(int puntosTotales) {
        this.puntosTotales = puntosTotales;
    }

    public LocalDateTime getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    public void setUltimaActualizacion(LocalDateTime ultimaActualizacion) {
        this.ultimaActualizacion = ultimaActualizacion;
    }
}
