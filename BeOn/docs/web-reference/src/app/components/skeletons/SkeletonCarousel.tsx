import { SkeletonPortraitCard, SkeletonTop10Card, SkeletonContinueWatchingCard, SkeletonLiveChannelCard } from "./SkeletonCard";

interface SkeletonCarouselProps {
  type?: "portrait" | "top10" | "continue" | "live";
  count?: number;
}

export function SkeletonCarousel({ type = "portrait", count = 6 }: SkeletonCarouselProps) {
  const renderCard = () => {
    switch (type) {
      case "top10":
        return <SkeletonTop10Card />;
      case "continue":
        return <SkeletonContinueWatchingCard />;
      case "live":
        return <SkeletonLiveChannelCard />;
      default:
        return <SkeletonPortraitCard />;
    }
  };

  return (
    <div className="px-16">
      {/* Title Skeleton */}
      <div className="mb-6">
        <div
          className="skeleton-text h-[28px] rounded"
          style={{ width: "250px" }}
        />
      </div>

      {/* Cards Container */}
      <div className="flex gap-4 overflow-hidden py-6">
        {Array.from({ length: count }).map((_, index) => (
          <div key={index}>
            {renderCard()}
          </div>
        ))}
      </div>
    </div>
  );
}

// Skeleton for multiple carousels
export function SkeletonCarouselGrid() {
  return (
    <div className="space-y-12 py-12">
      <SkeletonCarousel type="portrait" count={7} />
      <SkeletonCarousel type="top10" count={5} />
      <SkeletonCarousel type="portrait" count={7} />
      <SkeletonCarousel type="continue" count={5} />
      <SkeletonCarousel type="live" count={6} />
      <SkeletonCarousel type="portrait" count={7} />
    </div>
  );
}
