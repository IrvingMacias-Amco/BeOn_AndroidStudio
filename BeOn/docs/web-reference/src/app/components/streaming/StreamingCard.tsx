import { motion } from "motion/react";
import { Play, Plus, Info } from "lucide-react";
import { useState } from "react";

interface StreamingCardProps {
  title: string;
  thumbnail: string;
  type?: "portrait" | "landscape";
  progress?: number;
  isLive?: boolean;
  rating?: string;
  duration?: string;
  year?: string;
  isNew?: boolean;
}

export function StreamingCard({
  title,
  thumbnail,
  type = "landscape",
  progress,
  isLive,
  rating,
  duration,
  year,
  isNew
}: StreamingCardProps) {
  const [isHovered, setIsHovered] = useState(false);

  return (
    <motion.div
      className="group relative cursor-pointer"
      onHoverStart={() => setIsHovered(true)}
      onHoverEnd={() => setIsHovered(false)}
      initial={false}
      animate={isHovered ? "hover" : "default"}
      style={{
        aspectRatio: type === "portrait" ? "2/3" : "16/9"
      }}
    >
      {/* Main card container */}
      <motion.div
        className="relative w-full h-full rounded-lg overflow-hidden"
        variants={{
          default: {
            scale: 1,
            boxShadow: "0 4px 6px rgba(0, 0, 0, 0.4)"
          },
          hover: {
            scale: 1.05,
            boxShadow: "0 0 20px rgba(94, 234, 212, 0.3)",
            transition: {
              duration: 0.25,
              ease: [0.4, 0, 0.2, 1]
            }
          }
        }}
      >
        {/* Thumbnail */}
        <img
          src={thumbnail}
          alt={title}
          className="w-full h-full object-cover"
        />

        {/* Gradient overlay */}
        <motion.div
          className="absolute inset-0"
          style={{
            background: "linear-gradient(to top, rgba(0, 0, 0, 0.9) 0%, transparent 50%)"
          }}
          variants={{
            default: { opacity: 0.6 },
            hover: { opacity: 1 }
          }}
        />

        {/* Live indicator */}
        {isLive && (
          <motion.div
            className="absolute top-3 left-3 px-3 py-1 rounded-full text-xs font-bold text-white"
            style={{
              backgroundColor: "var(--live-indicator-color)",
              boxShadow: "var(--live-indicator-glow)"
            }}
            animate={{
              opacity: [1, 0.8, 1]
            }}
            transition={{
              duration: 2,
              repeat: Infinity,
              ease: "easeInOut"
            }}
          >
            LIVE
          </motion.div>
        )}

        {/* New badge */}
        {isNew && (
          <div className="absolute top-3 right-3 px-2 py-1 rounded bg-accent-primary text-black text-xs font-bold">
            NEW
          </div>
        )}

        {/* Progress bar */}
        {progress !== undefined && progress > 0 && (
          <div
            className="absolute bottom-0 left-0 right-0 bg-[var(--progress-bar-bg)]"
            style={{ height: "var(--progress-bar-height)" }}
          >
            <motion.div
              className="h-full bg-[var(--progress-bar-fill)]"
              initial={{ width: 0 }}
              animate={{ width: `${progress}%` }}
              transition={{ duration: 0.5 }}
            />
          </div>
        )}

        {/* Hover overlay with actions */}
        <motion.div
          className="absolute inset-0 flex flex-col items-center justify-center gap-3"
          variants={{
            default: { opacity: 0 },
            hover: {
              opacity: 1,
              transition: { delay: 0.1 }
            }
          }}
        >
          <motion.button
            className="w-12 h-12 rounded-full bg-white/90 flex items-center justify-center backdrop-blur-sm"
            whileHover={{ scale: 1.1 }}
            whileTap={{ scale: 0.95 }}
          >
            <Play className="w-6 h-6 text-black fill-black ml-0.5" />
          </motion.button>

          <div className="flex gap-2">
            <motion.button
              className="w-10 h-10 rounded-full border-2 border-white/80 flex items-center justify-center backdrop-blur-sm"
              whileHover={{ scale: 1.1, borderColor: "var(--accent-primary)" }}
              whileTap={{ scale: 0.95 }}
            >
              <Plus className="w-5 h-5 text-white" />
            </motion.button>

            <motion.button
              className="w-10 h-10 rounded-full border-2 border-white/80 flex items-center justify-center backdrop-blur-sm"
              whileHover={{ scale: 1.1, borderColor: "var(--accent-primary)" }}
              whileTap={{ scale: 0.95 }}
            >
              <Info className="w-5 h-5 text-white" />
            </motion.button>
          </div>
        </motion.div>

        {/* Metadata overlay */}
        <motion.div
          className="absolute bottom-0 left-0 right-0 p-4"
          variants={{
            default: { y: 10, opacity: 0 },
            hover: {
              y: 0,
              opacity: 1,
              transition: { delay: 0.15 }
            }
          }}
        >
          <h3 className="text-white font-semibold text-sm mb-1 line-clamp-2">
            {title}
          </h3>
          <div className="flex items-center gap-2 text-xs text-white/80">
            {year && <span>{year}</span>}
            {rating && (
              <>
                <span>â€¢</span>
                <span className="border border-white/50 px-1 rounded">{rating}</span>
              </>
            )}
            {duration && (
              <>
                <span>â€¢</span>
                <span>{duration}</span>
              </>
            )}
          </div>
        </motion.div>
      </motion.div>
    </motion.div>
  );
}
