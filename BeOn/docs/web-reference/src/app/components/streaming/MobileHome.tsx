import { useState, useRef } from "react";
import { motion } from "motion/react";
import { AnimatePresence } from "motion/react";
import { Play, Plus, ThumbsUp, Search, ChevronRight } from "lucide-react";
import beonLogo from "@/imports/beon_logo_prev.png";
import { MobileBottomNav } from "./MobileBottomNav";

export type MobilePlatform = "ios" | "android";

interface MobileHomeProps {
  platform: MobilePlatform;
}

const heroSlides = [
  {
    id: "1",
    title: "Marty Supremo",
    description: "Un agente deportivo con mÃ¡s estilo que Ã©tica descubre una Ãºltima oportunidad de redenciÃ³n cuando conoce a un joven talento del ping-pong. Entre apuestas arriesgadas, rivalidades feroces y una transformaciÃ³n personal inesperada, Marty deberÃ¡ decidir si vale la pena ganar a cualquier precio o si hay algo mÃ¡s importante que el Ã©xito.",
    image: "https://images.unsplash.com/photo-1745948080908-b7e5fe4cba90?w=600",
    year: "2025",
    duration: "2h 29min",
    rating: "PG-13",
    imdb: "7.5",
    genres: ["Comedia", "Deporte", "Drama"],
    ranking: { position: 5, category: "pelÃ­culas de Drama" }
  },
  {
    id: "2",
    title: "Urban Legends",
    description: "En las calles oscuras de la ciudad, las leyendas cobran vida. Un grupo de investigadores descubre que los mitos urbanos que aterrorizan a la metrÃ³polis tienen un origen mÃ¡s real y siniestro de lo que nadie imaginaba.",
    image: "https://images.unsplash.com/photo-1758405282251-26903f4b7fcb?w=600",
    year: "2025",
    duration: "2h 05min",
    rating: "R",
    imdb: "8.2",
    genres: ["Suspenso", "Terror", "Misterio"],
    ranking: { position: 2, category: "pelÃ­culas de Terror" }
  },
  {
    id: "3",
    title: "Neon Nights",
    description: "Una ciudad que nunca duerme, secretos que nunca descansan. En el corazÃ³n pulsante de la metrÃ³polis nocturna, un detective debe resolver el caso mÃ¡s complejo de su carrera mientras navega por un mundo de luces de neÃ³n y sombras profundas.",
    image: "https://images.unsplash.com/photo-1762417419967-d5ccd2ebe463?w=600",
    year: "2024",
    duration: "1h 52min",
    rating: "PG-13",
    imdb: "7.8",
    genres: ["AcciÃ³n", "Thriller", "Ciencia FicciÃ³n"]
  },
  {
    id: "4",
    title: "Speed Demon",
    description: "Cuando la velocidad es tu Ãºnica salvaciÃ³n y cada segundo cuenta. Un corredor de motos callejeras debe enfrentarse a su pasado mientras compite en la carrera mÃ¡s peligrosa de su vida para salvar a quienes mÃ¡s ama.",
    image: "https://images.unsplash.com/photo-1627736990081-602486bcb4d3?w=600",
    year: "2025",
    duration: "2h 05min",
    rating: "PG-13",
    imdb: "7.9",
    genres: ["AcciÃ³n", "Drama", "Deporte"],
    ranking: { position: 1, category: "pelÃ­culas de AcciÃ³n" }
  },
  {
    id: "5",
    title: "The Last Performance",
    description: "La mÃºsica puede cambiar vidas, pero Â¿puede salvar almas? Un mÃºsico en declive encuentra una Ãºltima oportunidad de redenciÃ³n cuando descubre un talento extraordinario que le hace recordar por quÃ© empezÃ³ a tocar.",
    image: "https://images.unsplash.com/photo-1628242681477-f8dd17bb0962?w=600",
    year: "2024",
    duration: "1h 40min",
    rating: "PG",
    imdb: "8.5",
    genres: ["Drama", "MÃºsica", "Inspiracional"],
    ranking: { position: 3, category: "pelÃ­culas de Drama" }
  }
];

const continueWatching = [
  { id: "19", title: "Dark Pursuit", thumbnail: "https://images.unsplash.com/photo-1745948080908-b7e5fe4cba90?w=400", progress: 25 },
  { id: "20", title: "Urban Legends", thumbnail: "https://images.unsplash.com/photo-1758405282251-26903f4b7fcb?w=400", progress: 50 },
  { id: "21", title: "Speed Demon", thumbnail: "https://images.unsplash.com/photo-1627736990081-602486bcb4d3?w=400", progress: 75 },
  { id: "22", title: "Night Ops", thumbnail: "https://images.unsplash.com/photo-1693645325862-cf4fc2db0e29?w=400", progress: 10 },
];

