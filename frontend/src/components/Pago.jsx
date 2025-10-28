import React, { useState } from "react";
import axios from "axios";
import "../css/Pago.css";
import { useLocation, useNavigate } from "react-router-dom";

const Pago = () => {
    const location = useLocation();
    const navigate = useNavigate();

    // Obtener usuario y suscripción desde el estado o localStorage
    const usuario = location.state?.usuario || JSON.parse(localStorage.getItem("usuario"));
    const suscripcion = location.state?.suscripcion;

    const [metodoPago, setMetodoPago] = useState("");
    const [tarjeta, setTarjeta] = useState("");
    const [correoPSE, setCorreoPSE] = useState("");
    const [mensaje, setMensaje] = useState("");
    const [loading, setLoading] = useState(false);

    const validarCampos = () => {
        if (!metodoPago) return "Debes seleccionar un método de pago.";
        if (metodoPago === "tarjeta" && !/^\d{16}$/.test(tarjeta))
            return "La tarjeta debe tener 16 dígitos.";
        if (metodoPago === "pse" && !correoPSE.includes("@"))
            return "Debes ingresar un correo válido para PSE.";
        return null;
    };

    const handlePago = async () => {
        const error = validarCampos();
        if (error) {
            alert(error);
            return;
        }

        if (!usuario || !suscripcion) {
            alert("Faltan datos del usuario o la suscripción.");
            return;
        }

        setLoading(true);
        setMensaje("");

        try {
            // 🧾 1️⃣ Crear el objeto de pago con la estructura exacta que espera el backend
            const pagoDTO = {
                fechaMaxima: new Date().toISOString().split('T')[0], // formato YYYY-MM-DD
                referencia: Math.random().toString(36).substring(2, 10).toUpperCase(), // referencia aleatoria
                monto: suscripcion.precio,
                idUsuario: usuario.id,
                idSuscripcion: suscripcion.id,
                idEstadoPago: 1,   // 1 = Pendiente
                idMetodoPago: 1    // 1 = Tarjeta (puedes cambiar según método)
            };

            console.log("📤 Enviando pago al backend:", pagoDTO);

            // 📤 2️⃣ Enviar la solicitud al backend
            const crearResponse = await axios.post("http://localhost:8080/pago", pagoDTO);

            // ✅ 3️⃣ Obtener la referencia generada por el backend (si no viene en la respuesta)
            let referencia = crearResponse.data?.referencia;

            if (!referencia) {
                const refResponse = await axios.get(
                    `http://localhost:8080/pago/ultima-referencia?idUsuario=${usuario.id}`
                );
                referencia = refResponse.data?.referencia;
            }

            if (!referencia) {
                setMensaje("⚠️ No se pudo obtener la referencia del pago.");
                setLoading(false);
                return;
            }

            // 📧 4️⃣ Solicitar al backend que genere y envíe el PDF al correo del usuario
            const pdfResponse = await axios.get(
                `http://localhost:8080/pago?referencia=${referencia}`
            );

            if (pdfResponse.status === 202 || pdfResponse.status === 200) {
                setMensaje("✅ Pago exitoso. Recibirás tu factura por correo electrónico.");
                setTimeout(() => navigate("/peliculas"), 2500);
            } else {
                setMensaje("⚠️ El pago se registró, pero no se pudo generar el recibo.");
            }
        } catch (error) {
            console.error("❌ Error al registrar pago:", error);
            setMensaje("❌ No se pudo realizar el pago. Intenta nuevamente.");
        } finally {
            setLoading(false);
        }
    };

    if (!usuario || !suscripcion) {
        return <p className="mensaje error">Faltan datos del usuario o suscripción.</p>;
    }

    return (
        <div className="pago-container">
            <div className="pago-card">
                <h2 className="titulo">Resumen de pago</h2>

                <p><strong>Usuario:</strong> {usuario.primerNombre} {usuario.primerApellido}</p>
                <p><strong>Correo:</strong> {usuario.correo}</p>
                <p><strong>Suscripción:</strong> {suscripcion.tipoSuscripcion}</p>
                <p><strong>Total a pagar:</strong> ${suscripcion.precio}</p>

                <hr />
                <label htmlFor="metodoPago">Método de pago</label>
                <select
                    id="metodoPago"
                    value={metodoPago}
                    onChange={(e) => setMetodoPago(e.target.value)}
                >
                    <option value="">Seleccione...</option>
                    <option value="tarjeta">Tarjeta</option>
                    <option value="pse">PSE</option>
                    <option value="nequi">Nequi</option>
                    <option value="daviplata">Daviplata</option>
                    <option value="efectivo">Efectivo</option>
                </select>

                {metodoPago === "tarjeta" && (
                    <div className="campo-dinamico">
                        <label>Número de tarjeta</label>
                        <input
                            type="text"
                            placeholder="XXXX-XXXX-XXXX-XXXX"
                            maxLength="16"
                            value={tarjeta}
                            onChange={(e) => setTarjeta(e.target.value)}
                        />
                    </div>
                )}

                {metodoPago === "pse" && (
                    <div className="campo-dinamico">
                        <label>Correo asociado</label>
                        <input
                            type="email"
                            placeholder="correo@banco.com"
                            value={correoPSE}
                            onChange={(e) => setCorreoPSE(e.target.value)}
                        />
                    </div>
                )}

                {(metodoPago === "nequi" || metodoPago === "daviplata") && (
                    <div className="campo-dinamico">
                        <label>Número asociado:</label>
                        <input type="text" value={usuario.telefono || ""} disabled />
                    </div>
                )}

                <button
                    className="btn-pago"
                    onClick={handlePago}
                    disabled={loading}
                >
                    {loading ? "Procesando..." : "Realizar pago"}
                </button>

                {mensaje && <p className="mensaje">{mensaje}</p>}
            </div>
        </div>
    );
};

export default Pago;
