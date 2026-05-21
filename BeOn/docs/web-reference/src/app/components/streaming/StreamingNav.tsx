import { motion } from "motion/react";
import { useState } from "react";
import beonLogo from "@/imports/beon_logo_prev.png";
import svgPaths from "@/imports/TopButtons/svg-tw92pt1r7d";

interface NavItem {
  label: string;
  active?: boolean;
}

export function StreamingNav() {
  const [activeItem, setActiveItem] = useState("Inicio");

  const navItems: NavItem[] = [
    { label: "Inicio", active: true },
    { label: "GuÃ­a de TV" },
    { label: "PelÃ­culas" },
    { label: "Series" },
    { label: "Deportes" },
    { label: "MÃºsica" },
    { label: "Noticias" },
    { label: "NiÃ±os" },
    { label: "Suscripciones" }
  ];

  return (
    <motion.header
      className="fixed top-0 left-0 right-0 z-[100]"
      style={{
        backgroundColor: "rgba(0, 0, 0, 0.8)",
        borderBottom: "1px solid var(--border-subtle)"
      }}
      initial={{ y: -100 }}
      animate={{ y: 0 }}
      transition={{ duration: 0.5, ease: [0.4, 0, 0.2, 1] }}
    >
      <div className="flex items-center px-12 h-[72px]">
        {/* Logo */}
        <motion.div whileHover={{ scale: 1.05 }} className="shrink-0">
          <img
            src={beonLogo}
            alt="BEOn"
            className="h-[36px] w-auto object-contain"
          />
        </motion.div>

        {/* Navigation menu â€” centered, fills remaining space */}
        <nav className="flex-1 flex items-center justify-center gap-8">
          {navItems.map((item) => {
            const isActive = item.label === activeItem;
            return (
              <motion.button
                key={item.label}
                className="relative text-lg font-medium transition-colors"
                style={{
                  color: isActive ? "var(--text-primary)" : "var(--text-muted)"
                }}
                onClick={() => setActiveItem(item.label)}
                whileHover={{
                  color: "var(--text-primary)"
                }}
              >
                {item.label}
                {isActive && (
                  <motion.div
                    className="absolute bottom-[-22px] left-0 h-0.5 rounded-full"
                    style={{
                      backgroundColor: "var(--text-primary)",
                      width: "100%"
                    }}
                    layoutId="activeIndicator"
                    transition={{
                      type: "spring",
                      stiffness: 380,
                      damping: 30
                    }}
                  />
                )}
              </motion.button>
            );
          })}
        </nav>

        {/* Right actions */}
        <div className="shrink-0 flex items-center gap-4">
          {/* Search */}
          <motion.button
            className="w-10 h-10 rounded-full flex items-center justify-center"
            whileHover={{ scale: 1.1 }}
            whileTap={{ scale: 0.95 }}
          >
            <svg width="24" height="24" viewBox="0 0 24 24" fill="none">
              <path d={svgPaths.pedb3a30} stroke="var(--text-muted)" strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" />
              <path d="M5.3 5.3L1 1" stroke="var(--text-muted)" strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" transform="translate(14.5 14.5)" />
            </svg>
          </motion.button>

          {/* Smiley */}
          <motion.button
            className="w-10 h-10 rounded-full flex items-center justify-center"
            whileHover={{ scale: 1.1 }}
            whileTap={{ scale: 0.95 }}
          >
            <svg width="24" height="24" viewBox="0 0 24 24" fill="none">
              <path d={svgPaths.pb60700} stroke="var(--text-muted)" strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" />
              <path d={svgPaths.p18fa300} stroke="var(--text-muted)" strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" transform="translate(7 12)" />
              <circle cx="9" cy="9" r="1" fill="var(--text-muted)" />
              <circle cx="15" cy="9" r="1" fill="var(--text-muted)" />
            </svg>
          </motion.button>

          {/* Settings / Gear */}
          <motion.button
            className="w-10 h-10 rounded-full flex items-center justify-center"
            whileHover={{ scale: 1.1 }}
            whileTap={{ scale: 0.95 }}
          >
            <svg width="24" height="24" viewBox="0 0 24 24" fill="none">
              <path d={svgPaths.p2eb1aa00} stroke="var(--text-muted)" strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" transform="translate(2 1)" />
              <path d={svgPaths.p1e531d00} stroke="var(--text-muted)" strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" transform="translate(8 8)" />
            </svg>
          </motion.button>

          {/* User (green bg) */}
          <motion.button
            className="w-10 h-10 rounded-full flex items-center justify-center"
            style={{
              backgroundColor: "rgba(0, 201, 80, 0.25)",
              boxShadow: "var(--shadow-glow-green)"
            }}
            whileHover={{
              scale: 1.1,
              backgroundColor: "rgba(0, 201, 80, 0.35)"
            }}
            whileTap={{ scale: 0.95 }}
          >
            <svg width="24" height="24" viewBox="0 0 24 24" fill="none">
              <path d={svgPaths.p67f12c8} stroke="white" strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" />
              <path d={svgPaths.p2c19cb00} stroke="white" strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" />
            </svg>
          </motion.button>
        </div>
      </div>
    </motion.header>
  );
}