const recommended = [
  { id: "11", title: "Cinema Dreams", thumbnail: "https://images.unsplash.com/photo-1688678004647-945d5aaf91c1?w=300" },
  { id: "12", title: "Theater Nights", thumbnail: "https://images.unsplash.com/photo-1635400138431-0bbde4d01484?w=300" },
  { id: "13", title: "The Screening", thumbnail: "https://images.unsplash.com/photo-1668890094751-6986d0ca9dfc?w=300" },
  { id: "14", title: "Premiere Night", thumbnail: "https://images.unsplash.com/photo-1640127249305-793865c2efe1?w=300" },
  { id: "15", title: "Classic Films", thumbnail: "https://images.unsplash.com/photo-1626814026160-2237a95fc5a0?w=300" },
];

const navItems = ["Inicio", "GuÃ­a de TV", "PelÃ­culas", "Series", "Deportes"];



function ProgressBar({ progress }: { progress: number }) {
  return (
    <div className="h-[3px] rounded-full mt-2" style={{ backgroundColor: "var(--color-border-default)" }}>
      <div
        className="h-full rounded-full"
        style={{
          width: `${progress}%`,
          background: "var(--gradient-progress-continue)",
        }}
      />
    </div>
  );
}

function HeroSection({ platform }: { platform: MobilePlatform }) {
  const [activeSlide, setActiveSlide] = useState(0);
  const slide = heroSlides[activeSlide];

  return (
    <div className="relative w-full" style={{ height: "72vw", minHeight: 260, maxHeight: 420 }}>
      <AnimatePresence mode="wait">
        <motion.div
          key={slide.id}
          className="absolute inset-0"
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
          exit={{ opacity: 0 }}
          transition={{ duration: 0.5 }}
        >
          <img
            src={slide.image}
            alt={slide.title}
            className="w-full h-full object-cover"
          />
          {/* Bottom gradient */}
          <div
            className="absolute inset-0"
            style={{
              background: "linear-gradient(to bottom, rgba(0,0,0,0.1) 0%, rgba(0,0,0,0.6) 60%, rgba(0,0,0,0.97) 100%)"
            }}
          />
        </motion.div>
      </AnimatePresence>

      {/* Hero content at bottom */}
      <div className="absolute bottom-0 left-0 right-0 px-4 pb-4">
        {slide.ranking && (
          <div className="flex items-center gap-1.5 mb-1.5">
            <span
              className="text-[10px] font-bold px-1.5 py-0.5 rounded"
              style={{ background: "var(--gradient-button-neon)", color: "#000" }}
            >
              #{slide.ranking.position}
            </span>
            <span className="text-[11px]" style={{ color: "var(--color-text-secondary)" }}>
              en {slide.ranking.category}
            </span>
          </div>
        )}

        <h1 className="font-black leading-tight mb-1.5 text-white" style={{ fontSize: 24, letterSpacing: "-0.5px" }}>
          {slide.title}
        </h1>

        {/* Metadata row */}
        <div className="flex items-center gap-2 mb-2 flex-wrap">
          <span className="text-xs font-medium" style={{ color: "var(--color-text-secondary)" }}>{slide.year}</span>
          <span style={{ color: "var(--color-border-subtle)" }}>Â·</span>
          <span className="text-xs font-medium" style={{ color: "var(--color-text-secondary)" }}>{slide.duration}</span>
          <span style={{ color: "var(--color-border-subtle)" }}>Â·</span>
          <span
            className="text-xs font-bold px-1.5 py-0.5 border rounded-sm"
            style={{
              color: "#FDC700",
              borderColor: "rgba(253,199,0,0.4)",
              fontSize: 10
            }}
          >
            IMDb {slide.imdb}
          </span>
        </div>

        {/* Genre tags */}
        <div className="flex flex-wrap gap-1.5 mb-3">
          {slide.genres.map((g) => (
            <span
              key={g}
              className="text-[10px] px-2 py-0.5 rounded-full border"
              style={{
                color: "var(--color-text-secondary)",
                borderColor: "var(--color-border-subtle)",
                backgroundColor: "rgba(255,255,255,0.05)"
              }}
            >
              {g}
            </span>
          ))}
        </div>

        {/* Description */}
        <p className="text-xs mb-3 line-clamp-2" style={{ color: "var(--color-text-secondary)", lineHeight: 1.5 }}>
          {slide.description}
        </p>

        {/* Play button */}
        <motion.button
          className="flex items-center justify-center gap-2 w-full rounded-full font-black text-sm text-black py-3 mb-3"
          style={{
            background: "var(--gradient-button-neon)",
            minHeight: "var(--touch-target-min)",
          }}
          whileTap={{ scale: 0.97 }}
          transition={{ duration: 0.1 }}
        >
          <Play className="w-4 h-4 fill-black" />
          REPRODUCIR
        </motion.button>

        {/* Slide dots + secondary actions */}
        <div className="flex items-center justify-between">
          <div className="flex items-center gap-1.5">
            {heroSlides.map((_, i) => (
              <motion.button
                key={i}
                onClick={() => setActiveSlide(i)}
                className="rounded-full"
                animate={{
                  width: i === activeSlide ? 20 : 6,
                  backgroundColor: i === activeSlide ? "var(--accent-primary)" : "rgba(255,255,255,0.3)"
                }}
                whileHover={{
                  backgroundColor: i === activeSlide
                    ? "var(--accent-primary)"
                    : "rgba(255,255,255,0.6)"
                }}
                style={{ height: 6 }}
                transition={{ duration: 0.3 }}
              />
            ))}
          </div>
          <div className="flex items-center gap-3">
            <motion.button whileTap={{ scale: 0.9 }}>
              <Plus className="w-5 h-5" style={{ color: "var(--color-text-secondary)" }} />
            </motion.button>
            <motion.button whileTap={{ scale: 0.9 }}>
              <ThumbsUp className="w-5 h-5" style={{ color: "var(--color-text-secondary)" }} />
            </motion.button>
          </div>
        </div>
      </div>
    </div>
  );
}

