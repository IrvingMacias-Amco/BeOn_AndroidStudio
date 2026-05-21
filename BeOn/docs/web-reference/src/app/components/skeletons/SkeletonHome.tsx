import { SkeletonNav } from "./SkeletonNav";
import { SkeletonHero } from "./SkeletonHero";
import { SkeletonCarouselGrid } from "./SkeletonCarousel";

export function SkeletonHome() {
  return (
    <div className="min-h-screen bg-black">
      {/* Navigation */}
      <SkeletonNav />

      {/* Hero */}
      <div className="pt-[72px]">
        <SkeletonHero />
      </div>

      {/* Carousels */}
      <SkeletonCarouselGrid />

      {/* Footer spacing */}
      <div className="h-24" />
    </div>
  );
}
