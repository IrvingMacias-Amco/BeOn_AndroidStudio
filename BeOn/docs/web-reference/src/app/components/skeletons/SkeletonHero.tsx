export function SkeletonHero() {
  return (
    <div className="relative w-full h-[675px] overflow-hidden bg-black">
      {/* Background skeleton */}
      <div className="absolute inset-0 skeleton-image bg-[#121212]" />

      {/* Gradient overlay */}
      <div
        className="absolute inset-0"
        style={{
          background: "linear-gradient(89.66deg, rgba(0, 0, 0, 0.9) 29.07%, rgba(0, 0, 0, 0.6) 98.69%)"
        }}
      />

      {/* Content skeleton */}
      <div className="absolute inset-0 flex items-end pb-24 px-16">
        <div className="max-w-2xl space-y-6">
          {/* Title skeleton */}
          <div className="space-y-3">
            <div className="skeleton-text h-[60px] w-[450px] rounded-lg" />
            <div className="skeleton-text h-[60px] w-[350px] rounded-lg" />
          </div>

          {/* Metadata skeleton */}
          <div className="flex items-center gap-4">
            <div className="skeleton-text h-4 w-16 rounded" />
            <div className="skeleton-text h-4 w-4 rounded-full" />
            <div className="skeleton-text h-4 w-24 rounded" />
            <div className="skeleton-text h-4 w-4 rounded-full" />
            <div className="skeleton-text h-4 w-12 rounded" />
            <div className="skeleton-text h-4 w-4 rounded-full" />
            <div className="skeleton-text h-6 w-12 rounded" />
          </div>

          {/* Ranking badge skeleton */}
          <div className="flex items-center gap-3">
            <div className="w-9 h-9 rounded-full skeleton" />
            <div className="skeleton-text h-4 w-48 rounded" />
          </div>

          {/* Genre pills skeleton */}
          <div className="flex gap-2">
            <div className="skeleton h-8 w-20 rounded-full" />
            <div className="skeleton h-8 w-24 rounded-full" />
            <div className="skeleton h-8 w-20 rounded-full" />
          </div>

          {/* Description skeleton */}
          <div className="space-y-2 pt-2">
            <div className="skeleton-text h-4 w-full rounded" />
            <div className="skeleton-text h-4 w-full rounded" />
            <div className="skeleton-text h-4 w-3/4 rounded" />
          </div>

          {/* Buttons skeleton */}
          <div className="flex items-center gap-4 pt-4">
            <div className="skeleton-button h-14 w-44 rounded-full" />
            <div className="skeleton-rounded h-14 w-14 rounded-full" />
          </div>
        </div>
      </div>

      {/* Dot indicators skeleton */}
      <div className="absolute bottom-8 left-1/2 -translate-x-1/2 flex gap-2">
        {[1, 2, 3, 4, 5].map((i) => (
          <div
            key={i}
            className="w-2 h-2 rounded-full skeleton"
            style={{ opacity: i === 1 ? 1 : 0.3 }}
          />
        ))}
      </div>
    </div>
  );
}
