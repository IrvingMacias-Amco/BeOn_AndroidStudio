export function SkeletonNav() {
  return (
    <div
      className="fixed top-0 left-0 right-0 h-[72px] z-50 px-16"
      style={{
        backgroundColor: "rgba(0, 0, 0, 0.95)",
        backdropFilter: "blur(20px)",
        borderBottom: "1px solid rgba(255, 255, 255, 0.05)"
      }}
    >
      <div className="flex items-center justify-between h-full">
        {/* Logo skeleton */}
        <div className="flex items-center gap-8">
          <div className="skeleton h-8 w-32 rounded" />

          {/* Nav links skeleton */}
          <div className="flex items-center gap-6">
            <div className="skeleton-text h-4 w-16 rounded" />
            <div className="skeleton-text h-4 w-20 rounded" />
            <div className="skeleton-text h-4 w-16 rounded" />
            <div className="skeleton-text h-4 w-24 rounded" />
          </div>
        </div>

        {/* Right side skeleton */}
        <div className="flex items-center gap-4">
          <div className="skeleton-text h-4 w-20 rounded" />
          <div className="skeleton-rounded w-10 h-10 rounded-full" />
        </div>
      </div>
    </div>
  );
}
