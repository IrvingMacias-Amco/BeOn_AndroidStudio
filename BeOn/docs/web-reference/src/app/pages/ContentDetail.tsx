import { motion } from "motion/react";
import { useNavigate, useParams } from "react-router";
import { Play, Plus, ThumbsUp, Share2, Download, ChevronLeft } from "lucide-react";
import { StreamingNav } from "../components/streaming/StreamingNav";
import { HorizontalCarousel } from "../components/streaming/HorizontalCarousel";

export function ContentDetail() {
  const navigate = useNavigate();
  const { id } = useParams();

  // Mock content data - in production this would come from an API
  const content = {
    title: "Friends",
    year: "1994",
    seasons: "10 Temporadas",
    imdbRating: "8.8",
    rating: "TV-14",
    ranking: { position: 2, category: "series de Comedia" },
    genres: ["Comedia", "Romance", "Sitcom"],
    synopsis: "Seis amigos navegan por las complejidades de la vida, el amor y las carreras en la ciudad de Nueva York. Entre risas, momentos emotivos y cafÃ© sin fin en Central Perk, esta icÃ³nica serie de comedia captura la esencia de la amistad y las experiencias compartidas de una generaciÃ³n.",
    cast: ["Jennifer Aniston", "Courteney Cox", "Lisa Kudrow", "Matt LeBlanc", "Matthew Perry", "David Schwimmer"],
    director: "David Crane, Marta Kauffman",
    producer: "Warner Bros. Television",
    contentTags: "CÃ¡lida, Divertida, IcÃ³nica",
    audio: "EspaÃ±ol (5.1), InglÃ©s [Original], FrancÃ©s.",
    subtitles: "EspaÃ±ol, InglÃ©s, PortuguÃ©s, +20 mÃ¡s.",
    backgroundImage: "https://images.unsplash.com/photo-1627873649417-c67f701f1949?w=1600"
  };

  // Related content - at least 10 items for horizontal scrolling
  const similarContent = [
    { id: "1", title: "Modern Family", thumbnail: "https://images.unsplash.com/photo-1633793675529-58eecb6ea16f?w=400", year: "2009" },
    { id: "2", title: "The Office", thumbnail: "https://images.unsplash.com/photo-1588546506381-74592e9b8a2d?w=400", year: "2005" },
    { id: "3", title: "Brooklyn Nine-Nine", thumbnail: "https://images.unsplash.com/photo-1627873959341-905d35362273?w=400", year: "2013" },
    { id: "4", title: "Parks and Recreation", thumbnail: "https://images.unsplash.com/photo-1663775635512-c60be8b302b0?w=400", year: "2009" },
    { id: "5", title: "How I Met Your Mother", thumbnail: "https://images.unsplash.com/photo-1588672455574-c4d93ec7e525?w=400", year: "2005" },
    { id: "6", title: "New Girl", thumbnail: "https://images.unsplash.com/photo-1584208750035-f175342eb922?w=400", year: "2011" },
    { id: "7", title: "Scrubs", thumbnail: "https://images.unsplash.com/photo-1633793675529-58eecb6ea16f?w=400", year: "2001" },
    { id: "8", title: "Community", thumbnail: "https://images.unsplash.com/photo-1588546506381-74592e9b8a2d?w=400", year: "2009" },
    { id: "9", title: "30 Rock", thumbnail: "https://images.unsplash.com/photo-1627873959341-905d35362273?w=400", year: "2006" },
    { id: "10", title: "Arrested Development", thumbnail: "https://images.unsplash.com/photo-1663775635512-c60be8b302b0?w=400", year: "2003" }
  ];

  return (
    <div className="min-h-screen bg-black">
      {/* Navigation */}
      <StreamingNav />

      {/* Hero Section */}
      <div className="relative pt-[72px]">
        {/* Background Image */}
        <div className="relative w-full h-[675px] overflow-hidden">
          <img
            src={content.backgroundImage}
            alt={content.title}
            className="w-full h-full object-cover"
          />

          {/* Gradient Overlay */}
          <div
            className="absolute inset-0"
            style={{
              background: "linear-gradient(89.66deg, rgba(26, 25, 25, 0.9) 29.07%, rgba(26, 25, 25, 0.63) 98.69%)"
            }}
          />

          {/* Back Button - positioned at very top */}
          <motion.button
            className="absolute w-12 h-12 rounded-full flex items-center justify-center z-20"
            style={{
              left: "65px",
              top: "22px",
              backgroundColor: "rgba(0, 201, 80, 0.15)",
              border: "1px solid rgba(0, 201, 80, 0.2)",
              boxShadow: "0px 0px 10px rgba(34, 197, 94, 0.15)"
            }}
            whileHover={{
              scale: 1.1,
              backgroundColor: "rgba(0, 201, 80, 0.25)"
            }}
            whileTap={{ scale: 0.95 }}
            onClick={() => navigate("/")}
          >
            <ChevronLeft className="w-6 h-6 text-white" />
          </motion.button>

          {/* Content */}
          <div className="absolute inset-0 flex items-end pb-24 px-16">
            <div className="max-w-3xl space-y-4 mt-8">
              {/* Title */}
              <h1 className="text-white text-6xl font-bold leading-tight">
                {content.title}
              </h1>

              {/* Metadata */}
              <div className="flex items-center gap-4 text-base">
                <span className="text-[#d1d5dc] font-semibold">{content.year}</span>
                <span className="text-[#4a5565]">â€¢</span>
                <span className="text-[#d1d5dc]">{content.seasons}</span>
                <span className="text-[#4a5565]">â€¢</span>
                <div className="flex items-center gap-2">
                  <span className="text-[#fdc700] font-bold">IMDb</span>
                  <span className="text-[#d1d5dc] font-semibold">{content.imdbRating}</span>
                </div>
                <span className="text-[#4a5565]">â€¢</span>
                <span
                  className="px-2 py-1 rounded text-xs font-semibold text-[#d1d5dc]"
                  style={{ border: "1px solid rgba(209, 213, 220, 0.4)" }}
                >
                  {content.rating}
                </span>
              </div>

              {/* Ranking Badge with trending up icon */}
              {content.ranking && (
                <motion.div
                  className="flex items-center gap-3"
                  initial={{ opacity: 0, y: 10 }}
                  animate={{ opacity: 1, y: 0 }}
                  transition={{ delay: 0.3 }}
                >
                  <div
                    className="w-9 h-9 rounded-full flex items-center justify-center"
                    style={{ border: "1px solid rgba(5, 223, 114, 0.4)" }}
                  >
                    <svg className="w-5 h-5" viewBox="0 0 18 18" fill="none" stroke="#05DF72" strokeWidth="1.875">
                      <path d="M16.5 5.25L10.125 11.625L6.375 7.875L1.5 12.75" strokeLinecap="round" strokeLinejoin="round" />
                      <path d="M12 5.25H16.5V9.75" strokeLinecap="round" strokeLinejoin="round" />
                    </svg>
                  </div>
                  <span className="text-[#05df72] font-bold text-sm">
                    NÃºmero {content.ranking.position} en {content.ranking.category}
                  </span>
                </motion.div>
              )}

              {/* Action Buttons - 5 buttons matching Figma */}
              <div className="flex items-center gap-4">
                <motion.button
                  className="w-14 h-14 rounded-full flex items-center justify-center"
                  style={{ border: "2px solid rgba(255, 255, 255, 0.4)" }}
                  whileHover={{
                    scale: 1.1,
                    borderColor: "var(--accent-primary)"
                  }}
                  whileTap={{ scale: 0.95 }}
                >
                  <Plus className="w-6 h-6 text-white" strokeWidth={2.5} />
                </motion.button>

                <motion.button
                  className="w-14 h-14 rounded-full flex items-center justify-center"
                  style={{ border: "2px solid rgba(255, 255, 255, 0.4)" }}
                  whileHover={{
                    scale: 1.1,
                    borderColor: "var(--accent-primary)"
                  }}
                  whileTap={{ scale: 0.95 }}
                >
                  <ThumbsUp className="w-5.5 h-5.5 text-white" strokeWidth={2.29} />
                </motion.button>

                <motion.button
                  className="w-14 h-14 rounded-full flex items-center justify-center"
                  style={{ border: "2px solid rgba(255, 255, 255, 0.4)" }}
                  whileHover={{
                    scale: 1.1,
                    borderColor: "var(--accent-primary)"
                  }}
                  whileTap={{ scale: 0.95 }}
                >
                  <ThumbsUp className="w-5.5 h-5.5 text-white rotate-180" strokeWidth={2.29} />
                </motion.button>

                <motion.button
                  className="w-14 h-14 rounded-full flex items-center justify-center"
                  style={{ border: "2px solid rgba(255, 255, 255, 0.4)" }}
                  whileHover={{
                    scale: 1.1,
                    borderColor: "var(--accent-primary)"
                  }}
                  whileTap={{ scale: 0.95 }}
                >
                  <Share2 className="w-5.5 h-5.5 text-white" strokeWidth={2.29} />
                </motion.button>

                <motion.button
                  className="w-14 h-14 rounded-full flex items-center justify-center"
                  style={{ border: "2px solid rgba(255, 255, 255, 0.4)" }}
                  whileHover={{
                    scale: 1.1,
                    borderColor: "var(--accent-primary)"
                  }}
                  whileTap={{ scale: 0.95 }}
                >
                  <Download className="w-5.5 h-5.5 text-white" strokeWidth={2.29} />
                </motion.button>
              </div>

              {/* Genres */}
              <div className="flex gap-2">
                {content.genres.map((genre, index) => (
                  <span
                    key={index}
                    className="px-3 py-1.5 rounded-full text-sm text-white"
                    style={{ backgroundColor: "rgba(255, 255, 255, 0.1)" }}
                  >
                    {genre}
                  </span>
                ))}
              </div>

              {/* Synopsis - moved inside hero area */}
              <p className="text-[#d1d5dc] text-base leading-relaxed max-w-2xl">
                {content.synopsis}
              </p>

              {/* REPRODUCIR Button */}
              <motion.button
                className="px-12 py-4 rounded-full font-black text-black flex items-center gap-3"
                style={{
                  background: "var(--gradient-button-primary)",
                  boxShadow: "var(--shadow-glow-strong)"
                }}
                whileHover={{ scale: 1.05 }}
                whileTap={{ scale: 0.98 }}
                onClick={() => navigate(`/player/${id}`)}
              >
                <Play className="w-5 h-5 fill-black" />
                <span className="text-base">REPRODUCIR</span>
              </motion.button>
            </div>

            {/* Cast on the right side */}
            <div className="absolute right-16 bottom-24">
              <p className="text-base leading-relaxed max-w-[246px]" style={{ color: "rgba(209, 213, 220, 0.8)" }}>
                Elenco: {content.cast.slice(0, 3).join(", ")}
              </p>
            </div>
          </div>
        </div>
      </div>

      {/* MÃ¡s tÃ­tulos similares a este */}
      <div className="bg-black py-16">
        <HorizontalCarousel
          title="MÃ¡s tÃ­tulos similares a este"
          items={similarContent}
          onItemClick={(id) => navigate(`/content/${id}`)}
        />
      </div>

      {/* Content Details Section */}
      <div className="bg-black px-16 pb-16">
        {/* Green separator line */}
        <div className="h-px mb-12" style={{ backgroundColor: "#6EE0A1", maxWidth: "513px" }} />

        <div className="max-w-7xl">
          {/* Detalles heading */}
          <h2 className="text-white text-xl font-bold mb-12">Detalles</h2>

          {/* Details Grid */}
          <div className="grid grid-cols-2 gap-x-24 gap-y-12">
            {/* Left Column */}
            <div className="space-y-8">
              {/* Creadores */}
              <div>
                <p className="text-[#a0a0a0] text-sm mb-1">Creadores</p>
                <p className="text-white text-base">{content.director}</p>
              </div>

              {/* Cast */}
              <div>
                <p className="text-[#a0a0a0] text-sm mb-1">Elenco</p>
                <p className="text-white text-base">{content.cast.join(", ")}</p>
              </div>

              {/* Producer */}
              <div>
                <p className="text-[#a0a0a0] text-sm mb-1">Productora</p>
                <p className="text-white text-lg">{content.producer}</p>
              </div>

              {/* Classification */}
              <div>
                <p className="text-[#a0a0a0] text-sm mb-2">ClasificaciÃ³n</p>
                <div className="flex items-center gap-4">
                  <span
                    className="px-2 py-1 rounded text-sm font-semibold text-white"
                    style={{ border: "1px solid #6a7282" }}
                  >
                    {content.rating}
                  </span>
                  <span className="text-white text-base">Contenido sugerente, lenguaje.</span>
                </div>
              </div>
            </div>

            {/* Right Column */}
            <div className="space-y-8">
              {/* Content Tags */}
              <div>
                <p className="text-[#a0a0a0] text-sm mb-1">Esta serie es</p>
                <p className="text-white text-base">{content.contentTags}</p>
              </div>

              {/* Audio */}
              <div>
                <p className="text-[#a0a0a0] text-sm mb-1">Audio</p>
                <p className="text-white text-base">{content.audio}</p>
              </div>

              {/* Subtitles */}
              <div>
                <p className="text-[#a0a0a0] text-sm mb-1">SubtÃ­tulos</p>
                <p className="text-white text-base">{content.subtitles}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
