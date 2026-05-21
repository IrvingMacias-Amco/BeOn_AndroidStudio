import { useState } from "react";
import { useNavigate } from "react-router";
import { motion } from "motion/react";
import { StreamingNav } from "../components/streaming/StreamingNav";
import { HeroCarousel } from "../components/streaming/HeroCarousel";
import { GenericCarousel } from "../components/streaming/GenericCarousel";
import { Top10Carousel } from "../components/streaming/Top10Carousel";
import { ContinueWatchingCarousel } from "../components/streaming/ContinueWatchingCarousel";
import { LiveChannelCarousel } from "../components/streaming/LiveChannelCarousel";
import { MobilePreview } from "../components/streaming/MobilePreview";
import type { MobilePlatform } from "../components/streaming/MobileHome";

export function Home() {
  const navigate = useNavigate();
  const [previewPlatform, setPreviewPlatform] = useState<MobilePlatform | null>(null);

  const handleContentClick = (id: string) => {
    navigate(`/content/${id}`);
  };

  // Hero carousel slides
  const heroSlides = [
    {
      id: "1",
      title: "Marty Supremo",
      description: "Un agente deportivo con mÃ¡s estilo que Ã©tica descubre una Ãºltima oportunidad de redenciÃ³n cuando conoce a un joven talento del ping-pong. Entre apuestas arriesgadas, rivalidades feroces y una transformaciÃ³n personal inesperada, Marty deberÃ¡ decidir si vale la pena ganar a cualquier precio o si hay algo mÃ¡s importante que el Ã©xito.",
      backgroundImage: "https://images.unsplash.com/photo-1745948080908-b7e5fe4cba90?w=1600",
      year: "2025",
      duration: "2h 29min",
      rating: "PG-13",
      imdbRating: "7.5",
      genres: ["Comedia", "Deporte", "Drama"],
      ranking: { position: 5, category: "pelÃ­culas de Drama" }
    },
    {
      id: "2",
      title: "Urban Legends",
      description: "En las calles oscuras de la ciudad, las leyendas cobran vida. Un grupo de investigadores descubre que los mitos urbanos que aterrorizan a la metrÃ³polis tienen un origen mÃ¡s real y siniestro de lo que nadie imaginaba.",
      backgroundImage: "https://images.unsplash.com/photo-1758405282251-26903f4b7fcb?w=1600",
      year: "2025",
      duration: "2h 05min",
      rating: "R",
      imdbRating: "8.2",
      genres: ["Suspenso", "Terror", "Misterio"],
      ranking: { position: 2, category: "pelÃ­culas de Terror" }
    },
    {
      id: "3",
      title: "Neon Nights",
      description: "Una ciudad que nunca duerme, secretos que nunca descansan. En el corazÃ³n pulsante de la metrÃ³polis nocturna, un detective debe resolver el caso mÃ¡s complejo de su carrera mientras navega por un mundo de luces de neÃ³n y sombras profundas.",
      backgroundImage: "https://images.unsplash.com/photo-1762417419967-d5ccd2ebe463?w=1600",
      year: "2024",
      duration: "1h 52min",
      rating: "PG-13",
      imdbRating: "7.8",
      genres: ["AcciÃ³n", "Thriller", "Ciencia FicciÃ³n"]
    },
    {
      id: "4",
      title: "Speed Demon",
      description: "Cuando la velocidad es tu Ãºnica salvaciÃ³n y cada segundo cuenta. Un corredor de motos callejeras debe enfrentarse a su pasado mientras compite en la carrera mÃ¡s peligrosa de su vida para salvar a quienes mÃ¡s ama.",
      backgroundImage: "https://images.unsplash.com/photo-1627736990081-602486bcb4d3?w=1600",
      year: "2025",
      duration: "2h 05min",
      rating: "PG-13",
      imdbRating: "7.9",
      genres: ["AcciÃ³n", "Drama", "Deporte"],
      ranking: { position: 1, category: "pelÃ­culas de AcciÃ³n" }
    },
    {
      id: "5",
      title: "The Last Performance",
      description: "La mÃºsica puede cambiar vidas, pero Â¿puede salvar almas? Un mÃºsico en declive encuentra una Ãºltima oportunidad de redenciÃ³n cuando descubre un talento extraordinario que le hace recordar por quÃ© empezÃ³ a tocar.",
      backgroundImage: "https://images.unsplash.com/photo-1628242681477-f8dd17bb0962?w=1600",
      year: "2024",
      duration: "1h 40min",
      rating: "PG",
      imdbRating: "8.5",
      genres: ["Drama", "MÃºsica", "Inspiracional"],
      ranking: { position: 3, category: "pelÃ­culas de Drama" }
    }
  ];

  // Content data
  const recienAgregado = [
    { id: "1", title: "Neon Nights", thumbnail: "https://images.unsplash.com/photo-1762417419967-d5ccd2ebe463?w=300", year: "2024", isNew: true },
    { id: "2", title: "Speed Demon", thumbnail: "https://images.unsplash.com/photo-1627736990081-602486bcb4d3?w=300", year: "2025", isNew: true },
    { id: "3", title: "Urban Chronicles", thumbnail: "https://images.unsplash.com/photo-1758405282251-26903f4b7fcb?w=300", year: "2025", isNew: true },
    { id: "4", title: "Dark Pursuit", thumbnail: "https://images.unsplash.com/photo-1745948080908-b7e5fe4cba90?w=300", year: "2025", isNew: true },
    { id: "5", title: "Street Fighter", thumbnail: "https://images.unsplash.com/photo-1693645325862-cf4fc2db0e29?w=300", year: "2024", isNew: true },
    { id: "6", title: "Last Stand", thumbnail: "https://images.unsplash.com/photo-1627736619924-ce9f159dedca?w=300", year: "2025", isNew: true },
    { id: "7", title: "Night Rider", thumbnail: "https://images.unsplash.com/photo-1627736610072-fc606c63d5f5?w=300", year: "2024", isNew: true },
    { id: "8", title: "City Lights", thumbnail: "https://images.unsplash.com/photo-1582628473668-9684efa82428?w=300", year: "2025", isNew: true }
  ];

  const top10Mexico = [
    { id: "1", title: "Marty Supremo", thumbnail: "https://images.unsplash.com/photo-1745948080908-b7e5fe4cba90?w=300", ranking: 1, year: "2025" },
    { id: "2", title: "Urban Legends", thumbnail: "https://images.unsplash.com/photo-1758405282251-26903f4b7fcb?w=300", ranking: 2, year: "2025" },
    { id: "3", title: "Neon Detective", thumbnail: "https://images.unsplash.com/photo-1762417419967-d5ccd2ebe463?w=300", ranking: 3, year: "2024" },
    { id: "4", title: "Speed Demon", thumbnail: "https://images.unsplash.com/photo-1627736990081-602486bcb4d3?w=300", ranking: 4, year: "2025" },
    { id: "5", title: "Last Performance", thumbnail: "https://images.unsplash.com/photo-1628242681477-f8dd17bb0962?w=300", ranking: 5, year: "2024" },
    { id: "6", title: "Combat Zone", thumbnail: "https://images.unsplash.com/photo-1627736619924-ce9f159dedca?w=300", ranking: 6, year: "2025" },
    { id: "7", title: "Night Operations", thumbnail: "https://images.unsplash.com/photo-1693645325862-cf4fc2db0e29?w=300", ranking: 7, year: "2024" },
    { id: "8", title: "Sunset Boulevard", thumbnail: "https://images.unsplash.com/photo-1627736610072-fc606c63d5f5?w=300", ranking: 8, year: "2025" },
    { id: "9", title: "Urban Warrior", thumbnail: "https://images.unsplash.com/photo-1582628473668-9684efa82428?w=300", ranking: 9, year: "2024" },
    { id: "10", title: "Final Chapter", thumbnail: "https://images.unsplash.com/photo-1778372670100-1660d369e049?w=300", ranking: 10, year: "2025" }
  ];

  const teRecomendamos = [
    { id: "11", title: "Cinema Dreams", thumbnail: "https://images.unsplash.com/photo-1688678004647-945d5aaf91c1?w=300", year: "2025" },
    { id: "12", title: "Theater Nights", thumbnail: "https://images.unsplash.com/photo-1635400138431-0bbde4d01484?w=300", year: "2024" },
    { id: "13", title: "The Screening", thumbnail: "https://images.unsplash.com/photo-1668890094751-6986d0ca9dfc?w=300", year: "2025" },
    { id: "14", title: "Premiere Night", thumbnail: "https://images.unsplash.com/photo-1640127249305-793865c2efe1?w=300", year: "2024" },
    { id: "15", title: "Classic Films", thumbnail: "https://images.unsplash.com/photo-1626814026160-2237a95fc5a0?w=300", year: "2025" },
    { id: "16", title: "Red Carpet", thumbnail: "https://images.unsplash.com/photo-1620145648299-f926ac0a9470?w=300", year: "2024" },
    { id: "17", title: "Cinema Hall", thumbnail: "https://images.unsplash.com/photo-1561722798-9a732d141027?w=300", year: "2025" },
    { id: "18", title: "Movie Magic", thumbnail: "https://images.unsplash.com/photo-1650475958723-e8d850c26f67?w=300", year: "2024" }
  ];

  const continuarViendo = [
    { id: "19", title: "Dark Pursuit", thumbnail: "https://images.unsplash.com/photo-1745948080908-b7e5fe4cba90?w=500", progress: 25, year: "2025" },
    { id: "20", title: "Urban Legends", thumbnail: "https://images.unsplash.com/photo-1758405282251-26903f4b7fcb?w=500", progress: 50, year: "2025" },
    { id: "21", title: "Speed Demon", thumbnail: "https://images.unsplash.com/photo-1627736990081-602486bcb4d3?w=500", progress: 75, year: "2025" },
    { id: "22", title: "Night Operations", thumbnail: "https://images.unsplash.com/photo-1693645325862-cf4fc2db0e29?w=500", progress: 10, year: "2024" },
    { id: "23", title: "City Chronicles", thumbnail: "https://images.unsplash.com/photo-1582628473668-9684efa82428?w=500", progress: 60, year: "2024" }
  ];

  const canalesEnVivo = [
    { id: "24", title: "Noticias 24/7", thumbnail: "https://images.unsplash.com/photo-1762417419967-d5ccd2ebe463?w=300", channelName: "Canal 1" },
    { id: "25", title: "Deportes EN VIVO", thumbnail: "https://images.unsplash.com/photo-1627736990081-602486bcb4d3?w=300", channelName: "ESPN" },
    { id: "26", title: "MÃºsica Live", thumbnail: "https://images.unsplash.com/photo-1628242681477-f8dd17bb0962?w=300", channelName: "MTV" },
    { id: "27", title: "Concierto Rock", thumbnail: "https://images.unsplash.com/photo-1778372670100-1660d369e049?w=300", channelName: "Music TV" },
    { id: "28", title: "Evento Especial", thumbnail: "https://images.unsplash.com/photo-1758405282251-26903f4b7fcb?w=300", channelName: "Canal 5" },
    { id: "29", title: "Show Nocturno", thumbnail: "https://images.unsplash.com/photo-1745948080908-b7e5fe4cba90?w=300", channelName: "Canal 2" }
  ];

  const seriesAclamadas = [
    { id: "30", title: "Breaking Boundaries", thumbnail: "https://images.unsplash.com/photo-1627873649417-c67f701f1949?w=300", year: "2024" },
    { id: "31", title: "The Crown Jewel", thumbnail: "https://images.unsplash.com/photo-1633793675529-58eecb6ea16f?w=300", year: "2025" },
    { id: "32", title: "Digital Age", thumbnail: "https://images.unsplash.com/photo-1588546506381-74592e9b8a2d?w=300", year: "2024" },
    { id: "33", title: "The Platform", thumbnail: "https://images.unsplash.com/photo-1627873959341-905d35362273?w=300", year: "2025" },
    { id: "34", title: "Time Chronicles", thumbnail: "https://images.unsplash.com/photo-1663775635512-c60be8b302b0?w=300", year: "2024" },
    { id: "35", title: "Modern Tales", thumbnail: "https://images.unsplash.com/photo-1588672455574-c4d93ec7e525?w=300", year: "2025" },
    { id: "36", title: "Epic Saga", thumbnail: "https://images.unsplash.com/photo-1584208750035-f175342eb922?w=300", year: "2024" }
  ];

  const terror = [
    { id: "37", title: "The Haunting", thumbnail: "https://images.unsplash.com/photo-1758405282251-26903f4b7fcb?w=300", year: "2025" },
    { id: "38", title: "Dark Shadows", thumbnail: "https://images.unsplash.com/photo-1745948080908-b7e5fe4cba90?w=300", year: "2024" },
    { id: "39", title: "Nightmare Realm", thumbnail: "https://images.unsplash.com/photo-1693645325862-cf4fc2db0e29?w=300", year: "2025" },
    { id: "40", title: "Silent Scream", thumbnail: "https://images.unsplash.com/photo-1627736619924-ce9f159dedca?w=300", year: "2024" },
    { id: "41", title: "The Possession", thumbnail: "https://images.unsplash.com/photo-1762417419967-d5ccd2ebe463?w=300", year: "2025" },
    { id: "42", title: "Cursed", thumbnail: "https://images.unsplash.com/photo-1627736610072-fc606c63d5f5?w=300", year: "2024" }
  ];

  const comedia = [
    { id: "43", title: "Laugh Out Loud", thumbnail: "https://images.unsplash.com/photo-1628242681477-f8dd17bb0962?w=300", year: "2025" },
    { id: "44", title: "Comedy Club", thumbnail: "https://images.unsplash.com/photo-1582628473668-9684efa82428?w=300", year: "2024" },
    { id: "45", title: "Funny Business", thumbnail: "https://images.unsplash.com/photo-1778372670100-1660d369e049?w=300", year: "2025" },
    { id: "46", title: "The Jokers", thumbnail: "https://images.unsplash.com/photo-1688678004647-945d5aaf91c1?w=300", year: "2024" },
    { id: "47", title: "Happy Hour", thumbnail: "https://images.unsplash.com/photo-1635400138431-0bbde4d01484?w=300", year: "2025" },
    { id: "48", title: "Comic Relief", thumbnail: "https://images.unsplash.com/photo-1668890094751-6986d0ca9dfc?w=300", year: "2024" }
  ];

  const drama = [
    { id: "49", title: "Emotional Journey", thumbnail: "https://images.unsplash.com/photo-1640127249305-793865c2efe1?w=300", year: "2025" },
    { id: "50", title: "Life Stories", thumbnail: "https://images.unsplash.com/photo-1626814026160-2237a95fc5a0?w=300", year: "2024" },
    { id: "51", title: "Human Condition", thumbnail: "https://images.unsplash.com/photo-1620145648299-f926ac0a9470?w=300", year: "2025" },
    { id: "52", title: "Tears & Joy", thumbnail: "https://images.unsplash.com/photo-1561722798-9a732d141027?w=300", year: "2024" },
    { id: "53", title: "The Journey", thumbnail: "https://images.unsplash.com/photo-1650475958723-e8d850c26f67?w=300", year: "2025" },
    { id: "54", title: "Deep Emotions", thumbnail: "https://images.unsplash.com/photo-1627873649417-c67f701f1949?w=300", year: "2024" }
  ];

  const accion = [
    { id: "55", title: "Speed Demon", thumbnail: "https://images.unsplash.com/photo-1627736990081-602486bcb4d3?w=300", year: "2025" },
    { id: "56", title: "Combat Zone", thumbnail: "https://images.unsplash.com/photo-1627736619924-ce9f159dedca?w=300", year: "2024" },
    { id: "57", title: "Urban Warrior", thumbnail: "https://images.unsplash.com/photo-1693645325862-cf4fc2db0e29?w=300", year: "2025" },
    { id: "58", title: "Night Operations", thumbnail: "https://images.unsplash.com/photo-1627736610072-fc606c63d5f5?w=300", year: "2024" },
    { id: "59", title: "Final Stand", thumbnail: "https://images.unsplash.com/photo-1745948080908-b7e5fe4cba90?w=300", year: "2025" },
    { id: "60", title: "Strike Force", thumbnail: "https://images.unsplash.com/photo-1758405282251-26903f4b7fcb?w=300", year: "2024" }
  ];

  const suspenso = [
    { id: "61", title: "The Mystery", thumbnail: "https://images.unsplash.com/photo-1762417419967-d5ccd2ebe463?w=300", year: "2025" },
    { id: "62", title: "Hidden Secrets", thumbnail: "https://images.unsplash.com/photo-1745948080908-b7e5fe4cba90?w=300", year: "2024" },
    { id: "63", title: "Dark Truth", thumbnail: "https://images.unsplash.com/photo-1758405282251-26903f4b7fcb?w=300", year: "2025" },
    { id: "64", title: "The Investigation", thumbnail: "https://images.unsplash.com/photo-1693645325862-cf4fc2db0e29?w=300", year: "2024" },
    { id: "65", title: "Conspiracy", thumbnail: "https://images.unsplash.com/photo-1627736619924-ce9f159dedca?w=300", year: "2025" },
    { id: "66", title: "Unsolved", thumbnail: "https://images.unsplash.com/photo-1627736610072-fc606c63d5f5?w=300", year: "2024" }
  ];

  const infantil = [
    { id: "67", title: "Magic Kingdom", thumbnail: "https://images.unsplash.com/photo-1688678004647-945d5aaf91c1?w=300", year: "2025" },
    { id: "68", title: "Adventure Time", thumbnail: "https://images.unsplash.com/photo-1635400138431-0bbde4d01484?w=300", year: "2024" },
    { id: "69", title: "Cartoon Fun", thumbnail: "https://images.unsplash.com/photo-1668890094751-6986d0ca9dfc?w=300", year: "2025" },
    { id: "70", title: "Animal Friends", thumbnail: "https://images.unsplash.com/photo-1640127249305-793865c2efe1?w=300", year: "2024" },
    { id: "71", title: "Fairy Tales", thumbnail: "https://images.unsplash.com/photo-1626814026160-2237a95fc5a0?w=300", year: "2025" },
    { id: "72", title: "Kids World", thumbnail: "https://images.unsplash.com/photo-1620145648299-f926ac0a9470?w=300", year: "2024" }
  ];

  const cineLatinoamericano = [
    { id: "73", title: "AmÃ©rica Latina", thumbnail: "https://images.unsplash.com/photo-1628242681477-f8dd17bb0962?w=300", year: "2025" },
    { id: "74", title: "RaÃ­ces", thumbnail: "https://images.unsplash.com/photo-1582628473668-9684efa82428?w=300", year: "2024" },
    { id: "75", title: "Cultura Viva", thumbnail: "https://images.unsplash.com/photo-1778372670100-1660d369e049?w=300", year: "2025" },
    { id: "76", title: "Historia Latina", thumbnail: "https://images.unsplash.com/photo-1745948080908-b7e5fe4cba90?w=300", year: "2024" },
    { id: "77", title: "Tradiciones", thumbnail: "https://images.unsplash.com/photo-1762417419967-d5ccd2ebe463?w=300", year: "2025" },
    { id: "78", title: "Nuestro Cine", thumbnail: "https://images.unsplash.com/photo-1758405282251-26903f4b7fcb?w=300", year: "2024" }
  ];

  const documental = [
    { id: "79", title: "Real Stories", thumbnail: "https://images.unsplash.com/photo-1627873649417-c67f701f1949?w=300", year: "2025" },
    { id: "80", title: "True Events", thumbnail: "https://images.unsplash.com/photo-1633793675529-58eecb6ea16f?w=300", year: "2024" },
    { id: "81", title: "Planet Earth", thumbnail: "https://images.unsplash.com/photo-1588546506381-74592e9b8a2d?w=300", year: "2025" },
    { id: "82", title: "History Revealed", thumbnail: "https://images.unsplash.com/photo-1627873959341-905d35362273?w=300", year: "2024" },
    { id: "83", title: "Science World", thumbnail: "https://images.unsplash.com/photo-1663775635512-c60be8b302b0?w=300", year: "2025" },
    { id: "84", title: "Nature's Wonders", thumbnail: "https://images.unsplash.com/photo-1588672455574-c4d93ec7e525?w=300", year: "2024" }
  ];

  return (
    <div className="min-h-screen" style={{ backgroundColor: "var(--color-bg-primary, #000000)" }}>
      {/* Navigation */}
      <StreamingNav />

      {/* Hero Carousel */}
      <div className="pt-[72px]">
        <HeroCarousel slides={heroSlides} autoplayInterval={5000} />
      </div>

      {/* Content Carousels */}
      <div className="space-y-12 py-12">
        <GenericCarousel title="ReciÃ©n agregado" items={recienAgregado} onItemClick={handleContentClick} />
        <Top10Carousel title="Top 10 en MÃ©xico" items={top10Mexico} onItemClick={handleContentClick} />
        <GenericCarousel title="Te recomendamos" items={teRecomendamos} onItemClick={handleContentClick} />
        <ContinueWatchingCarousel title="Continuar viendo" items={continuarViendo} onItemClick={handleContentClick} />
        <LiveChannelCarousel title="Canales en vivo" items={canalesEnVivo} onItemClick={handleContentClick} />
        <GenericCarousel title="Series aclamadas por la crÃ­tica" items={seriesAclamadas} onItemClick={handleContentClick} />
        <GenericCarousel title="Terror" items={terror} onItemClick={handleContentClick} />
        <GenericCarousel title="Comedia" items={comedia} onItemClick={handleContentClick} />
        <GenericCarousel title="Drama" items={drama} onItemClick={handleContentClick} />
        <GenericCarousel title="AcciÃ³n" items={accion} onItemClick={handleContentClick} />
        <GenericCarousel title="Suspenso" items={suspenso} onItemClick={handleContentClick} />
        <GenericCarousel title="Infantil" items={infantil} onItemClick={handleContentClick} />
        <GenericCarousel title="Cine Latinoamericano" items={cineLatinoamericano} onItemClick={handleContentClick} />
        <GenericCarousel title="Documental" items={documental} onItemClick={handleContentClick} />
      </div>

      {/* Mobile Preview Buttons */}
      <div
        className="flex flex-col items-center gap-4 py-16 px-6"
        style={{ borderTop: "1px solid var(--color-border-subtle)" }}
      >
        <p className="text-sm font-medium mb-2" style={{ color: "var(--color-text-secondary)", letterSpacing: "0.05em", textTransform: "uppercase" }}>
          Vista previa mÃ³vil
        </p>
        <div className="flex items-center gap-4 flex-wrap justify-center">
          {/* Android button */}
          <motion.button
            onClick={() => setPreviewPlatform("android")}
            className="flex items-center gap-3 px-6 py-3.5 rounded-full font-semibold text-sm"
            style={{
              backgroundColor: "var(--color-bg-elevated)",
              border: "1px solid var(--color-border-subtle)",
              color: "var(--color-text-primary)",
              minHeight: 48,
            }}
            whileHover={{
              scale: 1.03,
              backgroundColor: "var(--color-bg-surface)",
              borderColor: "rgba(255,255,255,0.2)"
            }}
            whileTap={{ scale: 0.97 }}
          >
            <svg width="20" height="20" viewBox="0 0 24 24" fill="none">
              <path d="M17.523 15.341c-.105.29-.23.564-.373.82-.197.354-.38.599-.544.735-.217.2-.45.302-.7.308-.18 0-.396-.05-.647-.154-.252-.103-.483-.154-.696-.154-.224 0-.462.051-.716.154-.254.103-.459.157-.617.162-.24.01-.478-.095-.716-.315-.175-.15-.366-.403-.573-.757-.246-.409-.447-.885-.605-1.427-.169-.583-.254-1.148-.254-1.695 0-.626.135-1.167.406-1.62.213-.365.496-.653.85-.864.354-.21.737-.317 1.148-.323.231 0 .535.071.912.21.376.14.617.211.724.211.08 0 .35-.083.808-.249.433-.155.799-.219 1.1-.195.812.065 1.422.383 1.829.954-.726.44-1.085 1.057-1.079 1.847.006.617.23 1.13.672 1.537.2.19.423.337.671.44-.054.156-.111.305-.171.446zM14.69 3.66c0 .484-.177.936-.529 1.353-.425.496-.94.783-1.497.737-.007-.058-.011-.12-.011-.184 0-.464.201-.96.56-1.366.178-.205.404-.375.68-.513.275-.137.536-.213.78-.226.008.067.012.133.012.199z" fill="currentColor"/>
            </svg>
            Vista previa Android
          </motion.button>

          {/* iOS button */}
          <motion.button
            onClick={() => setPreviewPlatform("ios")}
            className="flex items-center gap-3 px-6 py-3.5 rounded-full font-semibold text-sm text-black"
            style={{
              background: "var(--gradient-button-neon)",
              boxShadow: "var(--effect-glow-element)",
              minHeight: 48,
            }}
            whileHover={{
              scale: 1.03,
              boxShadow: "var(--effect-glow-hover)"
            }}
            whileTap={{ scale: 0.97 }}
          >
            <svg width="20" height="20" viewBox="0 0 24 24" fill="currentColor">
              <path d="M18.71 19.5c-.83 1.24-1.71 2.45-3.05 2.47-1.34.03-1.77-.79-3.29-.79-1.53 0-2 .77-3.27.82-1.31.05-2.3-1.32-3.14-2.53C4.25 17 2.94 12.45 4.7 9.39c.87-1.52 2.43-2.48 4.12-2.51 1.28-.02 2.5.87 3.29.87.78 0 2.26-1.07 3.8-.91.65.03 2.47.26 3.64 1.98-.09.06-2.17 1.28-2.15 3.81.03 3.02 2.65 4.03 2.68 4.04-.03.07-.42 1.44-1.38 2.83M13 3.5c.73-.83 1.94-1.46 2.94-1.5.13 1.17-.34 2.35-1.04 3.19-.69.85-1.83 1.51-2.95 1.42-.15-1.15.41-2.35 1.05-3.11z"/>
            </svg>
            Vista previa iOS
          </motion.button>
        </div>
      </div>

      {/* Mobile preview modal */}
      <MobilePreview platform={previewPlatform} onClose={() => setPreviewPlatform(null)} />
    </div>
  );
}
