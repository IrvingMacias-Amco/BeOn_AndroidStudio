import { useState } from "react";
import { motion } from "motion/react";
import { AnimatePresence } from "motion/react";
import { X } from "lucide-react";
import { MobileHome, MobilePlatform } from "./MobileHome";
import { MobileContentDetail } from "./MobileContentDetail";

interface MobilePreviewProps {
  platform: MobilePlatform | null;
  onClose: () => void;
}

/* â”€â”€ iPhone 15 Pro frame â”€â”€ */
function IOSFrame({ children }: { children: React.ReactNode }) {
  return (
    <div
      className="relative flex flex-col overflow-hidden"
      style={{
        width: 393,
        height: 720,
        borderRadius: 54,
        background: "#1C1C1E",
        boxShadow: `
          0 0 0 1px rgba(255,255,255,0.15),
          0 0 0 10px #2C2C2E,
          0 0 0 12px rgba(255,255,255,0.06),
          0 32px 80px rgba(0,0,0,0.9),
          0 0 120px rgba(34,197,94,0.08)
        `,
      }}
    >
      {/* Dynamic Island */}
      <div
        className="absolute top-3 left-1/2 -translate-x-1/2 z-10 flex items-center justify-center gap-2.5"
        style={{
          width: 126,
          height: 34,
          borderRadius: 20,
          backgroundColor: "#000",
        }}
      >
        {/* Front camera */}
        <div className="w-3 h-3 rounded-full bg-[#1C1C1E] border border-[#2A2A2A] relative">
          <div className="absolute inset-1 rounded-full bg-[#0D0D0D]" />
        </div>
        {/* Sensors */}
        <div className="flex gap-1 items-center">
          <div className="w-2 h-2 rounded-full" style={{ backgroundColor: "rgba(26,26,28,0.9)" }} />
        </div>
      </div>

      {/* Side buttons */}
      {/* Volume up */}
      <div className="absolute left-[-3px] top-[120px] w-1 h-8 rounded-l-sm" style={{ backgroundColor: "#3A3A3C" }} />
      <div className="absolute left-[-3px] top-[162px] w-1 h-8 rounded-l-sm" style={{ backgroundColor: "#3A3A3C" }} />
      {/* Power */}
      <div className="absolute right-[-3px] top-[140px] w-1 h-14 rounded-r-sm" style={{ backgroundColor: "#3A3A3C" }} />

      {/* Screen */}
      <div
        className="flex-1 overflow-hidden"
        style={{
          borderRadius: 46,
          margin: 6,
          backgroundColor: "#000",
        }}
      >
        {children}
      </div>

      {/* Home indicator */}
      <div className="absolute bottom-2 left-1/2 -translate-x-1/2">
        <div className="w-28 h-1 rounded-full" style={{ backgroundColor: "rgba(255,255,255,0.3)" }} />
      </div>
    </div>
  );
}

/* â”€â”€ Google Pixel 8 frame â”€â”€ */
function AndroidFrame({ children }: { children: React.ReactNode }) {
  return (
    <div
      className="relative flex flex-col overflow-hidden"
      style={{
        width: 393,
        height: 720,
        borderRadius: 44,
        background: "#1A1A1A",
        boxShadow: `
          0 0 0 1px rgba(255,255,255,0.1),
          0 0 0 9px #111111,
          0 0 0 11px rgba(255,255,255,0.05),
          0 32px 80px rgba(0,0,0,0.9),
          0 0 120px rgba(34,197,94,0.08)
        `,
      }}
    >
      {/* Punch-hole camera */}
      <div className="absolute top-3.5 left-1/2 -translate-x-1/2 z-10">
        <div
          className="rounded-full bg-black flex items-center justify-center"
          style={{ width: 12, height: 12 }}
        >
          <div className="w-2 h-2 rounded-full bg-[#1a1a1a]" />
        </div>
      </div>

      {/* Side buttons */}
      <div className="absolute right-[-2px] top-[100px] w-0.5 h-12 rounded-r-sm" style={{ backgroundColor: "#2A2A2A" }} />
      <div className="absolute right-[-2px] top-[160px] w-0.5 h-20 rounded-r-sm" style={{ backgroundColor: "#2A2A2A" }} />

      {/* Screen */}
      <div
        className="flex-1 overflow-hidden"
        style={{
          borderRadius: 36,
          margin: 5,
          backgroundColor: "#000",
        }}
      >
        {children}
      </div>

      {/* Android gesture bar */}
      <div className="absolute bottom-2 left-1/2 -translate-x-1/2">
        <div className="w-20 h-1 rounded-full" style={{ backgroundColor: "rgba(255,255,255,0.2)" }} />
      </div>
    </div>
  );
}

