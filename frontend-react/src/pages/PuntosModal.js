import React, { useEffect, useState } from "react";
import axios from "axios";
import "../css/PuntosModal.css";

const PuntosModal = ({ visible, mensaje, onClose }) => {
  const [puntos, setPuntos] = useState(0);
  const [premios, setPremios] = useState([]);

  const usuario = JSON.parse(localStorage.getItem("usuario"));
  const idUsuario = usuario?.id;

  useEffect(() => {
    if (visible && idUsuario) {
      const fetchPuntos = async () => {
        try {
          const res = await axios.get(`http://localhost:8080/puntos/consultar/${idUsuario}`);
          setPuntos(res.data.puntos || 0);
          setPremios(res.data.premios?.filter(p => p.estado === "pendiente") || []);
        } catch (error) {
          console.error("⚠️ Error obteniendo puntos:", error);
          setPuntos(0);
          setPremios([]);
        }
      };
      fetchPuntos();
    }
  }, [visible, idUsuario]);

  if (!visible) return null;

  return (
    <div className="puntos-modal-overlay" onClick={onClose}>
      <div className="puntos-modal-content" onClick={(e) => e.stopPropagation()}>
        <h2>🎉 Mis Puntos</h2>
        <p>Total de puntos: <strong>{puntos}</strong></p>
        {premios.length > 0 ? (
          <p>🎁 Tienes {premios.length} premio(s) pendiente(s) para reclamar</p>
        ) : (
          <p>No tienes premios pendientes</p>
        )}
        {mensaje && <p>{mensaje}</p>}
        <button onClick={onClose}>Aceptar</button>
      </div>
    </div>
  );
};

export default PuntosModal;
