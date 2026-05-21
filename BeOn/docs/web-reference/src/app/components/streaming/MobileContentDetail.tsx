import { motion } from "motion/react";
import { Play, Plus, ThumbsUp, Share2, Download, ChevronLeft } from "lucide-react";
import { MobileBottomNav } from "./MobileBottomNav";
import beonLogo from "@/imports/beon_logo_prev.png";
import type { MobilePlatform } from "./MobileHome";

interface MobileContentDetailProps {
  platform: MobilePlatform;
  onBack?: () => void;
  onPlay?: () => void;
}

export function MobileContentDetail({ platform, onBack, onPlay }: MobileContentDetailProps) {
  const isIOS = platform === "ios";

  // Mock content data
  const content = {
    title: "Friends",
    year: "1994",
    seasons: "10 Temporadas",
    imdbRating: "8.8",
    rating: "TV-14",
    ranking: { position: 2, category: "series de Comedia" },
    genres: ["Comedia", "Romance", "Sitcom"],
    synopsis: "Seis amigos navegan por las complejidades de la vida, el amor y las carreras en la ciudad de Nueva York. Entre risas, momentos emotivos y cafÃ© sin fin en Central Perk, esta icÃ³nica serie de comedia captura la esencia de la amistad y las experiencias compartidas de una generaciÃ³n.",
    cast: "Jennifer Aniston, Courteney Cox, Lisa Kudrow, Matt LeBlanc, Matthew Perry, David Schwimmer",
    creators: "David Crane, Marta Kauffman",
    producer: "Warner Bros. Television",
    classification: "TV-14",
    classificationDescription: "Contenido sugerente, lenguaje.",
    contentTags: "CÃ¡lida, Divertida, IcÃ³nica",
    audio: "EspaÃ±ol (5.1), InglÃ©s [Original], FrancÃ©s.",
    subtitles: "EspaÃ±ol, InglÃ©s, PortuguÃ©s, +20 mÃ¡s.",
    backgroundImage: "https://images.unsplash.com/photo-1627873649417-c67f701f1949?w=1600"
  };

  // Related content - horizontal cards
  const similarContent = [
    { id: "1", title: "Modern Family", thumbnail: "https://images.unsplash.com/photo-1633793675529-58eecb6ea16f?w=400" },
    { id: "2", title: "The Office", thumbnail: "https://images.unsplash.com/photo-1588546506381-74592e9b8a2d?w=400" },
    { id: "3", title: "Brooklyn Nine-Nine", thumbnail: "https://images.unsplash.com/photo-1627873959341-905d35362273?w=400" },
    { id: "4", title: "Parks and Recreation", thumbnail: "https://images.unsplash.com/photo-1663775635512-c60be8b302b0?w=400" },
    { id: "5", title: "How I Met Your Mother", thumbnail: "https://images.unsplash.com/photo-1588672455574-c4d93ec7e525?w=400" },
    { id: "6", title: "New Girl", thumbnail: "https://images.unsplash.com/photo-1633793675529-58eecb6ea16f?w=400" },
    { id: "7", title: "Scrubs", thumbnail: "https://images.unsplash.com/photo-1588546506381-74592e9b8a2d?w=400" },
    { id: "8", title: "Community", thumbnail: "https://images.unsplash.com/photo-1627873959341-905d35362273?w=400" },
    { id: "9", title: "30 Rock", thumbnail: "https://images.unsplash.com/photo-1663775635512-c60be8b302b0?w=400" },
    { id: "10", title: "Arrested Development", thumbnail: "https://images.unsplash.com/photo-1588672455574-c4d93ec7e525?w=400" },
  ];

  /* â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€ STATUS BAR â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€ */
  function IOSStatusBar() {
    return (
      <div className="flex items-center justify-between px-6 pt-3 pb-1 shrink-0" style={{ height: 44 }}>
        <span className="text-white font-semibold text-sm">9:41</span>
        <div className="flex items-center gap-1.5">
          <div className="flex items-end gap-0.5 h-3">
            {[3, 5, 7, 9].map((h, i) => (
              <div key={i} className="w-1 rounded-sm bg-white" style={{ height: h }} />
            ))}
          </div>
          <svg width="15" height="11" viewBox="0 0 15 11" fill="none">
            <path d="M7.5 10a1 1 0 100-2 1 1 0 000 2z" fill="white"/>
            <path d="M4.5 7.5C5.4 6.6 6.4 6 7.5 6s2.1.6 3 1.5" stroke="white" strokeWidth="1.2" strokeLinecap="round"/>
            <path d="M1.5 4.5C3.3 2.7 5.3 1.5 7.5 1.5s4.2 1.2 6 3" stroke="white" strokeWidth="1.2" strokeLinecap="round"/>
          </svg>
          <div className="flex items-center gap-0.5">
            <div className="w-6 h-3 rounded-sm border border-white/80 relative overflow-hidden">
              <div className="absolute left-0 top-0 bottom-0 bg-white rounded-sm" style={{ width: "80%" }} />
            </div>
            <div className="w-0.5 h-1.5 rounded-r-sm bg-white/60" />
          </div>
        </div>
      </div>
    );
  }

  function AndroidStatusBar() {
    return (
      <div className="flex items-center justify-between px-4 shrink-0" style={{ height: 28 }}>
        <span className="text-white font-medium" style={{ fontSize: 11 }}>9:41</span>
        <div className="flex items-center gap-1">
          <svg width="12" height="10" viewBox="0 0 12 10" fill="white">
            <path d="M6 9a.75.75 0 100-1.5A.75.75 0 006 9zM3.2 6.3C4 5.5 4.9 5 6 5s2 .5 2.8 1.3" stroke="white" strokeWidth="1" fill="none" strokeLinecap="round"/>
            <path d="M1 3.5C2.7 1.8 4.2.5 6 .5S9.3 1.8 11 3.5" stroke="white" strokeWidth="1" fill="none" strokeLinecap="round"/>
          </svg>
          <svg width="12" height="10" viewBox="0 0 12 10" fill="none">
            <rect x="1" y="2.5" width="8" height="5" rx="1" stroke="white" strokeWidth="1"/>
            <rect x="9" y="4" width="2" height="2" rx="0.5" fill="white" opacity="0.5"/>
            <rect x="1.5" y="3" width="5.5" height="4" rx="0.5" fill="white"/>
          </svg>
        </div>
      </div>
    );
  }

  return (
    <div
      className="flex flex-col w-full h-full overflow-hidden"
      style={{ backgroundColor: "var(--color-bg-base)", fontFamily: "Inter, system-ui, sans-serif" }}
    >
      {/* Status bar */}
      {isIOS ? <IOSStatusBar /> : <AndroidStatusBar />}

      {/* Scrollable content */}
      <div className="flex-1 overflow-y-auto overflow-x-hidden scrollbar-hide">
        {/* Hero Section with all content inside */}
        <div className="relative w-full bg-black" style={{ minHeight: 500 }}>
          {/* Background Image */}
          <div className="absolute inset-0" style={{ height: 380 }}>
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
          </div>

          {/* Back Button - positioned at very top */}
          <motion.button
            className="absolute flex items-center justify-center rounded-full z-20"
            style={{
              left: 24,
              top: 8,
              width: 48,
              height: 48,
              backgroundColor: "rgba(0, 201, 80, 0.15)",
              border: "1px solid rgba(0, 201, 80, 0.2)",
              boxShadow: "0px 0px 10px rgba(34, 197, 94, 0.15)"
            }}
            whileTap={{ scale: 0.9 }}
            onClick={onBack}
          >
            <ChevronLeft className="w-6 h-6 text-white" strokeWidth={2} />
          </motion.button>

          {/* Content inside hero */}
          <div className="relative px-6 pt-40 pb-6">
            {/* Title */}
            <h1 className="font-bold leading-tight text-white mb-3" style={{ fontSize: 28, letterSpacing: "-0.5px" }}>
              {content.title}
            </h1>

            {/* Metadata Row */}
            <div className="flex items-center gap-3 mb-3 flex-wrap">
              <span className="font-semibold text-sm" style={{ color: "#d1d5dc" }}>{content.year}</span>
              <span style={{ color: "#4a5565", fontSize: 14 }}>â€¢</span>
              <span className="text-sm" style={{ color: "#d1d5dc" }}>{content.seasons}</span>
              <span style={{ color: "#4a5565", fontSize: 14 }}>â€¢</span>
              <div className="flex items-center gap-1.5">
                <span className="font-bold text-sm" style={{ color: "#fdc700" }}>IMDb</span>
                <span className="font-semibold text-sm" style={{ color: "#d1d5dc" }}>{content.imdbRating}</span>
              </div>
              <span style={{ color: "#4a5565", fontSize: 14 }}>â€¢</span>
              <span
                className="px-2 py-0.5 rounded text-xs font-semibold"
                style={{
                  color: "#d1d5dc",
                  border: "1px solid rgba(209, 213, 220, 0.4)"
                }}
              >
                {content.rating}
              </span>
            </div>

            {/* Ranking Badge */}
            {content.ranking && (
              <div className="flex items-center gap-2 mb-3">
                <div
                  className="w-9 h-9 rounded-full flex items-center justify-center"
                  style={{ border: "1px solid rgba(5, 223, 114, 0.4)" }}
                >
                  <svg className="w-5 h-5" viewBox="0 0 18 18" fill="none" stroke="#05DF72" strokeWidth="1.875">
                    <path d="M15.9375 0.9375L9.5625 7.3125L5.8125 3.5625L0.9375 8.4375" strokeLinecap="round" strokeLinejoin="round" />
                    <path d="M0.9375 0.9375H5.4375V5.4375" strokeLinecap="round" strokeLinejoin="round" transform="translate(11 2)" />
                  </svg>
                </div>
                <span className="text-sm font-bold" style={{ color: "#05df72" }}>
                  NÃºmero {content.ranking.position} en {content.ranking.category}
                </span>
              </div>
            )}

            {/* Action Buttons Row - 5 buttons matching Figma */}
            <div className="flex items-center gap-4 mb-3">
              {/* Mi Lista */}
              <motion.button
                className="w-14 h-14 rounded-full flex items-center justify-center"
                style={{ border: "2px solid rgba(255,255,255,0.4)" }}
                whileTap={{ scale: 0.9 }}
              >
                <Plus className="w-6 h-6 text-white" strokeWidth={2.5} />
              </motion.button>

              {/* Me gusta */}
              <motion.button
                className="w-14 h-14 rounded-full flex items-center justify-center"
                style={{ border: "2px solid rgba(255,255,255,0.4)" }}
                whileTap={{ scale: 0.9 }}
              >
                <ThumbsUp className="w-5.5 h-5.5 text-white" strokeWidth={2.29} />
              </motion.button>

              {/* Dislike */}
              <motion.button
                className="w-14 h-14 rounded-full flex items-center justify-center"
                style={{ border: "2px solid rgba(255,255,255,0.4)" }}
                whileTap={{ scale: 0.9 }}
              >
                <ThumbsUp className="w-5.5 h-5.5 text-white rotate-180" strokeWidth={2.29} />
              </motion.button>

              {/* Compartir */}
              <motion.button
                className="w-14 h-14 rounded-full flex items-center justify-center"
                style={{ border: "2px solid rgba(255,255,255,0.4)" }}
                whileTap={{ scale: 0.9 }}
              >
                <Share2 className="w-5.5 h-5.5 text-white" strokeWidth={2.29} />
              </motion.button>

              {/* Descargar */}
              <motion.button
                className="w-14 h-14 rounded-full flex items-center justify-center"
                style={{ border: "2px solid rgba(255,255,255,0.4)" }}
                whileTap={{ scale: 0.9 }}
              >
                <Download className="w-5.5 h-5.5 text-white" strokeWidth={2.29} />
              </motion.button>
            </div>

            {/* Genres */}
            <div className="flex gap-2 mb-4 flex-wrap">
              {content.genres.map((genre) => (
                <span
                  key={genre}
                  className="px-3 py-1 rounded-full text-sm font-medium text-white"
                  style={{ backgroundColor: "rgba(255,255,255,0.1)" }}
                >
                  {genre}
                </span>
              ))}
            </div>

            {/* Synopsis - inside hero */}
            <p className="text-sm leading-relaxed mb-6" style={{ color: "#d1d5dc", lineHeight: "26px" }}>
              {content.synopsis}
            </p>

            {/* REPRODUCIR Button */}
            <motion.button
              className="flex items-center justify-center gap-3 w-full rounded-full font-bold text-lg text-black"
              style={{
                background: "linear-gradient(90deg, rgb(94, 234, 212) 0%, rgb(132, 204, 22) 100%)",
                height: 60,
                boxShadow: "0px 10px 15px rgba(34,197,94,0.4)"
              }}
              whileTap={{ scale: 0.97 }}
              onClick={onPlay}
            >
              <Play className="w-5 h-5 fill-black" />
              <span style={{ letterSpacing: "0.5px" }}>REPRODUCIR</span>
            </motion.button>
          </div>
        </div>

        {/* Main content area */}
        <div className="px-6 bg-black">
          {/* MÃ¡s tÃ­tulos similares a este */}
          <h2 className="font-bold leading-7 pt-6 pb-6 text-white" style={{ fontSize: 20 }}>
            MÃ¡s tÃ­tulos similares a este
          </h2>
          <div className="flex gap-4 overflow-x-auto scrollbar-hide pb-2 -mx-6 px-6 mb-6">
            {similarContent.map((item) => (
              <motion.div
                key={item.id}
                className="shrink-0 rounded-xl overflow-hidden"
                style={{
                  width: 320,
                  height: 180,
                  boxShadow: "0px 10px 15px -3px rgba(0,0,0,0.1), 0px 4px 6px -4px rgba(0,0,0,0.1)"
                }}
                whileTap={{ scale: 0.97 }}
              >
                <img src={item.thumbnail} alt={item.title} className="w-full h-full object-cover" />
              </motion.div>
            ))}
          </div>

          {/* Separator Line */}
          <div className="h-px mb-12" style={{ backgroundColor: "#6EE0A1" }} />

          {/* Detalles Section */}
          <div className="pb-12">
            <h2 className="font-bold leading-7 text-white mb-8" style={{ fontSize: 20 }}>
              Detalles
            </h2>

            <div className="space-y-8">
              {/* Creadores */}
              <div>
                <p className="text-sm font-medium leading-5 mb-0.5" style={{ color: "#a0a0a0" }}>Creadores</p>
                <p className="text-base leading-7 text-white">{content.creators}</p>
              </div>

              {/* Elenco */}
              <div>
                <p className="text-sm font-medium leading-5 mb-0.5" style={{ color: "#a0a0a0" }}>Elenco</p>
                <p className="text-base leading-7 text-white">{content.cast}</p>
              </div>

              {/* Productora */}
              <div>
                <p className="text-sm font-medium leading-5 mb-0.5" style={{ color: "#a0a0a0" }}>Productora</p>
                <p className="text-lg leading-7 text-white">{content.producer}</p>
              </div>

              {/* ClasificaciÃ³n */}
              <div>
                <p className="text-sm font-medium leading-5 mb-0.5" style={{ color: "#a0a0a0" }}>ClasificaciÃ³n</p>
                <div className="flex items-center gap-3">
                  <span
                    className="px-2 py-0.5 rounded text-sm font-semibold text-white"
                    style={{ border: "1px solid #6a7282" }}
                  >
                    {content.classification}
                  </span>
                  <span className="text-base leading-6 text-white">{content.classificationDescription}</span>
                </div>
              </div>

              {/* Esta serie es */}
              <div>
                <p className="text-sm font-medium leading-5 mb-0.5" style={{ color: "#a0a0a0" }}>Esta serie es</p>
                <p className="text-base leading-7 text-white">{content.contentTags}</p>
              </div>

              {/* Audio */}
              <div>
                <p className="text-sm font-medium leading-5 mb-0.5" style={{ color: "#a0a0a0" }}>Audio</p>
                <p className="text-base leading-7 text-white">{content.audio}</p>
              </div>

              {/* SubtÃ­tulos */}
              <div>
                <p className="text-sm font-medium leading-5 mb-0.5" style={{ color: "#a0a0a0" }}>SubtÃ­tulos</p>
                <p className="text-base leading-7 text-white">{content.subtitles}</p>
              </div>
            </div>
          </div>

          {/* Footer */}
          <div className="flex flex-col items-center pb-12 gap-8 pt-12 border-t" style={{ borderColor: "rgba(0, 201, 80, 0.2)" }}>
            <img src={beonLogo} alt="BEOn" className="h-10 w-auto object-contain" />
            <p className="text-sm leading-5 text-center" style={{ color: "#a0a0a0" }}>
              TÃ©rminos y Aviso de Privacidad â€¢ Comentarios â€¢ Ayuda
            </p>
          </div>

          {/* Bottom spacer for nav */}
          <div className="h-4" />
        </div>
      </div>

      {/* Bottom navigation */}
      <MobileBottomNav activeTab="inicio" />
    </div>
  );
}
