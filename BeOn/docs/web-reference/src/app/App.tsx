import { BrowserRouter, Routes, Route } from "react-router";
import { Home } from "./pages/Home";
import { ContentDetail } from "./pages/ContentDetail";
import { Player } from "./pages/Player";

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/content/:id" element={<ContentDetail />} />
        <Route path="/player/:id" element={<Player />} />
      </Routes>
    </BrowserRouter>
  );
}
