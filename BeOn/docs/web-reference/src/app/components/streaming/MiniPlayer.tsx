import { motion, AnimatePresence } from "motion/react";
import { Play, Pause, SkipBack, SkipForward, Volume2, Maximize2, X } from "lucide-react";
import { useState } from "react";

interface MiniPlayerProps {
  isVisible: boolean;
  onClose: () => void;
  title: string;
  subtitle?: string;
  thumbnail: string;
  progress: number;
  isPlaying?: boolean;
}

export function MiniPlayer({
  isVisible,
  onClose,
  title,
  subtitle,
  thumbnail,
  progress,
  isPlaying = false
}: MiniPlayerProps) {
  const [playing, setPlaying] = useState(isPlaying);
  const [volume, setVolume] = useState(80);

  return (
    <AnimatePresence>
      {isVisible && (
        <motion.div
          className="fixed bottom-0 left-0 right-0 z-[300]"
          initial={{ y: 100, opacity: 0 }}
          animate={{ y: 0, opacity: 1 }}
          exit={{ y: 100, opacity: 0 }}
          transition={{
            type: "spring",
            stiffness: 260,
            damping: 20
          }}
        >
          {/* Progress bar */}
          <div
            className="absolute top-0 left-0 right-0 h-1 bg-[var(--progress-bar-bg)] cursor-pointer group"
          >
            <motion.div
              className="h-full bg-[var(--progress-bar-fill)]"
              style={{ width: `${progress}%` }}
              whileHover={{
                height: "var(--progress-bar-hover-height)"
              }}
            />
          </div>

          {/* Player controls */}
          <div
            className="px-6 py-4"
            style={{
              backgroundColor: "var(--player-bg)",
              backdropFilter: "blur(20px)"
            }}
          >
            <div className="flex items-center justify-between max-w-screen-2xl mx-auto">
              {/* Left: Now playing info */}
              <div className="flex items-center gap-4 flex-1 min-w-0">
                <motion.img
                  src={thumbnail}
                  alt={title}
                  className="w-14 h-14 rounded object-cover"
                  whileHover={{ scale: 1.1 }}
                />
                <div className="min-w-0">
                  <h4 className="text-white font-semibold truncate text-sm">
                    {title}
                  </h4>
                  {subtitle && (
                    <p className="text-[var(--text-muted)] text-xs truncate">
                      {subtitle}
                    </p>
                  )}
                </div>
              </div>

              {/* Center: Playback controls */}
              <div className="flex items-center gap-4">
                <motion.button
                  className="text-white/80"
                  whileHover={{ scale: 1.1, color: "#ffffff" }}
                  whileTap={{ scale: 0.95 }}
                >
                  <SkipBack className="w-5 h-5" />
                </motion.button>

                <motion.button
                  className="w-10 h-10 rounded-full bg-white flex items-center justify-center"
                  whileHover={{ scale: 1.1 }}
                  whileTap={{ scale: 0.95 }}
                  onClick={() => setPlaying(!playing)}
                >
                  {playing ? (
                    <Pause className="w-5 h-5 text-black fill-black" />
                  ) : (
                    <Play className="w-5 h-5 text-black fill-black ml-0.5" />
                  )}
                </motion.button>

                <motion.button
                  className="text-white/80"
                  whileHover={{ scale: 1.1, color: "#ffffff" }}
                  whileTap={{ scale: 0.95 }}
                >
                  <SkipForward className="w-5 h-5" />
                </motion.button>
              </div>

              {/* Right: Additional controls */}
              <div className="flex items-center gap-4 flex-1 justify-end">
                <div className="flex items-center gap-2">
                  <Volume2 className="w-4 h-4 text-white/80" />
                  <input
                    type="range"
                    min="0"
                    max="100"
                    value={volume}
                    onChange={(e) => setVolume(parseInt(e.target.value))}
                    className="w-24 h-1 rounded-full appearance-none cursor-pointer"
                    style={{
                      background: `linear-gradient(to right, var(--accent-primary) 0%, var(--accent-primary) ${volume}%, rgba(255,255,255,0.2) ${volume}%, rgba(255,255,255,0.2) 100%)`
                    }}
                  />
                </div>

                <motion.button
                  className="text-white/80"
                  whileHover={{ scale: 1.1, color: "#ffffff" }}
                  whileTap={{ scale: 0.95 }}
                >
                  <Maximize2 className="w-4 h-4" />
                </motion.button>

                <motion.button
                  className="text-white/80"
                  whileHover={{ scale: 1.1, color: "#ffffff" }}
                  whileTap={{ scale: 0.95 }}
                  onClick={onClose}
                >
                  <X className="w-4 h-4" />
                </motion.button>
              </div>
            </div>
          </div>
        </motion.div>
      )}
    </AnimatePresence>
  );
}
