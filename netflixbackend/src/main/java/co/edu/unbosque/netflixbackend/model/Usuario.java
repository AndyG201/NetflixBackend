package co.edu.unbosque.netflixbackend.model;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class Usuario {

	private int idUsuario;
	private String primerNombre;
	private String primerApellido;
	private String correo;
	private String telefono;
	private LocalDate fechaNacimiento;
	private String contrasenia;
	private LocalDateTime fechaRegistro;
	private int idEstado;
    private boolean primeraVez;

    public Usuario() {
		// TODO Auto-generated constructor stub
	}


	public Usuario(String primerNombre, String primerApellido, String correo, String telefono,
			LocalDate fechaNacimiento, String contrasenia, LocalDateTime fechaRegistro, int idEstado) {
		super();
		this.primerNombre = primerNombre;
		this.primerApellido = primerApellido;
		this.correo = correo;
		this.telefono = telefono;
		this.fechaNacimiento = fechaNacimiento;
		this.contrasenia = contrasenia;
		this.fechaRegistro = fechaRegistro;
		this.idEstado = idEstado;
	}



	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public int getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

    public boolean isPrimeraVez() {
        return primeraVez;
    }

    public void setPrimeraVez(boolean primeraVez) {
        this.primeraVez = primeraVez;
    }

	
}
