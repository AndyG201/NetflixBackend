import React from "react";
import "../css/InfoModal.css";

const InfoModal = ({ pelicula, onClose, onPlay }) => {
  if (!pelicula) return null;

  const handlePlay = () => {
    onPlay(pelicula); // La suma de puntos ya se hace en Peliculas.jsx
    onClose();
  };

  return (
    <div className="info-modal-overlay" onClick={onClose}>
      <div className="info-modal-content" onClick={(e) => e.stopPropagation()}>
        <img src={pelicula.poster || "https://via.placeholder.com/300x450"} alt={pelicula.nombre} className="info-modal-poster" />

        <div className="info-modal-details">
          <h2>{pelicula.nombre}</h2>
          <p className="descripcion">{pelicula.descripcion}</p>

          <p><strong>Año:</strong> {pelicula.fechaEstreno || "N/A"}</p>
          <p><strong>Popularidad:</strong> {pelicula.popularidad || "N/A"}</p>
          <p><strong>Calificación:</strong> ⭐ {pelicula.calificacion || "N/A"}</p>

          <div className="info-modal-buttons">
            <button className="btn-play" onClick={handlePlay}>
              ▶ Reproducir
            </button>
            <button className="btn-back" onClick={onClose}>
              ← Volver al catálogo
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default InfoModal;
