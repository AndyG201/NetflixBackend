import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "../css/Suscripciones.css";

const Suscripciones = () => {
  const [suscripciones, setSuscripciones] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchSuscripciones = async () => {
      try {
        const response = await axios.get("http://localhost:8080/suscripciones");
        setSuscripciones(response.data);
      } catch (err) {
        console.error("Error al obtener las suscripciones:", err);
        setError("Error al cargar las suscripciones");
      } finally {
        setLoading(false);
      }
    };

    fetchSuscripciones();
  }, []);

  const handleSeleccionar = async (idSuscripcion) => {
    const usuario = JSON.parse(localStorage.getItem("usuario"));
    if (!usuario) {
      alert("Debes iniciar sesión antes de seleccionar una suscripción");
      navigate("/login");
      return;
    }

    try {
      await axios.post("http://localhost:8080/usuario/suscribirse", null, {
        params: {
          idUsuario: usuario.id,
          idSuscripcion: idSuscripcion,
        },
      });

      alert("✅ Suscripción realizada con éxito");
      navigate("/peliculas");
    } catch (error) {
      console.error("Error al suscribirse:", error);
      alert("❌ Error al realizar la suscripción");
    }
  };

  if (loading) return <p className="loading">Cargando suscripciones...</p>;
  if (error) return <p className="error">{error}</p>;

  return (
    <div className="suscripciones-container">
      <h1 className="suscripciones-title">Elige tu plan</h1>
      <div className="suscripciones-grid">
        {suscripciones.map((sus) => (
          <div key={sus.id} className="suscripcion-card">
            <h2>{sus.nombre}</h2>
            <p>{sus.descripcion}</p>
            <p className="precio">${sus.precio}/mes</p>
            <button
              className="btn-seleccionar"
              onClick={() => handleSeleccionar(sus.id)}
            >
              Seleccionar
            </button>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Suscripciones;
