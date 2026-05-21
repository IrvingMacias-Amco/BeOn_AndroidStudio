import { motion } from "motion/react";
import { Plus, Play } from "lucide-react";
import { useState } from "react";

interface Top10CardProps {
  title: string;
  thumbnail: string;
  ranking: number;
  year?: string;
  rating?: string;
  onClick?: () => void;
}

export function Top10Card({
  title,
  thumbnail,
  ranking,
  year,
  rating,
  onClick
}: Top10CardProps) {
  const [isHovered, setIsHovered] = useState(false);

  return (
    <motion.div
      className="group relative cursor-pointer flex items-center"
      onHoverStart={() => setIsHovered(true)}
      onHoverEnd={() => setIsHovered(false)}
      onClick={onClick}
      initial={false}
      animate={isHovered ? "hover" : "default"}
      style={{
        width: ranking === 10 ? "340px" : "308px",
        height: "302px"
      }}
    >
      {/* Large ranking number behind card */}
      <div
        className="absolute left-0 flex items-center justify-start"
        style={{
          width: ranking === 10 ? "160px" : "128px",
          height: "100%",
          zIndex: 0
        }}
      >
        <p
          className="font-black select-none self-end"
          style={{
            fontSize: ranking === 10 ? "240px" : "256px",
            lineHeight: "1",
            color: "transparent",
            WebkitTextStroke: "3px white",
            filter: "drop-shadow(0px 4px 16px rgba(0, 0, 0, 0.9))"
          }}
        >
          {ranking}
        </p>
      </div>

      {/* Card container overlaying the number */}
      <motion.div
        className="absolute rounded-[16px] overflow-hidden"
        style={{
          width: "180px",
          height: "270px",
          left: ranking === 10 ? "160px" : "128px",
          top: "16px",
          border: "1px solid rgba(255, 255, 255, 0.1)",
          backgroundColor: "#18181b",
          boxShadow: "0px 10px 15px -3px rgba(0,0,0,0.1), 0px 4px 6px -4px rgba(0,0,0,0.1)",
          zIndex: 1
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

        {/* Hover overlay */}
        <motion.div
          className="absolute inset-0"
          style={{
            background: "linear-gradient(to top, rgba(0, 0, 0, 0.9) 0%, rgba(0, 0, 0, 0.6) 50%, transparent 100%)"
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
