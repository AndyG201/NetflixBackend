package co.edu.unbosque.netflixbackend.model;

import java.time.LocalDateTime;

public class Usuario {

	private int idUsuario;
	private String primerNombre;
	private String primerApellido;
	private String correo;
	private int telefono;
	private LocalDateTime fechaNacimiento;
	private String contrasenia;
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	
	public Usuario(String correo, int telefono, String contrasenia ) {
		this.correo = correo;
		this.telefono = telefono;
		this.contrasenia = contrasenia;
	}

	public Usuario(String primerNombre, String primerApellido, String correo, int telefono,
			LocalDateTime fechaNacimiento, String contrasenia) {
		this.primerNombre = primerNombre;
		this.primerApellido = primerApellido;
		this.correo = correo;
		this.telefono = telefono;
		this.fechaNacimiento = fechaNacimiento;
		this.contrasenia = contrasenia;
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

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
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

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", primerNombre=" + primerNombre + ", primerApellido="
				+ primerApellido + ", correo=" + correo + ", telefono=" + telefono + ", fechaNacimiento="
				+ fechaNacimiento + ", contrasenia=" + contrasenia + "]";
	}
	
	
	
	
	
	
	
}
