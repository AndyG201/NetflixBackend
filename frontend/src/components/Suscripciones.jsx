import React, { useEffect, useState } from "react";
import axios from "axios";
import "../css/Suscripciones.css";
import { useNavigate } from "react-router-dom";

const Suscripciones = () => {
    const [suscripciones, setSuscripciones] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    const navigate = useNavigate();

    useEffect(() => {
        const obtenerSuscripciones = async () => {
            try {
                const response = await axios.get("http://localhost:8080/suscripcion/obtenersuscripciones");
                setSuscripciones(response.data);
            } catch (err) {
                console.error("Error al obtener suscripciones:", err);
                setError("No se pudieron cargar las suscripciones. Inténtalo de nuevo más tarde.");
            } finally {
                setLoading(false);
            }
        };
        obtenerSuscripciones();
    }, []);

    const manejarSeleccion = (suscripcion) => {
        const usuario = JSON.parse(localStorage.getItem("usuario"));
        navigate("/pago", { state: { suscripcion, usuario } });
    };

    if (loading) return <p className="mensaje">Cargando suscripciones...</p>;
    if (error) return <p className="mensaje error">{error}</p>;

    return (
        <div className="suscripciones-container">
            <h2 className="titulo">Planes de Suscripción</h2>
            <div className="suscripciones-grid">
                {suscripciones.map((sus) => (
                    <div key={sus.idSuscripcion} className="suscripcion-card">
                        <h3 className="suscripcion-tipo">{sus.tipoSuscripcion}</h3>
                        <p><strong>Duración:</strong> {sus.duracion} meses</p>
                        <p><strong>Precio:</strong> ${sus.precio}</p>
                        <p className="descripcion">{sus.descripcion}</p>
                        <button className="btn-suscribirse" onClick={() => manejarSeleccion(sus)}>
                            Seleccionar
                        </button>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default Suscripciones;
