import { motion } from "motion/react";
import { Play, Plus, Info, ChevronLeft, ChevronRight } from "lucide-react";

interface HeroSectionProps {
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

export function HeroSection({
  title,
  description,
  backgroundImage,
  year,
  duration,
  rating,
  imdbRating,
  genres,
  ranking
}: HeroSectionProps) {
  return (
    <motion.div
      className="relative w-full h-[90vh] overflow-hidden"
      initial={{ opacity: 0 }}
      animate={{ opacity: 1 }}
      transition={{ duration: 0.7 }}
    >
      {/* Background image */}
      <div className="absolute inset-0">
        <img
          src={backgroundImage}
          alt={title}
          className="w-full h-full object-cover"
        />
        {/* Cinematic gradient overlay */}
        <div
          className="absolute inset-0"
          style={{
            background: "var(--gradient-hero)"
          }}
        />
      </div>

      {/* Navigation arrows */}
      <motion.button
        className="absolute left-16 top-1/2 -translate-y-1/2 w-12 h-12 rounded-full flex items-center justify-center z-10"
        style={{
          backgroundColor: "rgba(0, 201, 80, 0.15)",
          border: "1px solid rgba(0, 201, 80, 0.2)",
          boxShadow: "var(--shadow-button)"
        }}
        whileHover={{
          scale: 1.1,
          backgroundColor: "rgba(0, 201, 80, 0.25)"
        }}
        whileTap={{ scale: 0.95 }}
      >
        <ChevronLeft className="w-6 h-6 text-white" />
      </motion.button>

      <motion.button
        className="absolute right-16 top-1/2 -translate-y-1/2 w-12 h-12 rounded-full flex items-center justify-center z-10"
        style={{
          backgroundColor: "rgba(0, 201, 80, 0.15)",
          border: "1px solid rgba(0, 201, 80, 0.2)",
          boxShadow: "var(--shadow-button)"
        }}
        whileHover={{
          scale: 1.1,
          backgroundColor: "rgba(0, 201, 80, 0.25)"
        }}
        whileTap={{ scale: 0.95 }}
      >
        <ChevronRight className="w-6 h-6 text-white" />
      </motion.button>

      {/* Content */}
      <div className="absolute bottom-24 left-16 right-16 z-10">
        <motion.div
          className="max-w-3xl space-y-6"
          initial={{ y: 50, opacity: 0 }}
          animate={{ y: 0, opacity: 1 }}
          transition={{ delay: 0.3, duration: 0.7 }}
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
            {title}
          </h1>

          {/* Metadata */}
          <div className="flex items-center gap-4 text-lg">
            {year && (
              <span className="text-[var(--text-muted)] font-semibold">
                {year}
              </span>
            )}
            {duration && (
              <>
                <span className="text-[var(--text-disabled)]">â€¢</span>
                <span className="text-[var(--text-muted)] font-medium">
                  {duration}
                </span>
              </>
            )}
            {imdbRating && (
              <>
                <span className="text-[var(--text-disabled)]">â€¢</span>
                <div className="flex items-center gap-2">
                  <span className="text-[var(--text-warning)] font-medium">
                    IMDb
                  </span>
                  <span className="text-[var(--text-muted)]">{imdbRating}</span>
                </div>
              </>
            )}
            {rating && (
              <>
                <span className="text-[var(--text-disabled)]">â€¢</span>
                <span
                  className="px-2 py-0.5 rounded text-sm font-medium"
                  style={{
                    border: "1px solid var(--text-disabled)",
                    color: "var(--text-secondary)"
                  }}
                >
                  {rating}
                </span>
              </>
            )}
          </div>

          {/* Ranking badge */}
          {ranking && (
            <motion.div
              className="flex items-center gap-2"
              initial={{ scale: 0.9, opacity: 0 }}
              animate={{ scale: 1, opacity: 1 }}
              transition={{ delay: 0.5 }}
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
                NÃºmero {ranking.position} en {ranking.category}
              </span>
            </motion.div>
          )}

          {/* Genres */}
          {genres && genres.length > 0 && (
            <div className="flex gap-2">
              {genres.map((genre, index) => (
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
            {description.length > 300 ? (
              <>
                {description.slice(0, 300)}...
                <button className="ml-2 text-[var(--text-accent)] font-bold hover:underline">
                  Ver mÃ¡s.
                </button>
              </>
            ) : (
              description
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
      </div>
    </motion.div>
  );
}
