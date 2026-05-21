export function SkeletonPlayer() {
  return (
    <div className="fixed inset-0 bg-black z-50">
      {/* Video container skeleton */}
      <div className="absolute inset-0 skeleton-image bg-[#0a0a0a]" />

      {/* Top bar skeleton */}
      <div
        className="absolute top-0 left-0 right-0 px-16 pt-4 pb-16"
        style={{
          background: "linear-gradient(to bottom, rgba(0, 0, 0, 0.8) 0%, rgba(0, 0, 0, 0) 100%)"
        }}
      >
        <div className="flex items-center gap-4">
          <div className="skeleton-rounded w-5 h-5 rounded" />
          <div className="skeleton-text h-4 w-16 rounded" />
          <div className="skeleton-text h-5 w-32 rounded ml-auto" />
        </div>
      </div>

      {/* Central controls skeleton */}
      <div className="absolute inset-0 flex items-center justify-center gap-12">
        {/* Back 10s skeleton */}
        <div className="w-[70px] h-[70px] rounded-full skeleton" />

        {/* Play button skeleton - larger with accent */}
        <div className="w-[110px] h-[110px] rounded-full skeleton-accent" />

        {/* Forward 10s skeleton */}
        <div className="w-[70px] h-[70px] rounded-full skeleton" />
      </div>

      {/* Bottom controls skeleton */}
      <div
        className="absolute bottom-0 left-0 right-0 px-16 pb-4"
        style={{
          background: "linear-gradient(to top, rgba(0, 0, 0, 0.8) 0%, rgba(0, 0, 0, 0) 100%)"
        }}
      >
        {/* Seek bar skeleton */}
        <div className="mb-4">
          <div className="h-1 bg-white/20 rounded-full relative overflow-hidden">
            <div className="h-full w-1/3 skeleton-accent rounded-full" />
          </div>
        </div>

        {/* Controls row skeleton */}
        <div className="flex items-center justify-between">
          {/* Left controls */}
          <div className="flex items-center gap-4">
            <div className="skeleton-rounded w-8 h-8 rounded-full" />
            <div className="skeleton-rounded w-7 h-7 rounded-full" />
            <div className="skeleton-text h-3 w-28 rounded" />
          </div>

          {/* Right controls */}
          <div className="flex items-center gap-2">
            <div className="skeleton-rounded w-7 h-7 rounded-full" />
            <div className="skeleton-rounded w-7 h-7 rounded-full" />
            <div className="skeleton-rounded w-7 h-7 rounded-full" />
          </div>
        </div>
      </div>

      {/* Buffering spinner skeleton (centered) */}
      <div className="absolute inset-0 flex items-center justify-center">
        <div className="w-16 h-16 rounded-full skeleton-accent animate-pulse" />
      </div>
    </div>
  );
}
