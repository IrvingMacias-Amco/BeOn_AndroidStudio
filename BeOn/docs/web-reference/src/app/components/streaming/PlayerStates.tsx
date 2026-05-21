import { motion } from "motion/react";
import { Loader2, AlertTriangle, X } from "lucide-react";
import React from "react";

// Buffering/Loading State
export function BufferingState() {
  return (
    <div className="absolute inset-0 flex items-center justify-center bg-black/60 backdrop-blur-sm z-40">
      <motion.div
        className="flex flex-col items-center gap-4"
        initial={{ opacity: 0, scale: 0.9 }}
        animate={{ opacity: 1, scale: 1 }}
        transition={{ duration: 0.3 }}
      >
        <motion.div
          animate={{ rotate: 360 }}
          transition={{ duration: 1, repeat: Infinity, ease: "linear" }}
        >
          <Loader2
            className="w-16 h-16"
            style={{ color: "var(--accent-primary)" }}
          />
        </motion.div>
        <p className="text-white text-lg font-medium">Cargando...</p>
      </motion.div>
    </div>
  );
}

// DRM Error State
interface DRMErrorStateProps {
  onRetry?: () => void;
  onClose?: () => void;
}

export function DRMErrorState({ onRetry, onClose }: DRMErrorStateProps) {
  return (
    <div className="absolute inset-0 flex items-center justify-center bg-black z-50">
      <motion.div
        className="max-w-md w-full mx-4 p-8 rounded-xl text-center"
        style={{
          backgroundColor: "rgba(26, 26, 26, 0.95)",
          border: "1px solid rgba(255, 255, 255, 0.1)",
          backdropFilter: "blur(20px)"
        }}
        initial={{ opacity: 0, y: 20 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.3 }}
      >
        <div className="flex justify-center mb-4">
          <div
            className="w-16 h-16 rounded-full flex items-center justify-center"
            style={{
              backgroundColor: "rgba(239, 68, 68, 0.2)",
              border: "2px solid rgba(239, 68, 68, 0.4)"
            }}
          >
            <AlertTriangle className="w-8 h-8 text-red-500" />
          </div>
        </div>

        <h2 className="text-white text-2xl font-bold mb-3">Error de reproducciÃ³n</h2>
        <p className="text-gray-400 text-base mb-6 leading-relaxed">
          No pudimos reproducir este contenido debido a restricciones de derechos digitales (DRM). Por favor, verifica tu conexiÃ³n e intenta nuevamente.
        </p>

        <div className="flex gap-3 justify-center">
          {onRetry && (
            <motion.button
              className="px-6 py-3 rounded-full font-bold text-black"
              style={{
                background: "var(--gradient-button-primary)",
                boxShadow: "var(--shadow-glow-medium)"
              }}
              whileHover={{ scale: 1.05 }}
              whileTap={{ scale: 0.95 }}
              onClick={onRetry}
            >
              Reintentar
            </motion.button>
          )}
          {onClose && (
            <motion.button
              className="px-6 py-3 rounded-full font-semibold text-white"
              style={{
                border: "2px solid rgba(255, 255, 255, 0.3)"
              }}
              whileHover={{
                scale: 1.05,
                borderColor: "rgba(255, 255, 255, 0.6)"
              }}
              whileTap={{ scale: 0.95 }}
              onClick={onClose}
            >
              Cerrar
            </motion.button>
          )}
        </div>

        <p className="text-gray-500 text-xs mt-6">
          CÃ³digo de error: DRM_LICENSE_ERROR_0x8004C029
        </p>
      </motion.div>
    </div>
  );
}

// Pre-roll Advertisement State
interface PreRollAdStateProps {
  duration?: number;
  onSkip?: () => void;
  canSkip?: boolean;
  skipAfter?: number;
}

export function PreRollAdState({
  duration = 30,
  onSkip,
  canSkip = false,
  skipAfter = 5
}: PreRollAdStateProps) {
  const [timeRemaining, setTimeRemaining] = React.useState(duration);
  const [canSkipNow, setCanSkipNow] = React.useState(false);

  React.useEffect(() => {
    const interval = setInterval(() => {
      setTimeRemaining((prev) => {
        if (prev <= 1) {
          clearInterval(interval);
          onSkip?.();
          return 0;
        }
        return prev - 1;
      });
    }, 1000);

    return () => clearInterval(interval);
  }, [onSkip]);

  React.useEffect(() => {
    if (canSkip && timeRemaining <= duration - skipAfter) {
      setCanSkipNow(true);
    }
  }, [timeRemaining, canSkip, duration, skipAfter]);

  return (
    <div className="absolute inset-0 bg-black z-40">
      {/* Ad Content Placeholder */}
      <div className="absolute inset-0 flex items-center justify-center bg-gradient-to-br from-gray-900 to-black">
        <div className="text-center">
          <div
            className="w-32 h-32 rounded-full mx-auto mb-6 flex items-center justify-center"
            style={{
              background: "var(--gradient-button-primary)",
              boxShadow: "var(--shadow-glow-strong)"
            }}
          >
            <svg className="w-16 h-16 text-black" viewBox="0 0 24 24" fill="currentColor">
              <path d="M8 5v14l11-7z" />
            </svg>
          </div>
          <p className="text-white text-2xl font-bold mb-2">Anuncio publicitario</p>
          <p className="text-gray-400 text-lg">Tu contenido comenzarÃ¡ pronto</p>
        </div>
      </div>

      {/* Ad Controls Overlay */}
      <div className="absolute top-0 left-0 right-0 px-16 pt-4">
        <div className="flex items-center justify-between">
          <div className="px-4 py-2 rounded-full bg-black/60 backdrop-blur-md">
            <span className="text-white text-sm font-medium">
              Anuncio Â· {timeRemaining}s
            </span>
          </div>

          {canSkipNow && onSkip && (
            <motion.button
              className="px-6 py-2 rounded-full font-semibold flex items-center gap-2"
              style={{
                background: "var(--gradient-button-primary)",
                color: "black"
              }}
              initial={{ opacity: 0, x: 20 }}
              animate={{ opacity: 1, x: 0 }}
              whileHover={{ scale: 1.05 }}
              whileTap={{ scale: 0.95 }}
              onClick={onSkip}
            >
              Saltar anuncio
              <X className="w-4 h-4" />
            </motion.button>
          )}
        </div>
      </div>

      {/* Ad Progress Bar */}
      <div className="absolute bottom-0 left-0 right-0 h-1 bg-white/20">
        <motion.div
          className="h-full"
          style={{
            background: "var(--gradient-button-primary)",
            width: `${((duration - timeRemaining) / duration) * 100}%`
          }}
          transition={{ duration: 0.3 }}
        />
      </div>
    </div>
  );
}
