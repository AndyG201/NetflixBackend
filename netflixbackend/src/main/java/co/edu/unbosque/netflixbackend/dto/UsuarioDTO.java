package co.edu.unbosque.netflixbackend.dto;
import java.time.LocalDateTime;
import co.edu.unbosque.netflixbackend.model.EstadoCuenta;

public class UsuarioDTO {

	private int idUsuario;
	private String primerNombre;
	private String primerApellido;
	private String correo;
	private String telefono;
	private LocalDateTime fechaNacimiento;
	private String contrasenia;
	private LocalDateTime fechaRegistro;
	private EstadoCuenta estadoCuenta;

	public UsuarioDTO() {
		this.fechaRegistro = LocalDateTime.now();
		this.estadoCuenta = EstadoCuenta.ACTIVO;
	}

	public UsuarioDTO(String correo, String telefono, String contrasenia) {
		this.correo = correo;
		this.telefono = telefono;
		this.contrasenia = contrasenia;
		this.fechaRegistro = LocalDateTime.now();
		this.estadoCuenta = EstadoCuenta.ACTIVO;
	}

	public UsuarioDTO(String primerNombre, String primerApellido, String correo, String telefono,
			LocalDateTime fechaNacimiento, String contrasenia) {
		this.primerNombre = primerNombre;
		this.primerApellido = primerApellido;
		this.correo = correo;
		this.telefono = telefono;
		this.fechaNacimiento = fechaNacimiento;
		this.contrasenia = contrasenia;
		this.fechaRegistro = LocalDateTime.now();
		this.estadoCuenta = EstadoCuenta.ACTIVO;
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

	public LocalDateTime getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDateTime fechaNacimiento) {
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

	public EstadoCuenta getEstadoCuenta() {
		return estadoCuenta;
	}

	public void setEstadoCuenta(EstadoCuenta estadoCuenta) {
		this.estadoCuenta = estadoCuenta;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", primerNombre=" + primerNombre + ", primerApellido="
				+ primerApellido + ", correo=" + correo + ", telefono=" + telefono + ", fechaNacimiento="
				+ fechaNacimiento + ", contrasenia=" + contrasenia + ", fechaRegistro=" + fechaRegistro
				+ ", estadoCuenta=" + estadoCuenta + "]";
	}
}
