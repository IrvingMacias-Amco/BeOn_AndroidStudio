import { motion, useMotionValue, useTransform } from "motion/react";
import { ChevronLeft, ChevronRight } from "lucide-react";
import { useState, useRef, useEffect } from "react";
import { StreamingCard } from "./StreamingCard";
import { SkeletonCard } from "./SkeletonCard";

interface ContentItem {
  id: string;
  title: string;
  thumbnail: string;
  year?: string;
  duration?: string;
  rating?: string;
  progress?: number;
  isLive?: boolean;
  isNew?: boolean;
}

interface ContentCarouselProps {
  title: string;
  items: ContentItem[];
  type?: "portrait" | "landscape";
  loading?: boolean;
}

export function ContentCarousel({
  title,
  items,
  type = "landscape",
  loading = false
}: ContentCarouselProps) {
  const [showLeftArrow, setShowLeftArrow] = useState(false);
  const [showRightArrow, setShowRightArrow] = useState(true);
  const containerRef = useRef<HTMLDivElement>(null);
  const x = useMotionValue(0);

  const handleScroll = (direction: "left" | "right") => {
    if (!containerRef.current) return;

    const cardWidth = type === "portrait" ? 200 : 280;
    const scrollAmount = cardWidth * 4;
    const currentScroll = containerRef.current.scrollLeft;
    const targetScroll =
      direction === "left"
        ? currentScroll - scrollAmount
        : currentScroll + scrollAmount;

    containerRef.current.scrollTo({
      left: targetScroll,
      behavior: "smooth"
    });
  };

  useEffect(() => {
    const container = containerRef.current;
    if (!container) return;

    const updateArrows = () => {
      const { scrollLeft, scrollWidth, clientWidth } = container;
      setShowLeftArrow(scrollLeft > 0);
      setShowRightArrow(scrollLeft < scrollWidth - clientWidth - 10);
    };

    container.addEventListener("scroll", updateArrows);
    updateArrows();

    return () => container.removeEventListener("scroll", updateArrows);
  }, [items]);

  return (
    <div className="relative group">
      {/* Section title */}
      <h2
        className="text-white mb-4 px-12"
        style={{
          fontSize: "var(--text-xl)",
          fontWeight: "var(--font-bold)"
        }}
      >
        {title}
      </h2>

      {/* Carousel container */}
      <div className="relative">
        {/* Left arrow */}
        {showLeftArrow && (
          <motion.button
            className="absolute left-2 top-1/2 -translate-y-1/2 w-12 h-12 rounded-full flex items-center justify-center z-20"
            style={{
              backgroundColor: "rgba(0, 0, 0, 0.7)",
              backdropFilter: "blur(10px)"
            }}
            initial={{ opacity: 0 }}
            animate={{ opacity: 1 }}
            exit={{ opacity: 0 }}
            whileHover={{
              scale: 1.1,
              backgroundColor: "rgba(0, 0, 0, 0.9)"
            }}
            whileTap={{ scale: 0.95 }}
            onClick={() => handleScroll("left")}
          >
            <ChevronLeft className="w-8 h-8 text-white" />
          </motion.button>
        )}

        {/* Right arrow */}
        {showRightArrow && (
          <motion.button
            className="absolute right-2 top-1/2 -translate-y-1/2 w-12 h-12 rounded-full flex items-center justify-center z-20"
            style={{
              backgroundColor: "rgba(0, 0, 0, 0.7)",
              backdropFilter: "blur(10px)"
            }}
            initial={{ opacity: 0 }}
            animate={{ opacity: 1 }}
            exit={{ opacity: 0 }}
            whileHover={{
              scale: 1.1,
              backgroundColor: "rgba(0, 0, 0, 0.9)"
            }}
            whileTap={{ scale: 0.95 }}
            onClick={() => handleScroll("right")}
          >
            <ChevronRight className="w-8 h-8 text-white" />
          </motion.button>
        )}

        {/* Scrollable content */}
        <div
          ref={containerRef}
          className="flex gap-4 overflow-x-auto scrollbar-hide px-12 py-2"
          style={{
            scrollbarWidth: "none",
            msOverflowStyle: "none"
          }}
        >
          {loading
            ? Array.from({ length: 6 }).map((_, i) => (
                <div
                  key={i}
                  className="flex-shrink-0"
                  style={{
                    width: type === "portrait" ? "200px" : "280px"
                  }}
                >
                  <SkeletonCard type={type} />
                </div>
              ))
            : items.map((item) => (
                <div
                  key={item.id}
                  className="flex-shrink-0"
                  style={{
                    width: type === "portrait" ? "200px" : "280px"
                  }}
                >
                  <StreamingCard
                    title={item.title}
                    thumbnail={item.thumbnail}
                    type={type}
                    year={item.year}
                    duration={item.duration}
                    rating={item.rating}
                    progress={item.progress}
                    isLive={item.isLive}
                    isNew={item.isNew}
                  />
                </div>
              ))}
        </div>
      </div>

      {/* CSS to hide scrollbar */}
      <style>{`
        .scrollbar-hide::-webkit-scrollbar {
          display: none;
        }
      `}</style>
    </div>
  );
}
