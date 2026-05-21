import { motion } from "motion/react";
import { ChevronLeft, ChevronRight } from "lucide-react";
import { useState, useRef, useEffect } from "react";

interface ContentItem {
  id: string;
  title: string;
  thumbnail: string;
  year?: string;
}

interface HorizontalCarouselProps {
  title: string;
  items: ContentItem[];
  onItemClick?: (id: string) => void;
}

export function HorizontalCarousel({ title, items, onItemClick }: HorizontalCarouselProps) {
  const [showLeftArrow, setShowLeftArrow] = useState(false);
  const [showRightArrow, setShowRightArrow] = useState(true);
  const containerRef = useRef<HTMLDivElement>(null);

  const handleScroll = (direction: "left" | "right") => {
    if (!containerRef.current) return;

    const scrollAmount = 1000;
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
    <div className="relative group px-16">
      {/* Section title */}
      <h2
        className="text-white mb-6"
        style={{
          fontSize: "30px",
          fontWeight: "700",
          lineHeight: "28px",
          letterSpacing: "-0.75px"
        }}
      >
        {title}
      </h2>

      {/* Carousel container */}
      <div className="relative">
        {/* Left arrow */}
        {showLeftArrow && (
          <motion.button
            className="absolute left-0 top-1/2 -translate-y-1/2 w-12 h-12 rounded-full flex items-center justify-center z-20 opacity-0 group-hover:opacity-100 transition-opacity"
            style={{
              backgroundColor: "rgba(0, 0, 0, 0.7)",
              backdropFilter: "blur(10px)"
            }}
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
            className="absolute right-0 top-1/2 -translate-y-1/2 w-12 h-12 rounded-full flex items-center justify-center z-20 opacity-0 group-hover:opacity-100 transition-opacity"
            style={{
              backgroundColor: "rgba(0, 0, 0, 0.7)",
              backdropFilter: "blur(10px)"
            }}
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
          className="flex gap-4 overflow-x-auto scrollbar-hide py-6"
          style={{
            scrollbarWidth: "none",
            msOverflowStyle: "none"
          }}
        >
          {items.map((item) => (
            <motion.div
              key={item.id}
              className="flex-shrink-0 rounded-xl overflow-hidden cursor-pointer"
              style={{
                width: "320px",
                height: "180px",
                backgroundColor: "rgba(255,255,255,0)",
                boxShadow: "0px 10px 15px -3px rgba(0,0,0,0.1), 0px 4px 6px -4px rgba(0,0,0,0.1)"
              }}
              whileHover={{ scale: 1.05 }}
              whileTap={{ scale: 0.98 }}
              onClick={() => onItemClick?.(item.id)}
            >
              <img
                src={item.thumbnail}
                alt={item.title}
                className="w-full h-full object-cover"
              />
            </motion.div>
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
