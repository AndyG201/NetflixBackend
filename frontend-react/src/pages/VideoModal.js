import React from "react";
import "../css/VideoModal.css";

function VideoModal({ videoUrl, onClose }) {
  if (!videoUrl) return null;

  // 🔹 Convierte enlaces de YouTube al formato embed
  const transformarYoutubeUrl = (url) => {
    if (!url) return "";
    if (url.includes("watch?v=")) {
      const base = url.split("&")[0]; // elimina parámetros extras
      return base.replace("watch?v=", "embed/");
    }
    return url;
  };

  const esYoutube = videoUrl.includes("youtube.com");

  return (
    <div className="modal-overlay" onClick={onClose}>
      <div
        className="modal-contenido"
        onClick={(e) => e.stopPropagation()} // evita cierre al hacer clic dentro
      >
        <button className="btn-cerrar" onClick={onClose}>
          ✕
        </button>

        {esYoutube ? (
          <iframe
            src={transformarYoutubeUrl(videoUrl)}
            title="Reproductor"
            frameBorder="0"
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
            allowFullScreen
            style={{ width: "80vw", height: "70vh", borderRadius: "12px" }}
          ></iframe>
        ) : (
          <video
            src={videoUrl}
            controls
            autoPlay
            style={{ width: "80vw", height: "70vh", borderRadius: "12px" }}
          />
        )}
      </div>
    </div>
  );
}

export default VideoModal;
