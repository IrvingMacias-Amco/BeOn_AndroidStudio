import { motion } from "motion/react";
import Container from "@/imports/Container/Container";

export type MobileNavTab = "inicio" | "canales" | "buscar" | "perfil";

interface MobileBottomNavProps {
  activeTab?: MobileNavTab;
  onTabChange?: (tab: MobileNavTab) => void;
}

export function MobileBottomNav({ activeTab = "inicio", onTabChange }: MobileBottomNavProps) {
  return (
    <div
      className="shrink-0 px-4"
      style={{
        backgroundColor: "rgba(0,0,0,0.98)",
        backdropFilter: "blur(20px)",
        paddingTop: 8,
        paddingBottom: "calc(env(safe-area-inset-bottom, 0px) + 8px)",
        minHeight: 67,
      }}
    >
      <div className="w-full max-w-md mx-auto" style={{ height: 51 }}>
        <Container />
      </div>
    </div>
  );
}
