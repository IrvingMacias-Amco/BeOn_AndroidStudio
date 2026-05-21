import { motion } from "motion/react";
import { Plus, Play } from "lucide-react";
import { useState } from "react";

interface ContinueWatchingCardProps {
  title: string;
  thumbnail: string;
  progress: number;
  year?: string;
  rating?: string;
  onClick?: () => void;
}

export function ContinueWatchingCard({
  title,
  thumbnail,
  progress,
  year,
  rating,
  onClick
}: ContinueWatchingCardProps) {
  const [isHovered, setIsHovered] = useState(false);

  return (
    <motion.div
      className="group relative cursor-pointer w-[320px]"
      onHoverStart={() => setIsHovered(true)}
      onHoverEnd={() => setIsHovered(false)}
      onClick={onClick}
      initial={false}
      animate={isHovered ? "hover" : "default"}
    >
      {/* Card container with 16:9 aspect ratio */}
      <motion.div
        className="relative w-full h-[188px] rounded-[16px] overflow-hidden"
        style={{
          border: "1px solid rgba(255, 255, 255, 0.1)",
          backgroundColor: "#121212",
          boxShadow: "0px 10px 15px -3px rgba(0,0,0,0.1), 0px 4px 6px -4px rgba(0,0,0,0.1)"
        }}
        variants={{
          default: {
            scale: 1
          },
          hover: {
            scale: 1.05,
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

        {/* Bottom gradient overlay with progress */}
        <div
          className="absolute bottom-0 left-0 right-0 h-[60px]"
          style={{
            background: "linear-gradient(to top, rgba(0, 0, 0, 0.9) 0%, transparent 100%)"
          }}
        >
          {/* Progress bar */}
          <div
            className="absolute left-4 top-4 right-4 h-1 rounded-full overflow-hidden"
            style={{
              backgroundColor: "rgba(255, 255, 255, 0.2)"
            }}
          >
            <motion.div
              className="h-full rounded-full"
              style={{
                background: "var(--gradient-button-primary)"
              }}
              initial={{ width: 0 }}
              animate={{ width: `${progress}%` }}
              transition={{ duration: 0.5 }}
            />
          </div>

          {/* Progress text */}
          <p className="absolute left-4 top-7 text-[#a0a0a0] text-sm">
            {progress}% completado
          </p>
        </div>

        {/* Hover overlay */}
        <motion.div
          className="absolute inset-0"
          style={{
            background: "rgba(0, 0, 0, 0.6)"
          }}
          variants={{
            default: { opacity: 0 },
            hover: {
              opacity: 1,
              transition: { duration: 0.2 }
            }
          }}
        >
          {/* Centered Play icon */}
          <motion.div
            className="absolute inset-0 flex items-center justify-center"
            variants={{
              default: { scale: 0.8, opacity: 0 },
              hover: { scale: 1, opacity: 1 }
            }}
          >
            <motion.div
              className="w-16 h-16 rounded-full flex items-center justify-center"
              style={{
                background: "var(--gradient-button-primary)",
                boxShadow: "var(--shadow-glow-strong)"
              }}
              whileHover={{ scale: 1.1 }}
              whileTap={{ scale: 0.95 }}
            >
              <Play className="w-8 h-8 text-black fill-black ml-1" />
            </motion.div>
          </motion.div>

          {/* Add to list button - bottom right */}
          <motion.button
            className="absolute bottom-3 right-3 w-10 h-10 rounded-full flex items-center justify-center"
            style={{
              border: "2px solid rgba(255, 255, 255, 0.8)",
              backdropFilter: "blur(10px)",
              backgroundColor: "rgba(0, 0, 0, 0.3)"
            }}
            variants={{
              default: { scale: 0.8, opacity: 0 },
              hover: { scale: 1, opacity: 1 }
            }}
            whileHover={{
              scale: 1.1,
              borderColor: "var(--accent-primary)",
              backgroundColor: "rgba(34, 197, 94, 0.2)"
            }}
            whileTap={{ scale: 0.95 }}
            onClick={(e) => e.stopPropagation()}
          >
            <Plus className="w-5 h-5 text-white" strokeWidth={2.5} />
          </motion.button>
        </motion.div>
      </motion.div>
    </motion.div>
  );
}
