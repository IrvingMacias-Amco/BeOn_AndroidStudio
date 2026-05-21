import { SkeletonNav } from "./SkeletonNav";
import { SkeletonCarousel } from "./SkeletonCarousel";

export function SkeletonContentDetail() {
  return (
    <div className="min-h-screen bg-black">
      {/* Navigation */}
      <SkeletonNav />

      {/* Hero Section */}
      <div className="relative pt-[72px]">
        <div className="relative w-full h-[675px] overflow-hidden">
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
              {/* Back button skeleton */}
              <div className="w-12 h-12 rounded-full skeleton mb-6" />

              {/* Title skeleton */}
              <div className="skeleton-text h-[64px] w-[400px] rounded-lg" />

              {/* Metadata skeleton */}
              <div className="flex items-center gap-4">
                <div className="skeleton-text h-4 w-12 rounded" />
                <div className="skeleton-text h-4 w-4 rounded-full" />
                <div className="skeleton-text h-4 w-24 rounded" />
                <div className="skeleton-text h-4 w-4 rounded-full" />
                <div className="skeleton-text h-4 w-16 rounded" />
                <div className="skeleton-text h-4 w-4 rounded-full" />
                <div className="skeleton-text h-6 w-12 rounded" />
              </div>

              {/* Ranking badge skeleton */}
              <div className="flex items-center gap-3">
                <div className="w-9 h-9 rounded-full skeleton" />
                <div className="skeleton-text h-4 w-56 rounded" />
              </div>

              {/* Genre pills skeleton */}
              <div className="flex gap-2">
                <div className="skeleton h-8 w-20 rounded-full" />
                <div className="skeleton h-8 w-24 rounded-full" />
                <div className="skeleton h-8 w-20 rounded-full" />
              </div>

              {/* Buttons skeleton */}
              <div className="flex items-center gap-4 pt-4">
                <div className="skeleton-button h-14 w-48 rounded-full" />
                <div className="skeleton-rounded h-14 w-14 rounded-full" />
              </div>
            </div>
          </div>
        </div>
      </div>

      {/* Content Details Section */}
      <div className="bg-black px-16 py-16">
        <div className="max-w-7xl">
          {/* Synopsis skeleton */}
          <div className="mb-12 space-y-3">
            <div className="skeleton-text h-5 w-full rounded" />
            <div className="skeleton-text h-5 w-full rounded" />
            <div className="skeleton-text h-5 w-3/4 rounded" />
          </div>

          {/* Details Grid skeleton */}
          <div className="grid grid-cols-2 gap-x-24 gap-y-12">
            {/* Left Column */}
            <div className="space-y-6">
              {[1, 2, 3, 4, 5].map((i) => (
                <div key={i} className="space-y-2">
                  <div className="skeleton-text h-3 w-24 rounded" />
                  <div className="skeleton-text h-4 w-full rounded" />
                </div>
              ))}
            </div>

            {/* Right Column */}
            <div className="space-y-6">
              {[1, 2, 3].map((i) => (
                <div key={i} className="space-y-2">
                  <div className="skeleton-text h-3 w-24 rounded" />
                  <div className="skeleton-text h-4 w-full rounded" />
                </div>
              ))}
            </div>
          </div>
        </div>
      </div>

      {/* Similar Content */}
      <div className="bg-black pb-16">
        <div className="border-t border-[rgba(0,201,80,0.1)] pt-12">
          <SkeletonCarousel type="portrait" count={6} />
        </div>
      </div>
    </div>
  );
}
