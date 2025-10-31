import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Suscripciones from "./pages/Suscripciones";
import Peliculas from "./pages/Peliculas";
import Pago from "./pages/Pago";


function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/suscripciones" element={<Suscripciones />} />
        <Route path="/peliculas" element={<Peliculas />} />
              <Route path="/pago" element={<Pago />} />

      </Routes>
    </Router>
  );
}

export default App;
