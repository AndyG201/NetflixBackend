import React, { useEffect, useState } from "react";
import axios from "axios";
import "../css/Peliculas.css";
import InfoModal from "../pages/InfoModal";
import VideoModal from "../pages/VideoModal";
import PuntosModal from "../pages/PuntosModal";

function Peliculas() {
  const [contenido, setContenido] = useState([]);
  const [contenidoFiltrado, setContenidoFiltrado] = useState([]);
  const [tipo, setTipo] = useState("todos");
  const [busqueda, setBusqueda] = useState("");
  const [peliculaSeleccionada, setPeliculaSeleccionada] = useState(null);
  const [videoUrl, setVideoUrl] = useState(null);
  const [modalPuntos, setModalPuntos] = useState({ visible: false, puntos: 0, mensaje: "" });

  // ✅ Obtener usuario y idUsuario desde localStorage
  const usuario = JSON.parse(localStorage.getItem("usuario"));
  const idUsuario = usuario?.id;

  const cargarContenido = async () => {
    try {
      const urls = [
        "http://localhost:8080/pelicula/obtenertodaspeliculas",
        "http://localhost:8080/serie/obtenertodaslasseries",
        "http://localhost:8080/documental/obtenertodosdocumentales",
      ];

      const peticiones = urls.map((u) => axios.get(u).catch(() => ({ data: [] })));
      const respuestas = await Promise.all(peticiones);

      const todoContenido = respuestas.flatMap((r, index) => {
        let tipo = "desconocido";
        if (urls[index].includes("pelicula")) tipo = "peliculas";
        if (urls[index].includes("serie")) tipo = "series";
        if (urls[index].includes("documental")) tipo = "documentales";

        return (r.data || []).map((item) => ({
          ...item,
          __tipo: tipo,
          __id:
            item.idPelicula ||
            item.idSerie ||
            item.idDocumental ||
            item.id ||
            Math.random(),
          nombre: item.nombre || item.titulo || "Sin título",
        }));
      });

      setContenido(todoContenido);
    } catch (error) {
      console.error("❌ Error cargando contenido:", error);
      setContenido([]);
    }
  };

  useEffect(() => {
    cargarContenido();
  }, []);

  useEffect(() => {
    const filtrados = contenido.filter((item) => {
      const coincideTipo = tipo === "todos" || item.__tipo === tipo;
      const coincideBusqueda = item.nombre?.toLowerCase().includes(busqueda.toLowerCase());
      return coincideTipo && coincideBusqueda;
    });
    setContenidoFiltrado(filtrados);
  }, [tipo, busqueda, contenido]);

  const registrarVisualizacion = async (item) => {
    if (!idUsuario) return;

    try {
      const payload = {
        idUsuario: parseInt(idUsuario),
        fechaVisualizacion: new Date().toISOString(),
      };

      if (item.__tipo === "peliculas") payload.idPelicula = item.idPelicula;
      if (item.__tipo === "series") payload.idSerie = item.idSerie;
      if (item.__tipo === "documentales") payload.idDocumental = item.idDocumental;

      await axios.post(`http://localhost:8080/usuariopelicula/agregaralhistorial`, payload);

      // 🎯 Sumar puntos al usuario
      const puntosGanados = Math.floor(Math.random() * 20) + 10;
      await axios.post(`http://localhost:8080/puntos/sumar/${idUsuario}`, { puntos: puntosGanados });

      setModalPuntos({
        visible: true,
        puntos: puntosGanados,
        mensaje: "¡Gracias por ver este contenido!",
      });
    } catch (error) {
      console.error("⚠️ Error al registrar visualización o sumar puntos:", error);
    }
  };

  const abrirInfo = (item) => setPeliculaSeleccionada(item);
  const cerrarInfo = () => setPeliculaSeleccionada(null);

  const reproducirVideo = (item) => {
    setPeliculaSeleccionada(null);
    const url = item.urlVideo || item.urlPelicula || item.urlDocumental || "";
    setVideoUrl(url);
    registrarVisualizacion(item);
  };

  const cerrarVideo = () => setVideoUrl(null);

  return (
    <div className="contenedor-peliculas">
      <header className="top-bar">
        <div className="logo">NETFLIX+</div>

        <div className="tabs">
          <button className={tipo === "todos" ? "tab-activa" : ""} onClick={() => setTipo("todos")}>Todos</button>
          <button className={tipo === "peliculas" ? "tab-activa" : ""} onClick={() => setTipo("peliculas")}>Películas</button>
          <button className={tipo === "series" ? "tab-activa" : ""} onClick={() => setTipo("series")}>Series</button>
          <button className={tipo === "documentales" ? "tab-activa" : ""} onClick={() => setTipo("documentales")}>Documentales</button>
          <button className="btn-premios" onClick={() => setModalPuntos({ visible: true })}>
            🏆 Mis premios
          </button>
        </div>

        <button className="btn-cerrar-sesion" onClick={() => { localStorage.clear(); window.location.href = "/"; }}>
          Cerrar sesión
        </button>
      </header>

      <div className="buscador-contenedor">
        <input
          type="text"
          placeholder="Buscar por título..."
          value={busqueda}
          onChange={(e) => setBusqueda(e.target.value)}
        />
      </div>

      <div className="grid-peliculas">
        {contenidoFiltrado.length > 0 ? (
          contenidoFiltrado.map((item) => (
            <div key={`${item.__tipo}-${item.__id}`} className="card-pelicula" onClick={() => abrirInfo(item)}>
              <img src={item.poster || "https://via.placeholder.com/200"} alt={item.nombre} />
              <div className="card-info"><h3>{item.nombre}</h3></div>
            </div>
          ))
        ) : (
          <p className="mensaje-vacio">No se encontraron resultados.</p>
        )}
      </div>

      <InfoModal pelicula={peliculaSeleccionada} onClose={cerrarInfo} onPlay={reproducirVideo} />
      <VideoModal videoUrl={videoUrl} onClose={cerrarVideo} />
      <PuntosModal visible={modalPuntos.visible} mensaje={modalPuntos.mensaje} onClose={() => setModalPuntos({ visible: false })} />
    </div>
  );
}

export default Peliculas;
