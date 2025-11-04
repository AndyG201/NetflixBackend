import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "../css/Login.css";
import fondo from "../img/fondo1.jpg";

const Login = () => {
  const [correo, setCorreo] = useState("");
  const [contrasenia, setContrasenia] = useState("");
  const [errorMsg, setErrorMsg] = useState(""); // ✅ Para mensaje tipo Netflix
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post("http://localhost:8080/usuario/login", {
        correo,
        contrasenia,
      });

      const usuario = response.data;
      localStorage.setItem("usuario", JSON.stringify(usuario));

      if (usuario.primeraVez) {
        navigate("/suscripciones");
      } else {
        navigate("/peliculas");
      }

    } catch (error) {
      console.error("❌ Error al iniciar sesión:", error.response || error);

      // ✅ Mostrar mensaje y ocultarlo después de 3s
      setErrorMsg("¡Correo o contraseña incorrectos!");
      setTimeout(() => setErrorMsg(""), 3000);
    }
  };

  const goToRegister = () => {
    navigate("/register");
  };

  return (
    <div
      className="login-container"
      style={{
        backgroundImage: `url(${fondo})`,
        backgroundSize: "cover",
        backgroundPosition: "center",
        height: "100vh",
      }}
    >
      <div className="overlay"></div>

      {/* ✅ Mensaje estilo Netflix */}
      {errorMsg && <div className="netflix-error">{errorMsg}</div>}

      <form className="login-form" onSubmit={handleLogin}>
        <h1 className="login-title">Iniciar sesión</h1>

        <input
          type="email"
          placeholder="Correo electrónico"
          value={correo}
          onChange={(e) => setCorreo(e.target.value)}
          required
        />
        <input
          type="password"
          placeholder="Contraseña"
          value={contrasenia}
          onChange={(e) => setContrasenia(e.target.value)}
          required
        />

        <button type="submit" className="login-btn">
          Iniciar sesión
        </button>

        <p className="register-text">
          ¿Nuevo en Netflix?{" "}
          <span onClick={goToRegister} className="register-link">
            Regístrate ahora
          </span>
        </p>
      </form>
    </div>
  );
};

export default Login;