function SectionTitle({ title }: { title: string }) {
  return (
    <div className="flex items-center justify-between px-4 mb-3">
      <h2 className="font-bold text-white" style={{ fontSize: 16, letterSpacing: "-0.3px" }}>{title}</h2>
      <button className="flex items-center gap-0.5" style={{ color: "var(--color-accent-success)" }}>
        <span className="text-xs font-medium">Ver todo</span>
        <ChevronRight className="w-3.5 h-3.5" />
      </button>
    </div>
  );
}

function ContinueWatchingRow() {
  return (
    <div>
      <SectionTitle title="Continuar viendo" />
      <div className="flex gap-3 px-4 overflow-x-auto scrollbar-hide pb-1">
        {continueWatching.map((item) => (
          <motion.div
            key={item.id}
            className="shrink-0 rounded-lg overflow-hidden"
            style={{ width: 200, backgroundColor: "var(--color-bg-elevated)" }}
            whileTap={{ scale: 0.97 }}
          >
            <div className="relative" style={{ aspectRatio: "16/9" }}>
              <img src={item.thumbnail} alt={item.title} className="w-full h-full object-cover" />
              <div className="absolute inset-0 flex items-center justify-center">
                <div
                  className="w-9 h-9 rounded-full flex items-center justify-center"
                  style={{ backgroundColor: "rgba(0,0,0,0.5)", backdropFilter: "blur(4px)" }}
                >
                  <Play className="w-4 h-4 fill-white text-white ml-0.5" />
                </div>
              </div>
            </div>
            <div className="px-2 pb-2 pt-1.5">
              <p className="text-xs font-medium text-white truncate">{item.title}</p>
              <ProgressBar progress={item.progress} />
            </div>
          </motion.div>
        ))}
      </div>
    </div>
  );
}

function RecommendedRow() {
  return (
    <div>
      <SectionTitle title="Te recomendamos" />
      <div className="flex gap-3 px-4 overflow-x-auto scrollbar-hide pb-1">
        {recommended.map((item) => (
          <motion.div
            key={item.id}
            className="shrink-0 rounded-lg overflow-hidden"
            style={{ width: 140, aspectRatio: "2/3", backgroundColor: "var(--color-bg-elevated)" }}
            whileTap={{ scale: 0.97 }}
          >
            <img src={item.thumbnail} alt={item.title} className="w-full h-full object-cover" />
          </motion.div>
        ))}
      </div>
    </div>
  );
}