/* â”€â”€ Platform badge â”€â”€ */
function PlatformBadge({ platform }: { platform: MobilePlatform }) {
  return (
    <div
      className="flex items-center gap-2 px-4 py-2 rounded-full mb-6"
      style={{
        backgroundColor: "rgba(255,255,255,0.06)",
        border: "1px solid rgba(255,255,255,0.1)",
        backdropFilter: "blur(8px)"
      }}
    >
      {platform === "ios" ? (
        <svg width="16" height="16" viewBox="0 0 24 24" fill="white">
          <path d="M18.71 19.5c-.83 1.24-1.71 2.45-3.05 2.47-1.34.03-1.77-.79-3.29-.79-1.53 0-2 .77-3.27.82-1.31.05-2.3-1.32-3.14-2.53C4.25 17 2.94 12.45 4.7 9.39c.87-1.52 2.43-2.48 4.12-2.51 1.28-.02 2.5.87 3.29.87.78 0 2.26-1.07 3.8-.91.65.03 2.47.26 3.64 1.98-.09.06-2.17 1.28-2.15 3.81.03 3.02 2.65 4.03 2.68 4.04-.03.07-.42 1.44-1.38 2.83M13 3.5c.73-.83 1.94-1.46 2.94-1.5.13 1.17-.34 2.35-1.04 3.19-.69.85-1.83 1.51-2.95 1.42-.15-1.15.41-2.35 1.05-3.11z" />
        </svg>
      ) : (
        <svg width="16" height="16" viewBox="0 0 24 24" fill="white">
          <path d="M17.523 15.341c-.105.29-.23.564-.373.82-.197.354-.38.599-.544.735-.217.2-.45.302-.7.308-.18 0-.396-.05-.647-.154-.252-.103-.483-.154-.696-.154-.224 0-.462.051-.716.154-.254.103-.459.157-.617.162-.24.01-.478-.095-.716-.315-.175-.15-.366-.403-.573-.757-.246-.409-.447-.885-.605-1.427-.169-.583-.254-1.148-.254-1.695 0-.626.135-1.167.406-1.62.213-.365.496-.653.85-.864.354-.21.737-.317 1.148-.323.231 0 .535.071.912.21.376.14.617.211.724.211.08 0 .35-.083.808-.249.433-.155.799-.219 1.1-.195.812.065 1.422.383 1.829.954-.726.44-1.085 1.057-1.079 1.847.006.617.23 1.13.672 1.537.2.19.423.337.671.44-.054.156-.111.305-.171.446zM14.69 3.66c0 .484-.177.936-.529 1.353-.425.496-.94.783-1.497.737-.007-.058-.011-.12-.011-.184 0-.464.201-.96.56-1.366.178-.205.404-.375.68-.513.275-.137.536-.213.78-.226.008.067.012.133.012.199z"/>
        </svg>
      )}
      <span className="text-white font-semibold text-sm">
        {platform === "ios" ? "iOS 17 Â· iPhone 15 Pro" : "Android 14 Â· Pixel 8"}
      </span>
    </div>
  );
}

export function MobilePreview({ platform, onClose }: MobilePreviewProps) {
  const [currentView, setCurrentView] = useState<"home" | "detail">("home");

  return (
    <AnimatePresence>
      {platform && (
        <motion.div
          className="fixed inset-0 z-[500] flex items-center justify-center"
          style={{ backgroundColor: "rgba(0,0,0,0.92)", backdropFilter: "blur(24px)" }}
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
          exit={{ opacity: 0 }}
          transition={{ duration: 0.3 }}
          onClick={onClose}
        >
          {/* Close button */}
          <motion.button
            className="absolute top-6 right-6 w-10 h-10 rounded-full flex items-center justify-center z-10"
            style={{
              backgroundColor: "rgba(255,255,255,0.1)",
              border: "1px solid rgba(255,255,255,0.15)",
              backdropFilter: "blur(8px)"
            }}
            onClick={onClose}
            whileHover={{ scale: 1.1, backgroundColor: "rgba(255,255,255,0.15)" }}
            whileTap={{ scale: 0.95 }}
            initial={{ opacity: 0, scale: 0.8 }}
            animate={{ opacity: 1, scale: 1 }}
            transition={{ delay: 0.2 }}
          >
            <X className="w-5 h-5 text-white" />
          </motion.button>

          {/* Device + label */}
          <motion.div
            className="flex flex-col items-center"
            onClick={(e) => e.stopPropagation()}
            initial={{ scale: 0.85, opacity: 0, y: 40 }}
            animate={{ scale: 1, opacity: 1, y: 0 }}
            exit={{ scale: 0.85, opacity: 0, y: 40 }}
            transition={{ type: "spring", stiffness: 280, damping: 28 }}
          >
            <div className="flex items-center gap-4 mb-6">
              <PlatformBadge platform={platform} />

              {/* View toggle */}
              <div
                className="flex items-center gap-1 p-1 rounded-full"
                style={{
                  backgroundColor: "rgba(255,255,255,0.06)",
                  border: "1px solid rgba(255,255,255,0.1)",
                }}
              >
                <button
                  onClick={() => setCurrentView("home")}
                  className="px-4 py-1.5 rounded-full text-xs font-medium transition-all"
                  style={{
                    backgroundColor: currentView === "home" ? "rgba(34,197,94,0.2)" : "transparent",
                    color: currentView === "home" ? "#22C55E" : "rgba(255,255,255,0.5)",
                  }}
                >
                  Home
                </button>
                <button
                  onClick={() => setCurrentView("detail")}
                  className="px-4 py-1.5 rounded-full text-xs font-medium transition-all"
                  style={{
                    backgroundColor: currentView === "detail" ? "rgba(34,197,94,0.2)" : "transparent",
                    color: currentView === "detail" ? "#22C55E" : "rgba(255,255,255,0.5)",
                  }}
                >
                  Detalle
                </button>
              </div>
            </div>

            {platform === "ios" ? (
              <IOSFrame>
                {currentView === "home" ? (
                  <MobileHome platform="ios" />
                ) : (
                  <MobileContentDetail platform="ios" onBack={() => setCurrentView("home")} />
                )}
              </IOSFrame>
            ) : (
              <AndroidFrame>
                {currentView === "home" ? (
                  <MobileHome platform="android" />
                ) : (
                  <MobileContentDetail platform="android" onBack={() => setCurrentView("home")} />
                )}
              </AndroidFrame>
            )}

            <p className="mt-6 text-sm" style={{ color: "rgba(255,255,255,0.35)" }}>
              Haz clic fuera del dispositivo para cerrar
            </p>
          </motion.div>
        </motion.div>
      )}
    </AnimatePresence>
  );
}
