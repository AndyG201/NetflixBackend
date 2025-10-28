package co.edu.unbosque.netflixbackend.model;

public class TipoPremio {
    private int idTipoPremio;
    private String nombreTipo;
    private String criterio;
    private String recompensa;
    
    public TipoPremio() {
		// TODO Auto-generated constructor stub
	}

	public TipoPremio(int idTipoPremio, String nombreTipo, String criterio, String recompensa) {
		super();
		this.idTipoPremio = idTipoPremio;
		this.nombreTipo = nombreTipo;
		this.criterio = criterio;
		this.recompensa = recompensa;
	}

	public TipoPremio(String nombreTipo, String criterio, String recompensa) {
		super();
		this.nombreTipo = nombreTipo;
		this.criterio = criterio;
		this.recompensa = recompensa;
	}

	public int getIdTipoPremio() {
		return idTipoPremio;
	}

	public void setIdTipoPremio(int idTipoPremio) {
		this.idTipoPremio = idTipoPremio;
	}

	public String getNombreTipo() {
		return nombreTipo;
	}

	public void setNombreTipo(String nombreTipo) {
		this.nombreTipo = nombreTipo;
	}

	public String getCriterio() {
		return criterio;
	}

	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}

	public String getRecompensa() {
		return recompensa;
	}

	public void setRecompensa(String recompensa) {
		this.recompensa = recompensa;
	}
    
}
