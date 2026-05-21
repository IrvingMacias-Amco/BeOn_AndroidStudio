// Portrait Card Skeleton (2:3 aspect ratio - 192x288px)
export function SkeletonPortraitCard() {
  return (
    <div className="w-[192px] shrink-0">
      <div
        className="w-full h-[288px] rounded-[16px] skeleton-card"
        style={{
          border: "1px solid rgba(255, 255, 255, 0.05)"
        }}
      />
    </div>
  );
}

// Top 10 Card Skeleton
export function SkeletonTop10Card() {
  return (
    <div className="flex items-center shrink-0" style={{ width: "308px", height: "302px" }}>
      {/* Large ranking number */}
      <div className="w-[128px] h-full flex items-center">
        <div
          className="skeleton"
          style={{
            width: "120px",
            height: "256px",
            borderRadius: "24px"
          }}
        />
      </div>

      {/* Card */}
      <div
        className="w-[180px] h-[270px] rounded-[16px] skeleton-card ml-[-128px]"
        style={{
          border: "1px solid rgba(255, 255, 255, 0.05)",
          zIndex: 1
        }}
      />
    </div>
  );
}

// Continue Watching Card Skeleton (16:9 aspect ratio - 320x188px)
export function SkeletonContinueWatchingCard() {
  return (
    <div className="w-[320px] shrink-0">
      <div
        className="w-full h-[188px] rounded-[16px] skeleton-card relative overflow-hidden"
        style={{
          border: "1px solid rgba(255, 255, 255, 0.05)"
        }}
      >
        {/* Progress bar skeleton */}
        <div className="absolute bottom-4 left-4 right-4 space-y-2">
          <div className="h-1 w-full rounded-full skeleton" />
          <div className="h-3 w-32 rounded skeleton-text" />
        </div>
      </div>
    </div>
  );
}

// Live Channel Card Skeleton (2:3 aspect ratio with badge)
export function SkeletonLiveChannelCard() {
  return (
    <div className="w-[192px] shrink-0">
      <div
        className="w-full h-[288px] rounded-[16px] skeleton-card relative"
        style={{
          border: "1px solid rgba(255, 255, 255, 0.05)"
        }}
      >
        {/* EN VIVO badge skeleton */}
        <div
          className="absolute top-3 right-3 px-3 py-1.5 rounded skeleton"
          style={{
            width: "70px",
            height: "24px",
            backgroundColor: "rgba(239, 68, 68, 0.3)"
          }}
        />
      </div>
    </div>
  );
}
