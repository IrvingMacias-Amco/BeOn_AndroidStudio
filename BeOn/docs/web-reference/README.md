# Premium Streaming Platform Design System

A complete, production-ready OTT streaming platform built with React, Tailwind CSS, and Motion (Framer Motion).

## ðŸŽ¬ Features

### Comprehensive Design Token System
- **Motion tokens**: Durations, easing curves, transitions, animation timing
- **Color tokens**: Surfaces, accents, text colors, borders
- **Cinematic effects**: Gradients, shadows, glows
- **Typography scale**: Complete type system with responsive sizing
- **Spacing scale**: Consistent spacing throughout
- **Streaming-specific tokens**: Progress bars, live indicators, player chrome

### Interactive Components

#### ðŸŽ­ HeroSection
Cinematic hero banner with:
- Full-width background with gradient overlays
- Animated title and metadata
- Ranking badge with pulsing animation
- Genre pills
- Primary CTA with gradient and glow effect
- Navigation arrows

#### ðŸŽ´ StreamingCard
Interactive content cards with:
- Hover states with scale and glow effects
- Progress indicators
- Live badges with pulse animation
- "NEW" badges
- Metadata overlays
- Action buttons (Play, Add, Info)
- Portrait (2:3) or landscape (16:9) layouts

#### âš¡ SkeletonCard
Loading states with:
- Shimmer animation (1500ms cycle)
- Realistic content placeholders
- Matching card dimensions

#### ðŸŽ  ContentCarousel
Scrollable content rows with:
- Smooth horizontal scrolling
- Navigation arrows (auto show/hide)
- Multiple content types
- Responsive card sizing

#### ðŸ§­ StreamingNav
Fixed navigation with:
- Logo and branding
- Horizontal menu with active state indicator
- Search, notifications, user buttons
- Animated underline for active page
- Notification pulse animation

#### ðŸŽ® MiniPlayer
Bottom player bar with:
- Now playing information
- Playback controls
- Volume control
- Progress scrubbing
- Expand to fullscreen
- Spring animations

### Interaction States

All components support:
- **Default**: Base appearance
- **Hover**: Enhanced visual feedback
- **Active/Pressed**: Touch feedback
- **Focus**: Keyboard navigation
- **Disabled**: Reduced opacity
- **Loading**: Skeleton placeholders

## ðŸš€ Getting Started

```bash
# Install dependencies
pnpm install

# Start development server
pnpm dev
```

## ðŸ“ Project Structure

```
src/
â”œâ”€â”€ styles/
â”‚   â”œâ”€â”€ tokens.css      # Complete design token system
â”‚   â”œâ”€â”€ theme.css       # Tailwind theme configuration
â”‚   â””â”€â”€ fonts.css       # Font imports
â”œâ”€â”€ app/
â”‚   â”œâ”€â”€ App.tsx         # Main application
â”‚   â””â”€â”€ components/
â”‚       â””â”€â”€ streaming/
â”‚           â”œâ”€â”€ StreamingNav.tsx
â”‚           â”œâ”€â”€ HeroSection.tsx
â”‚           â”œâ”€â”€ StreamingCard.tsx
â”‚           â”œâ”€â”€ SkeletonCard.tsx
â”‚           â”œâ”€â”€ ContentCarousel.tsx
â”‚           â”œâ”€â”€ MiniPlayer.tsx
â”‚           â””â”€â”€ index.ts
```

## ðŸŽ¨ Design System

See [DESIGN_SYSTEM.md](./DESIGN_SYSTEM.md) for complete documentation on:
- Design tokens reference
- Component API
- Motion system
- Responsive design
- Accessibility guidelines
- Usage examples
- Performance best practices

## ðŸŽ¯ Token System

### Using Design Tokens

```tsx
// Motion
transition: var(--transition-fast);

// Colors
backgroundColor: var(--surface-card);
color: var(--text-primary);

// Effects
boxShadow: var(--shadow-glow-green);

// Spacing
padding: var(--space-4);
gap: var(--card-gap);
```

### Key Token Categories

- **Motion**: Duration, easing, transitions
- **Colors**: Surfaces, text, accents, borders
- **Effects**: Gradients, shadows, glows
- **Spacing**: Scale from 4px to 96px
- **Typography**: Sizes, weights, line heights
- **Radius**: Border radius scale

## ðŸŽ¬ Component Usage

### StreamingCard

```tsx
<StreamingCard
  title="Movie Title"
  thumbnail="/path/to/image.jpg"
  type="landscape"
  year="2025"
  duration="2h 15min"
  rating="PG-13"
  progress={65}
  isLive={false}
  isNew={true}
/>
```

### ContentCarousel

```tsx
<ContentCarousel
  title="Continue Watching"
  items={contentItems}
  type="landscape"
  loading={false}
/>
```

### HeroSection

```tsx
<HeroSection
  title="Featured Title"
  description="Description..."
  backgroundImage="/path/to/bg.jpg"
  year="2025"
  duration="2h 29min"
  rating="PG-13"
  imdbRating="7.5"
  genres={["Action", "Drama"]}
  ranking={{ position: 5, category: "Action Movies" }}
/>
```

## ðŸŽ­ Motion System

Built with Motion (Framer Motion) for:
- Smooth, spring-based animations
- Hover and tap interactions
- Layout animations
- Entrance/exit animations
- Gesture support

### Animation Principles

- **Fast feedback** (150-250ms): UI interactions
- **Normal transitions** (250-350ms): State changes
- **Slow animations** (500-700ms): Carousels, layout shifts
- **Cinematic effects** (1000-1500ms): Hero transitions, shimmers

## ðŸŽ¨ Visual Design

### Color Palette

- **Primary Surface**: Deep space blue-black (#030213)
- **Accent**: Vibrant green (#22c55e)
- **Gradient**: Teal to lime (for CTAs)
- **Text**: White, gray scale for hierarchy

### Typography

- **Font**: Inter (Google Fonts)
- **Weights**: 400, 500, 600, 700, 900
- **Scale**: 12px â†’ 64px

## ðŸ“± Responsive Design

- Mobile-first approach
- Breakpoints: 768px, 1024px, 1440px
- Adaptive spacing and sizing
- Touch-optimized interactions

## â™¿ Accessibility

- Keyboard navigation support
- Focus states with visible rings
- WCAG AA color contrast
- Semantic HTML structure
- ARIA labels where needed

## ðŸ”§ Technologies

- **React 18.3**: UI framework
- **Motion 12**: Animation library
- **Tailwind CSS 4**: Utility-first CSS
- **Lucide React**: Icon library
- **TypeScript**: Type safety
- **Vite**: Build tool

## ðŸ“¦ Dependencies

Key packages:
- `motion` - Animation library
- `lucide-react` - Icons
- `tailwindcss` - Styling
- `@radix-ui/*` - UI primitives (available for extension)

## ðŸŽ¯ Performance

- Optimized animations using transforms and opacity
- Layout shift prevention
- Lazy loading ready
- Efficient re-renders with React optimization
- GPU-accelerated animations

## ðŸš€ Deployment

Built for production with:
- Optimized bundle size
- Tree-shaking
- Code splitting ready
- Fast TTI (Time to Interactive)

## ðŸ“„ License

This is a demonstration project for Figma Make.

## ðŸŽ‰ Credits

- Design inspired by premium streaming platforms
- Images from Unsplash
- Built with Figma Make

---

**Built with â¤ï¸ using Claude Code and Figma Make**
