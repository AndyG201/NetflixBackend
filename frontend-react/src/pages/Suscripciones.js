import React, { useEffect, useState } from "react";
import axios from "axios";
import "../css/Suscripciones.css";
import { useNavigate } from "react-router-dom";

const Suscripciones = () => {
  const [suscripciones, setSuscripciones] = useState([]);
  const navigate = useNavigate();

  // ✅ Obtener usuario del localStorage
  const usuario = JSON.parse(localStorage.getItem("usuario"));

  useEffect(() => {
    const fetchSuscripciones = async () => {
      try {
        const response = await axios.get("http://localhost:8080/suscripciones");
        setSuscripciones(response.data);
      } catch (error) {
        console.error("❌ Error al cargar suscripciones:", error);
      }
    };

    fetchSuscripciones();
  }, []);

  // ✅ Manejar selección de suscripción
  const handleSeleccionar = (suscripcion) => {
    if (!usuario) {
      alert("Debes iniciar sesión primero.");
      navigate("/login");
      return;
    }

    // Guardar suscripción seleccionada en localStorage
    localStorage.setItem("suscripcion", JSON.stringify(suscripcion));

    // Navegar a la página de pago con los datos
    navigate("/pago", { state: { usuario, suscripcion } });
  };

  return (
    <div className="suscripciones-container">
      <h1 className="titulo">Elige tu plan de suscripción</h1>

      <div className="suscripciones-grid">
        {suscripciones.length > 0 ? (
          suscripciones.map((s) => (
            <div key={s.idSuscripcion} className="suscripcion-card">
              <h2>{s.tipoSuscripcion}</h2>
              <p>{s.descripcion}</p>
              <p className="precio">${s.precio}</p>
              <button
                className="btn-seleccionar"
                onClick={() => handleSeleccionar(s)}
              >
                Seleccionar
              </button>
            </div>
          ))
        ) : (
          <p>Cargando planes...</p>
        )}
      </div>
    </div>
  );
};

export default Suscripciones;
