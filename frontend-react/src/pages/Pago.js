import React, { useEffect, useState } from "react";
import "../css/Pago.css";
import { useLocation, useNavigate } from "react-router-dom";

const Pago = () => {
  const [metodoSeleccionado, setMetodoSeleccionado] = useState("");
  const navigate = useNavigate();
  const location = useLocation();

  const usuario = location.state?.usuario;
  const suscripcion = location.state?.suscripcion;

  useEffect(() => {
    if (!usuario || !suscripcion) {
      alert("⚠️ Debes seleccionar una suscripción antes de pagar");
      navigate("/suscripciones");
    }
  }, [usuario, suscripcion, navigate]);

  const metodosPago = [
    { id: 1, nombre: "Tarjeta de crédito" },
    { id: 2, nombre: "PSE" },
    { id: 3, nombre: "Nequi" },
    { id: 4, nombre: "Daviplata" },
    { id: 5, nombre: "Efectivo" },
  ];

  const handlePagar = () => {
    alert(`✅ Pago registrado usando: ${metodoSeleccionado}`);
    navigate("/peliculas");
  };

  return (
    <div className="pago-background">
      <div className="pago-container">
        <h1>Confirmar pago</h1>

        <div className="info-box">
          <p>
            <strong>Usuario:</strong> {usuario?.primerNombre}{" "}
            {usuario?.primerApellido}
          </p>
          <p>
            <strong>Suscripción:</strong> {suscripcion?.tipoSuscripcion}
          </p>
          <p>
            <strong>Precio:</strong> ${suscripcion?.precio}
          </p>
        </div>

        <h2>Selecciona un método de pago:</h2>

        <div className="metodos-container">
          {metodosPago.map((m) => (
            <label key={m.id} className="metodo-option">
              <input
                type="radio"
                name="metodoPago"
                value={m.nombre}
                checked={metodoSeleccionado === m.nombre}
                onChange={(e) => setMetodoSeleccionado(e.target.value)}
              />
              <span>{m.nombre}</span>
            </label>
          ))}
        </div>

        <button
          className="btn-pagar"
          onClick={handlePagar}
          disabled={!metodoSeleccionado}
        >
          Pagar
        </button>
      </div>
    </div>
  );
};

export default Pago;
