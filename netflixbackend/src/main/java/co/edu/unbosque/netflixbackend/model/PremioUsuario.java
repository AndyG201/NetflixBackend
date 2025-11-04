package co.edu.unbosque.netflixbackend.model;

import java.time.LocalDate;

public class PremioUsuario {

    private int idUsuario;
    private int idPremio;
    private LocalDate fechaOtorgado; // ✅ tipo LocalDate

    // --- Getters y Setters ---
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdPremio() {
        return idPremio;
    }

    public void setIdPremio(int idPremio) {
        this.idPremio = idPremio;
    }

    public LocalDate getFechaOtorgado() {
        return fechaOtorgado;
    }

    public void setFechaOtorgado(LocalDate fechaOtorgado) {
        this.fechaOtorgado = fechaOtorgado;
    }

    @Override
    public String toString() {
        return "PremioUsuario{" +
                "idUsuario=" + idUsuario +
                ", idPremio=" + idPremio +
                ", fechaOtorgado=" + fechaOtorgado +
                '}';
    }
}
