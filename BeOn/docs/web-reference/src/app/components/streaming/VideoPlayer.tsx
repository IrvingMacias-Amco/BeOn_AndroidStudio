import { motion, AnimatePresence } from "motion/react";
import { Play, Pause, Volume2, VolumeX, Settings, Maximize, ChevronLeft, Subtitles, SkipForward } from "lucide-react";
import { useState, useEffect, useRef } from "react";
import { useNavigate } from "react-router";

interface VideoPlayerProps {
  title: string;
  backgroundImage: string;
  onClose?: () => void;
}

export function VideoPlayer({ title, backgroundImage, onClose }: VideoPlayerProps) {
  const navigate = useNavigate();
  const [isPlaying, setIsPlaying] = useState(false);
  const [showControls, setShowControls] = useState(true);
  const [progress, setProgress] = useState(28.5); // 0-100
  const [currentTime, setCurrentTime] = useState(2732); // seconds (45:32)
  const [duration, setDuration] = useState(9374); // seconds (2:36:14)
  const [volume, setVolume] = useState(80);
  const [isMuted, setIsMuted] = useState(false);
  const [showSettings, setShowSettings] = useState(false);
  const [showSubtitles, setShowSubtitles] = useState(false);
  const [showNextEpisode, setShowNextEpisode] = useState(false);
  const [playbackSpeed, setPlaybackSpeed] = useState(1);
  const [quality, setQuality] = useState("1080p");

  const controlsTimeoutRef = useRef<NodeJS.Timeout>();
  const playerRef = useRef<HTMLDivElement>(null);

  // Auto-hide controls logic
  useEffect(() => {
    if (isPlaying && !showSettings && showControls) {
      controlsTimeoutRef.current = setTimeout(() => {
        setShowControls(false);
      }, 3000);
    }

    return () => {
      if (controlsTimeoutRef.current) {
        clearTimeout(controlsTimeoutRef.current);
      }
    };
  }, [isPlaying, showControls, showSettings]);

  // Show next episode CTA when 20 seconds remain
  useEffect(() => {
    const timeRemaining = duration - currentTime;
    if (timeRemaining <= 20 && timeRemaining > 0) {
      setShowNextEpisode(true);
    } else {
      setShowNextEpisode(false);
    }
  }, [currentTime, duration]);

  const handleMouseMove = () => {
    setShowControls(true);
    if (controlsTimeoutRef.current) {
      clearTimeout(controlsTimeoutRef.current);
    }
  };

  const handlePlayPause = () => {
    setIsPlaying(!isPlaying);
    setShowControls(true);
  };

  const handleSkip = (seconds: number) => {
    const newTime = Math.max(0, Math.min(duration, currentTime + seconds));
    setCurrentTime(newTime);
    setProgress((newTime / duration) * 100);
  };

  const handleSeek = (e: React.MouseEvent<HTMLDivElement>) => {
    const rect = e.currentTarget.getBoundingClientRect();
    const pos = (e.clientX - rect.left) / rect.width;
    const newTime = pos * duration;
    setCurrentTime(newTime);
    setProgress(pos * 100);
  };

  const handleVolumeToggle = () => {
    setIsMuted(!isMuted);
  };

  const handleClose = () => {
    if (onClose) {
      onClose();
    } else {
      navigate(-1);
    }
  };

  const formatTime = (seconds: number) => {
    const h = Math.floor(seconds / 3600);
    const m = Math.floor((seconds % 3600) / 60);
    const s = Math.floor(seconds % 60);
    if (h > 0) {
      return `${h}:${m.toString().padStart(2, '0')}:${s.toString().padStart(2, '0')}`;
    }
    return `${m}:${s.toString().padStart(2, '0')}`;
  };

  return (
    <div
      ref={playerRef}
      className="fixed inset-0 bg-black z-50"
      onMouseMove={handleMouseMove}
      onClick={() => setShowControls(true)}
    >
      {/* Video Container */}
      <div className="absolute inset-0">
        <img
          src={backgroundImage}
          alt={title}
          className="w-full h-full object-cover"
        />
        <div className="absolute inset-0 bg-black/30" />
      </div>

      {/* Central Playback Controls */}
      <AnimatePresence>
        {showControls && (
          <motion.div
            className="absolute inset-0 flex items-center justify-center gap-12 pointer-events-none"
            initial={{ opacity: 0 }}
            animate={{ opacity: 1 }}
            exit={{ opacity: 0 }}
            transition={{ duration: 0.2, ease: "easeInOut" }}
          >
            {/* Backward 10s */}
            <motion.button
              className="pointer-events-auto w-[70px] h-[70px] rounded-full flex flex-col items-center justify-center"
              style={{
                backgroundColor: "rgba(51, 51, 51, 0.5)",
                border: "2px solid rgba(255, 255, 255, 0.2)",
                backdropFilter: "blur(10px)"
              }}
              whileHover={{
                scale: 1.1,
                backgroundColor: "rgba(51, 51, 51, 0.7)",
                borderColor: "var(--accent-primary)"
              }}
              whileTap={{ scale: 0.95 }}
              onClick={() => handleSkip(-10)}
            >
              <svg className="w-7 h-7" viewBox="0 0 24 24" fill="none" stroke="white" strokeWidth="2">
                <path d="M19 12H5M5 12L12 19M5 12L12 5" strokeLinecap="round" strokeLinejoin="round" />
              </svg>
              <span className="text-white text-sm font-medium mt-1">10s</span>
            </motion.button>

            {/* Play/Pause - Larger Premium Button */}
            <motion.button
              className="pointer-events-auto w-[110px] h-[110px] rounded-full flex items-center justify-center"
              style={{
                background: "var(--gradient-button-primary)",
                boxShadow: "var(--shadow-glow-strong)"
              }}
              whileHover={{ scale: 1.08 }}
              whileTap={{ scale: 0.95 }}
              onClick={handlePlayPause}
            >
              {isPlaying ? (
                <Pause className="w-14 h-14 text-black fill-black" />
              ) : (
                <Play className="w-14 h-14 text-black fill-black ml-2" />
              )}
            </motion.button>

            {/* Forward 10s */}
            <motion.button
              className="pointer-events-auto w-[70px] h-[70px] rounded-full flex flex-col items-center justify-center"
              style={{
                backgroundColor: "rgba(51, 51, 51, 0.5)",
                border: "2px solid rgba(255, 255, 255, 0.2)",
                backdropFilter: "blur(10px)"
              }}
              whileHover={{
                scale: 1.1,
                backgroundColor: "rgba(51, 51, 51, 0.7)",
                borderColor: "var(--accent-primary)"
              }}
              whileTap={{ scale: 0.95 }}
              onClick={() => handleSkip(10)}
            >
              <svg className="w-7 h-7" viewBox="0 0 24 24" fill="none" stroke="white" strokeWidth="2">
                <path d="M5 12H19M19 12L12 5M19 12L12 19" strokeLinecap="round" strokeLinejoin="round" />
              </svg>
              <span className="text-white text-sm font-medium mt-1">10s</span>
            </motion.button>
          </motion.div>
        )}
      </AnimatePresence>

      {/* Top Navigation Bar */}
      <AnimatePresence>
        {showControls && (
          <motion.div
            className="absolute top-0 left-0 right-0 px-16 pt-4 pb-16"
            style={{
              background: "linear-gradient(to bottom, rgba(0, 0, 0, 0.8) 0%, rgba(0, 0, 0, 0) 100%)"
            }}
            initial={{ opacity: 0, y: -20 }}
            animate={{ opacity: 1, y: 0 }}
            exit={{ opacity: 0, y: -20 }}
            transition={{ duration: 0.2, ease: "easeInOut" }}
          >
            <div className="flex items-center gap-4">
              <motion.button
                className="flex items-center gap-2 text-white"
                whileHover={{ scale: 1.05 }}
                whileTap={{ scale: 0.95 }}
                onClick={handleClose}
              >
                <ChevronLeft className="w-5 h-5" />
                <span className="font-semibold text-sm">Volver</span>
              </motion.button>

              <h2 className="font-bold text-xl text-white ml-auto">{title}</h2>
            </div>
          </motion.div>
        )}
      </AnimatePresence>

      {/* Bottom Controls Bar */}
      <AnimatePresence>
        {showControls && (
          <motion.div
            className="absolute bottom-0 left-0 right-0 px-16 pb-4"
            style={{
              background: "linear-gradient(to top, rgba(0, 0, 0, 0.8) 0%, rgba(0, 0, 0, 0) 100%)"
            }}
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            exit={{ opacity: 0, y: 20 }}
            transition={{ duration: 0.2, ease: "easeInOut" }}
          >
            {/* Seek Bar */}
            <div className="mb-4">
              <div
                className="h-1 bg-white/30 rounded-full cursor-pointer relative group"
                onClick={handleSeek}
              >
                <motion.div
                  className="h-full rounded-full relative"
                  style={{
                    width: `${progress}%`,
                    background: "var(--gradient-button-primary)"
                  }}
                >
                  <div
                    className="absolute right-0 top-1/2 -translate-y-1/2 w-3 h-3 rounded-full bg-white opacity-0 group-hover:opacity-100 transition-opacity"
                    style={{
                      boxShadow: "0 0 10px rgba(34, 197, 94, 0.6)"
                    }}
                  />
                </motion.div>
              </div>
            </div>

            {/* Control Buttons Row */}
            <div className="flex items-center justify-between">
              {/* Left Side: Play + Volume + Time */}
              <div className="flex items-center gap-4">
                <motion.button
                  className="w-8 h-8 flex items-center justify-center text-white"
                  whileHover={{ scale: 1.1 }}
                  whileTap={{ scale: 0.95 }}
                  onClick={handlePlayPause}
                >
                  {isPlaying ? (
                    <Pause className="w-5 h-5 fill-white" />
                  ) : (
                    <Play className="w-5 h-5 fill-white" />
                  )}
                </motion.button>

                <motion.button
                  className="w-7 h-7 flex items-center justify-center text-white"
                  whileHover={{ scale: 1.1 }}
                  whileTap={{ scale: 0.95 }}
                  onClick={handleVolumeToggle}
                >
                  {isMuted || volume === 0 ? (
                    <VolumeX className="w-4 h-4" />
                  ) : (
                    <Volume2 className="w-4 h-4" />
                  )}
                </motion.button>

                <span className="text-white text-xs">
                  {formatTime(currentTime)} / {formatTime(duration)}
                </span>
              </div>

              {/* Right Side: Subtitles + Settings + Fullscreen */}
              <div className="flex items-center gap-2">
                <motion.button
                  className="w-7 h-7 flex items-center justify-center text-white"
                  whileHover={{ scale: 1.1 }}
                  whileTap={{ scale: 0.95 }}
                  onClick={() => setShowSubtitles(!showSubtitles)}
                >
                  <Subtitles className="w-4 h-4" />
                </motion.button>

                <motion.button
                  className="w-7 h-7 flex items-center justify-center text-white relative"
                  whileHover={{ scale: 1.1 }}
                  whileTap={{ scale: 0.95 }}
                  onClick={() => setShowSettings(!showSettings)}
                >
                  <Settings className="w-4 h-4" />
                </motion.button>

                <motion.button
                  className="w-7 h-7 flex items-center justify-center text-white"
                  whileHover={{ scale: 1.1 }}
                  whileTap={{ scale: 0.95 }}
                >
                  <Maximize className="w-4 h-4" />
                </motion.button>
              </div>
            </div>

            {/* Settings Panel */}
            <AnimatePresence>
              {showSettings && (
                <motion.div
                  className="absolute bottom-16 right-16 w-64 rounded-lg overflow-hidden"
                  style={{
                    backgroundColor: "rgba(26, 26, 26, 0.95)",
                    border: "1px solid rgba(255, 255, 255, 0.1)",
                    backdropFilter: "blur(20px)"
                  }}
                  initial={{ opacity: 0, y: 10, scale: 0.95 }}
                  animate={{ opacity: 1, y: 0, scale: 1 }}
                  exit={{ opacity: 0, y: 10, scale: 0.95 }}
                  transition={{ duration: 0.2 }}
                >
                  <div className="p-4 space-y-4">
                    {/* Playback Speed */}
                    <div>
                      <p className="text-white text-sm font-semibold mb-2">Velocidad de reproducciÃ³n</p>
                      <div className="flex gap-2">
                        {[0.5, 0.75, 1, 1.25, 1.5, 2].map((speed) => (
                          <button
                            key={speed}
                            className="px-3 py-1.5 rounded text-xs font-medium transition-colors"
                            style={{
                              backgroundColor: playbackSpeed === speed ? "var(--accent-primary)" : "rgba(255, 255, 255, 0.1)",
                              color: playbackSpeed === speed ? "black" : "white"
                            }}
                            onClick={() => setPlaybackSpeed(speed)}
                          >
                            {speed}x
                          </button>
                        ))}
                      </div>
                    </div>

                    {/* Video Quality */}
                    <div>
                      <p className="text-white text-sm font-semibold mb-2">Calidad</p>
                      <div className="space-y-1">
                        {["Auto", "1080p", "720p", "480p", "360p"].map((q) => (
                          <button
                            key={q}
                            className="w-full px-3 py-2 rounded text-sm text-left transition-colors"
                            style={{
                              backgroundColor: quality === q ? "rgba(34, 197, 94, 0.2)" : "transparent",
                              color: quality === q ? "var(--accent-primary)" : "white"
                            }}
                            onClick={() => setQuality(q)}
                          >
                            {q}
                          </button>
                        ))}
                      </div>
                    </div>
                  </div>
                </motion.div>
              )}
            </AnimatePresence>
          </motion.div>
        )}
      </AnimatePresence>

      {/* Next Episode CTA */}
      <AnimatePresence>
        {showNextEpisode && (
          <motion.div
            className="absolute right-16 bottom-32 w-80 rounded-lg overflow-hidden"
            style={{
              backgroundColor: "rgba(26, 26, 26, 0.95)",
              border: "1px solid rgba(255, 255, 255, 0.1)",
              backdropFilter: "blur(20px)"
            }}
            initial={{ opacity: 0, x: 20 }}
            animate={{ opacity: 1, x: 0 }}
            exit={{ opacity: 0, x: 20 }}
            transition={{ duration: 0.3 }}
          >
            <div className="p-4">
              <div className="flex items-center gap-4">
                <div className="w-24 h-14 rounded bg-gray-700 overflow-hidden">
                  <img
                    src={backgroundImage}
                    alt="Next Episode"
                    className="w-full h-full object-cover"
                  />
                </div>
                <div className="flex-1">
                  <p className="text-white text-sm font-semibold">Siguiente episodio</p>
                  <p className="text-gray-400 text-xs">Episodio 2: TÃ­tulo del episodio</p>
                </div>
                <motion.button
                  className="px-4 py-2 rounded font-semibold text-sm"
                  style={{
                    background: "var(--gradient-button-primary)",
                    color: "black"
                  }}
                  whileHover={{ scale: 1.05 }}
                  whileTap={{ scale: 0.95 }}
                >
                  <SkipForward className="w-4 h-4" />
                </motion.button>
              </div>
            </div>
          </motion.div>
        )}
      </AnimatePresence>
    </div>
  );
}
