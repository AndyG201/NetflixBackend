import React, { useEffect, useState } from "react";
import axios from "axios";
import "../css/PuntosModal.css";

function PuntosModal({ visible, onClose }) {
  const [puntos, setPuntos] = useState(0);
  const [premios, setPremios] = useState([]);
  const usuario = JSON.parse(localStorage.getItem("usuario"));
  const idUsuario = usuario?.idUsuario || usuario?.id;

  useEffect(() => {
    if (visible && idUsuario) {
      axios
        .get(`http://localhost:8080/puntos/consultar/${idUsuario}`)
        .then((res) => {
          setPuntos(res.data.puntos || 0);
          setPremios(res.data.premios || []);
        })
        .catch((err) => console.error("Error cargando puntos:", err));
    }
  }, [visible, idUsuario]);

  if (!visible) return null;

  return (
    <div className="puntos-modal-overlay">
      <div className="puntos-modal">
        <h2>Puntos acumulados: {puntos}</h2>

        <h3>Premios disponibles:</h3>
        <ul className="lista-premios">
          {premios.length > 0 ? (
            premios.map((premio, index) => (
              <li key={index}>
                <strong>{premio.nombre}</strong> — {premio.recompensa}
              </li>
            ))
          ) : (
            <p>No tienes premios disponibles aún</p>
          )}
        </ul>

        <button className="btn-cerrar" onClick={onClose}>
          Cerrar
        </button>
      </div>
    </div>
  );
}

export default PuntosModal;
