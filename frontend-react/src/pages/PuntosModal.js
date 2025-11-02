import React from "react";
import "../css/PuntosModal.css";

const PuntosModal = ({ visible, puntos, mensaje, onClose }) => {
  if (!visible) return null;

  return (
    <div className="puntos-modal-overlay" onClick={onClose}>
      <div className="puntos-modal-content" onClick={(e) => e.stopPropagation()}>
        <h2>🎉 ¡Felicidades!</h2>
        <p>Has ganado <strong>{puntos}</strong> puntos</p>
        {mensaje && <p className="mensaje">{mensaje}</p>}
        <button onClick={onClose}>Aceptar</button>
      </div>
    </div>
  );
};

export default PuntosModal;
