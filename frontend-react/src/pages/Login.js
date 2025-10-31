import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "../css/Login.css";

const Login = () => {
  const [correo, setCorreo] = useState("");
  const [contrasenia, setContrasenia] = useState("");
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(
        "http://localhost:8080/usuario/login",
        null,
        { params: { correo, contrasenia } }
      );

      // ✅ Guardar datos del usuario en el localStorage
      localStorage.setItem("usuario", JSON.stringify(response.data));

      alert("✅ Inicio de sesión exitoso");

      if (response.data.primeraVez) {
        navigate("/suscripciones");
      } else {
        navigate("/peliculas");
      }
    } catch (error) {
      alert("❌ Correo o contraseña incorrectos");
      console.error(error);
    }
  };

  const goToRegister = () => {
    navigate("/register");
  };

  return (
    <div className="login-container">
      <div className="overlay"></div>
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