/* â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€ iOS STATUS BAR â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€ */
function IOSStatusBar() {
  return (
    <div className="flex items-center justify-between px-6 pt-3 pb-1 shrink-0" style={{ height: 44 }}>
      <span className="text-white font-semibold text-sm">9:41</span>
      <div className="flex items-center gap-1.5">
        {/* Signal */}
        <div className="flex items-end gap-0.5 h-3">
          {[3, 5, 7, 9].map((h, i) => (
            <div key={i} className="w-1 rounded-sm bg-white" style={{ height: h }} />
          ))}
        </div>
        {/* WiFi */}
        <svg width="15" height="11" viewBox="0 0 15 11" fill="none">
          <path d="M7.5 10a1 1 0 100-2 1 1 0 000 2z" fill="white"/>
          <path d="M4.5 7.5C5.4 6.6 6.4 6 7.5 6s2.1.6 3 1.5" stroke="white" strokeWidth="1.2" strokeLinecap="round"/>
          <path d="M1.5 4.5C3.3 2.7 5.3 1.5 7.5 1.5s4.2 1.2 6 3" stroke="white" strokeWidth="1.2" strokeLinecap="round"/>
        </svg>
        {/* Battery */}
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

/* â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€ ANDROID STATUS BAR â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€ */
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

/* â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€ MOBILE TOP NAV â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€ */
function MobileTopNav() {
  const [active, setActive] = useState("Inicio");

  const navItems = ["Inicio", "GuÃ­a de TV", "PelÃ­culas", "Series", "Deportes", "MÃºsica", "Noticias", "NiÃ±os"];

  return (
    <div className="shrink-0 px-4 pb-2">
      <div className="flex items-center mb-3">
        <img src={beonLogo} alt="BEOn" className="h-7 w-auto object-contain" />
      </div>
      <div className="flex gap-6 overflow-x-auto scrollbar-hide pb-1 -mx-4 px-4">
        {navItems.map((item) => {
          const isActive = item === active;
          return (
            <motion.button
              key={item}
              onClick={() => setActive(item)}
              className="shrink-0 text-sm font-medium pb-1 relative"
              style={{
                color: isActive ? "#fff" : "var(--color-text-secondary)",
                minHeight: "var(--touch-target-min)"
              }}
              whileTap={{ scale: 0.95 }}
            >
              {item}
              {isActive && (
                <motion.div
                  className="absolute bottom-0 left-0 right-0 h-0.5 rounded-full"
                  style={{ backgroundColor: "#fff" }}
                  layoutId="mobileActiveTab"
                  transition={{ type: "spring", stiffness: 400, damping: 30 }}
                />
              )}
            </motion.button>
          );
        })}
      </div>
    </div>
  );
}





/* â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€ MAIN EXPORT â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€ */
export function MobileHome({ platform }: MobileHomeProps) {
  const scrollRef = useRef<HTMLDivElement>(null);
  const isIOS = platform === "ios";

  return (
    <div
      className="flex flex-col w-full h-full overflow-hidden"
      style={{ backgroundColor: "var(--color-bg-base)", fontFamily: "Inter, system-ui, sans-serif" }}
    >
      {/* Status bar */}
      {isIOS ? <IOSStatusBar /> : <AndroidStatusBar />}

      {/* Top navigation */}
      <MobileTopNav />

      {/* Scrollable content */}
      <div ref={scrollRef} className="flex-1 overflow-y-auto overflow-x-hidden scrollbar-hide">
        {/* Hero */}
        <HeroSection platform={platform} />

        {/* Content sections */}
        <div className="py-6 space-y-6">
          <ContinueWatchingRow />
          <RecommendedRow />
          {/* Second recommended row */}
          <div>
            <SectionTitle title="Series aclamadas" />
            <div className="flex gap-3 px-4 overflow-x-auto scrollbar-hide pb-1">
              {recommended.slice(0, 4).map((item) => (
                <motion.div
                  key={`s-${item.id}`}
                  className="shrink-0 rounded-lg overflow-hidden"
                  style={{ width: 140, backgroundColor: "var(--color-bg-elevated)" }}
                  whileTap={{ scale: 0.97 }}
                >
                  <div style={{ aspectRatio: "16/9" }}>
                    <img src={item.thumbnail} alt={item.title} className="w-full h-full object-cover" />
                  </div>
                  <div className="px-2 py-1.5">
                    <p className="text-[11px] font-medium text-white truncate">{item.title}</p>
                  </div>
                </motion.div>
              ))}
            </div>
          </div>
        </div>

        {/* Bottom spacer so last content isn't hidden behind tab bar */}
        <div className="h-4" />
      </div>

      {/* Bottom navigation - Using imported Figma design */}
      <MobileBottomNav activeTab="inicio" />
    </div>
  );
}
