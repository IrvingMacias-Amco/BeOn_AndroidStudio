import { motion } from "motion/react";

interface SkeletonCardProps {
  type?: "portrait" | "landscape";
}

export function SkeletonCard({ type = "landscape" }: SkeletonCardProps) {
  return (
    <div
      className="relative w-full rounded-lg overflow-hidden"
      style={{
        aspectRatio: type === "portrait" ? "2/3" : "16/9",
        backgroundColor: "var(--skeleton-bg)"
      }}
    >
      {/* Shimmer effect */}
      <motion.div
        className="absolute inset-0"
        style={{
          background: "var(--gradient-skeleton)"
        }}
        animate={{
          x: ["-100%", "100%"]
        }}
        transition={{
          duration: 1.5,
          repeat: Infinity,
          ease: "linear"
        }}
      />

      {/* Content placeholders */}
      <div className="absolute bottom-0 left-0 right-0 p-4 space-y-2">
        <div
          className="h-4 rounded"
          style={{
            width: "70%",
            backgroundColor: "var(--skeleton-highlight)"
          }}
        />
        <div
          className="h-3 rounded"
          style={{
            width: "40%",
            backgroundColor: "var(--skeleton-highlight)"
          }}
        />
      </div>
    </div>
  );
}
