import React, { useState } from 'react';
import axios from 'axios';
import '../css/Register.css';
import { useNavigate } from 'react-router-dom';

const Register = () => {
    const [usuario, setUsuario] = useState({
        primerNombre: '',
        primerApellido: '',
        correo: '',
        telefono: '',
        fechaNacimiento: '',
        contrasenia: ''
    });

    const [codigo, setCodigo] = useState('');
    const [etapa, setEtapa] = useState('registro'); // 'registro' o 'verificacion'
    const [mensaje, setMensaje] = useState('');
    const navigate = useNavigate();

    // Formatear la fecha al formato YYYY-MM-DD
    const formatearFecha = (fecha) => {
        if (!fecha) return null;
        const partes = fecha.split('-');
        if (partes.length === 3) {
            return `${partes[0]}-${partes[1]}-${partes[2]}`;
        }
        return fecha;
    };

    const handleChange = (e) => {
        setUsuario({ ...usuario, [e.target.name]: e.target.value });
    };

    // Enviar código de verificación al correo
    const handleSubmitRegistro = async (e) => {
        e.preventDefault();

        const usuarioFormateado = {
            ...usuario,
            fechaNacimiento: formatearFecha(usuario.fechaNacimiento)
        };

        try {
            const response = await axios.post(
                'http://localhost:8080/usuario/enviarcodigo',
                usuarioFormateado
            );

            if (response.status === 202) {
                setEtapa('verificacion');
                setMensaje('Código enviado al correo. Revisa tu bandeja de entrada.');
            } else {
                setMensaje('No se pudo enviar el código.');
            }
        } catch (error) {
            console.error('Error al registrar:', error);
            setMensaje('Error al enviar el código.');
        }
    };

    // Verificar el código y crear el usuario
    const handleSubmitVerificacion = async (e) => {
        e.preventDefault();

        try {
            const response = await axios.post(
                'http://localhost:8080/usuario/crearusuario',
                { codigo: codigo }, // ✅ enviar como objeto JSON
                { headers: { 'Content-Type': 'application/json' } }
            );

            if (response.status === 201) {
                navigate('/');
            } else {
                setMensaje('Código incorrecto o error al crear el usuario.');
            }
        } catch (error) {
            console.error('Error al verificar:', error);
            setMensaje('Error al crear el usuario.');
        }
    };

    const volverLogin = () => {
        navigate('/');
    };

    return (
        <div className="register-container">
            <div className="register-box">
                {etapa === 'registro' ? (
                    <>
                        <h2>Crear Cuenta</h2>
                        <form onSubmit={handleSubmitRegistro}>
                            <input
                                type="text"
                                name="primerNombre"
                                placeholder="Primer nombre"
                                value={usuario.primerNombre}
                                onChange={handleChange}
                                required
                            />
                            <input
                                type="text"
                                name="primerApellido"
                                placeholder="Primer apellido"
                                value={usuario.primerApellido}
                                onChange={handleChange}
                                required
                            />
                            <input
                                type="email"
                                name="correo"
                                placeholder="Correo electrónico"
                                value={usuario.correo}
                                onChange={handleChange}
                                required
                            />
                            <input
                                type="text"
                                name="telefono"
                                placeholder="Teléfono"
                                value={usuario.telefono}
                                onChange={handleChange}
                            />
                            <input
                                type="date"
                                name="fechaNacimiento"
                                placeholder="Fecha de nacimiento"
                                value={usuario.fechaNacimiento}
                                onChange={handleChange}
                            />
                            <input
                                type="password"
                                name="contrasenia"
                                placeholder="Contraseña"
                                value={usuario.contrasenia}
                                onChange={handleChange}
                                required
                            />
                            <button type="submit" className="btn-register">
                                Enviar Código
                            </button>
                            <button type="button" className="btn-back" onClick={volverLogin}>
                                Volver
                            </button>
                        </form>
                    </>
                ) : (
                    <>
                        <h2>Verificar Código</h2>
                        <form onSubmit={handleSubmitVerificacion}>
                            <input
                                type="text"
                                placeholder="Ingresa el código recibido"
                                value={codigo}
                                onChange={(e) => setCodigo(e.target.value)}
                                required
                            />
                            <button type="submit" className="btn-register">
                                Verificar y Crear Cuenta
                            </button>
                            <button type="button" className="btn-back" onClick={volverLogin}>
                                Cancelar
                            </button>
                        </form>
                    </>
                )}
                {mensaje && <p className="register-message">{mensaje}</p>}
            </div>
        </div>
    );
};

export default Register;
