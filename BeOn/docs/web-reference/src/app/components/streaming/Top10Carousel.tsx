import { motion } from "motion/react";
import { ChevronLeft, ChevronRight } from "lucide-react";
import { useState, useRef, useEffect } from "react";
import { Top10Card } from "./Top10Card";

interface Top10Item {
  id: string;
  title: string;
  thumbnail: string;
  ranking: number;
  year?: string;
  rating?: string;
}

interface Top10CarouselProps {
  title: string;
  items: Top10Item[];
  onItemClick?: (id: string) => void;
}

export function Top10Carousel({ title, items, onItemClick }: Top10CarouselProps) {
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
        className="text-white mb-8"
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

        {/* Scrollable content with larger spacing for ranking numbers */}
        <div
          ref={containerRef}
          className="flex gap-6 overflow-x-auto scrollbar-hide py-6"
          style={{
            scrollbarWidth: "none",
            msOverflowStyle: "none"
          }}
        >
          {items.slice(0, 10).map((item) => (
            <div key={item.id} className="flex-shrink-0">
              <Top10Card
                title={item.title}
                thumbnail={item.thumbnail}
                ranking={item.ranking}
                year={item.year}
                rating={item.rating}
                onClick={() => onItemClick?.(item.id)}
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
