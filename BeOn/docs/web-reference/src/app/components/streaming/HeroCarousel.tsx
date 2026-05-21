import { motion, AnimatePresence } from "motion/react";
import { Play, Plus, Info } from "lucide-react";
import { useState, useEffect } from "react";

interface HeroSlide {
  id: string;
  title: string;
  description: string;
  backgroundImage: string;
  year?: string;
  duration?: string;
  rating?: string;
  imdbRating?: string;
  genres?: string[];
  ranking?: { position: number; category: string };
}

interface HeroCarouselProps {
  slides: HeroSlide[];
  autoplayInterval?: number;
}

export function HeroCarousel({ slides, autoplayInterval = 5000 }: HeroCarouselProps) {
  const [currentSlide, setCurrentSlide] = useState(0);

  useEffect(() => {
    const timer = setInterval(() => {
      setCurrentSlide((prev) => (prev + 1) % slides.length);
    }, autoplayInterval);

    return () => clearInterval(timer);
  }, [slides.length, autoplayInterval]);

  const slide = slides[currentSlide];

  return (
    <div className="relative w-full h-[90vh] overflow-hidden">
      {/* Background slides */}
      <AnimatePresence mode="wait">
        <motion.div
          key={currentSlide}
          className="absolute inset-0"
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
          exit={{ opacity: 0 }}
          transition={{ duration: 0.7 }}
        >
          <img
            src={slide.backgroundImage}
            alt={slide.title}
            className="w-full h-full object-cover"
          />
          {/* Cinematic gradient overlay */}
          <div
            className="absolute inset-0"
            style={{
              background: "var(--gradient-hero)"
            }}
          />
        </motion.div>
      </AnimatePresence>

      {/* Content */}
      <div className="absolute bottom-24 left-16 right-16 z-10">
        <AnimatePresence mode="wait">
          <motion.div
            key={currentSlide}
            className="max-w-3xl space-y-6"
            initial={{ y: 50, opacity: 0 }}
            animate={{ y: 0, opacity: 1 }}
            exit={{ y: -50, opacity: 0 }}
            transition={{ duration: 0.5 }}
          >
            {/* Title */}
            <h1
              className="text-white tracking-tight"
              style={{
                fontSize: "var(--text-3xl)",
                fontWeight: "var(--font-black)",
                lineHeight: "var(--leading-tight)",
                letterSpacing: "-0.05em"
              }}
            >
              {slide.title}
            </h1>

            {/* Metadata */}
            <div className="flex items-center gap-4 text-lg">
              {slide.year && (
                <span className="text-[var(--text-muted)] font-semibold">
                  {slide.year}
                </span>
              )}
              {slide.duration && (
                <>
                  <span className="text-[var(--text-disabled)]">â€¢</span>
                  <span className="text-[var(--text-muted)] font-medium">
                    {slide.duration}
                  </span>
                </>
              )}
              {slide.imdbRating && (
                <>
                  <span className="text-[var(--text-disabled)]">â€¢</span>
                  <div className="flex items-center gap-2">
                    <span className="text-[var(--text-warning)] font-medium">
                      IMDb
                    </span>
                    <span className="text-[var(--text-muted)]">{slide.imdbRating}</span>
                  </div>
                </>
              )}
              {slide.rating && (
                <>
                  <span className="text-[var(--text-disabled)]">â€¢</span>
                  <span
                    className="px-2 py-0.5 rounded text-sm font-medium"
                    style={{
                      border: "1px solid var(--text-disabled)",
                      color: "var(--text-secondary)"
                    }}
                  >
                    {slide.rating}
                  </span>
                </>
              )}
            </div>

            {/* Ranking badge */}
            {slide.ranking && (
              <motion.div
                className="flex items-center gap-2"
                initial={{ scale: 0.9, opacity: 0 }}
                animate={{ scale: 1, opacity: 1 }}
                transition={{ delay: 0.3 }}
              >
                <div
                  className="w-7 h-7 rounded-full flex items-center justify-center"
                  style={{
                    border: "1px solid rgba(5, 223, 114, 0.4)"
                  }}
                >
                  <svg
                    className="w-4 h-4"
                    viewBox="0 0 16 16"
                    fill="none"
                    stroke="var(--accent-primary)"
                    strokeWidth="2"
                  >
                    <path d="M13 6L6 13L2 9" strokeLinecap="round" strokeLinejoin="round" />
                    <circle cx="11" cy="4" r="2" />
                  </svg>
                </div>
                <span className="text-[var(--text-accent)] font-bold">
                  NÃºmero {slide.ranking.position} en {slide.ranking.category}
                </span>
              </motion.div>
            )}

            {/* Genres */}
            {slide.genres && slide.genres.length > 0 && (
              <div className="flex gap-2">
                {slide.genres.map((genre, index) => (
                  <span
                    key={index}
                    className="px-3 py-1.5 rounded-full text-sm text-white"
                    style={{
                      backgroundColor: "rgba(51, 51, 51, 0.4)"
                    }}
                  >
                    {genre}
                  </span>
                ))}
              </div>
            )}

            {/* Description */}
            <p
              className="max-w-2xl"
              style={{
                color: "var(--text-secondary)",
                fontSize: "var(--text-lg)",
                lineHeight: "1.625"
              }}
            >
              {slide.description.length > 300 ? (
                <>
                  {slide.description.slice(0, 300)}...
                  <button className="ml-2 text-[var(--text-accent)] font-bold hover:underline">
                    Ver mÃ¡s.
                  </button>
                </>
              ) : (
                slide.description
              )}
            </p>

            {/* Action buttons */}
            <div className="flex items-center gap-4 pt-2">
              <motion.button
                className="px-12 py-4 rounded-full font-black text-black flex items-center gap-3"
                style={{
                  background: "var(--gradient-button-primary)",
                  boxShadow: "var(--shadow-glow-strong)"
                }}
                whileHover={{ scale: 1.05 }}
                whileTap={{ scale: 0.98 }}
              >
                <Play className="w-5 h-5 fill-black" />
                <span className="text-base">REPRODUCIR</span>
              </motion.button>

              <motion.button
                className="w-14 h-14 rounded-full flex items-center justify-center"
                style={{
                  border: "2px solid var(--border-strong)"
                }}
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
                style={{
                  border: "2px solid var(--border-strong)"
                }}
                whileHover={{
                  scale: 1.1,
                  borderColor: "var(--accent-primary)"
                }}
                whileTap={{ scale: 0.95 }}
              >
                <Info className="w-6 h-6 text-white" />
              </motion.button>
            </div>
          </motion.div>
        </AnimatePresence>
      </div>

      {/* Dot indicators */}
      <div className="absolute bottom-8 left-1/2 -translate-x-1/2 z-20 flex items-center gap-3">
        {slides.map((_, index) => (
          <motion.button
            key={index}
            className="rounded-full transition-all"
            style={{
              width: currentSlide === index ? "32px" : "8px",
              height: "8px",
              backgroundColor: currentSlide === index
                ? "var(--accent-primary)"
                : "rgba(255, 255, 255, 0.3)"
            }}
            onClick={() => setCurrentSlide(index)}
            whileHover={{
              backgroundColor: currentSlide === index
                ? "var(--accent-primary)"
                : "rgba(255, 255, 255, 0.6)"
            }}
            animate={{
              width: currentSlide === index ? "32px" : "8px"
            }}
            transition={{ duration: 0.3 }}
          />
        ))}
      </div>
    </div>
  );
}
