# Streaming Platform Design System

## Overview

This is a complete production-ready design system for an OTT/Cinema streaming platform, featuring comprehensive design tokens, interactive states, and reusable components.

## Design Tokens

All design tokens are defined in `/src/styles/tokens.css` and organized into the following categories:

### Motion Tokens

- **Duration**: `instant` (0ms) â†’ `cinematic` (1500ms)
- **Easing**: Linear, ease-in, ease-out, spring, smooth, cinematic
- **Transitions**: Fast, normal, slow, cinematic presets
- **Animation Timing**: Autoplay delays, overlay appearance, skeleton shimmer, carousel transitions

### Color Tokens

#### Surface Colors
- `--surface-primary`: Main background (#030213)
- `--surface-elevated`: Elevated surfaces with transparency
- `--surface-overlay`: Modal/overlay backgrounds
- `--surface-card`: Card backgrounds
- `--surface-card-hover`: Card hover states

#### Accent Colors
- `--accent-primary`: Primary brand color (#22c55e)
- `--accent-gradient-start/end`: Button gradient colors
- `--accent-glow`: Glow effects for interactive elements

#### Text Colors
- `--text-primary`: Primary text (white)
- `--text-secondary`: Secondary text (#d1d5dc)
- `--text-muted`: Muted text (#a0a0a0)
- `--text-accent`: Accent text (green)
- `--text-warning`: Warning text (yellow)

### Cinematic Effects

#### Gradients
- `--gradient-hero`: Hero section bottom gradient overlay
- `--gradient-overlay-top/bottom`: Top/bottom overlays
- `--gradient-card-hover`: Card hover gradient
- `--gradient-button-primary`: Primary button gradient
- `--gradient-skeleton`: Skeleton shimmer gradient

#### Shadows
- `--shadow-sm` through `--shadow-xl`: Standard shadow scale
- `--shadow-glow-green`: Green glow effect
- `--shadow-glow-strong`: Strong green glow for CTAs

### Spacing Scale

- `--space-1` (4px) through `--space-24` (96px)
- Component-specific spacing for cards, sections, carousels, navigation

### Typography Scale

- Font sizes from `--text-xs` (12px) to `--text-4xl` (64px)
- Font weights: normal (400), medium (500), semibold (600), bold (700), black (900)
- Line heights: tight (1.25), normal (1.5), relaxed (1.75)

### Radius Values

- `--radius-sm` (4px) through `--radius-2xl` (24px)
- `--radius-full` (9999px) for circular elements

## Component System

### StreamingCard

Interactive content card with hover effects, progress indicators, and metadata overlay.

**States:**
- Default: Standard appearance with subtle shadow
- Hover: Scale up (1.05), show glow, reveal action buttons
- Focus: Keyboard navigation support

**Features:**
- Portrait (2:3) or landscape (16:9) aspect ratios
- Progress bar for continue watching
- Live indicator with pulsing animation
- "NEW" badge for new releases
- Metadata overlay (title, year, rating, duration)
- Action buttons (Play, Add to list, More info)

### SkeletonCard

Loading placeholder with shimmer animation.

**Animation:**
- 1500ms shimmer cycle
- Gradient moves left to right
- Realistic content placeholders

### HeroSection

Cinematic hero banner for featured content.

**Features:**
- Full-width background with cinematic gradient overlay
- Large title with tracking-tight typography
- Metadata row (year, duration, IMDb rating, age rating)
- Ranking badge with animated icon
- Genre pills
- Expandable description
- Primary CTA with gradient and glow
- Secondary action buttons
- Navigation arrows with hover states

### StreamingNav

Fixed navigation header with active state indicators.

**Features:**
- Logo and branding
- Horizontal navigation menu
- Active page indicator (animated underline)
- Search, notifications, and user account buttons
- Notification badge with pulse animation
- Smooth hover transitions

### ContentCarousel

Scrollable content row with navigation arrows.

**Features:**
- Horizontal scroll with smooth snap behavior
- Left/right navigation arrows (show/hide based on scroll position)
- Configurable portrait or landscape cards
- Loading state with skeletons
- Section title
- Responsive card sizing

## Interaction States

### Button States

All interactive elements support:
- **Default**: Base appearance
- **Hover**: Scale 1.05-1.1, enhanced colors/borders
- **Active/Pressed**: Scale 0.95-0.98
- **Focus**: Glow ring for keyboard navigation
- **Disabled**: Reduced opacity (0.4)

### Card States

- **Default**: Subtle shadow, base colors
- **Hover**: Scale 1.05, glow effect, reveal overlay
- **Focus**: Keyboard focus ring
- **Loading**: Skeleton with shimmer animation

### Navigation States

- **Default**: Muted color
- **Hover**: White color
- **Active**: White color + animated underline indicator
- **Focus**: Keyboard focus visible

## Motion System

### Transitions

Use predefined transition variables:
```css
transition: all var(--transition-fast);    /* 150ms ease-out */
transition: all var(--transition-normal);  /* 250ms ease-in-out */
transition: all var(--transition-slow);    /* 500ms smooth */
transition: all var(--transition-cinematic); /* 1500ms cinematic */
```

### Animation Principles

1. **Fast feedback** (150-250ms): UI interactions, button hovers
2. **Normal transitions** (250-350ms): Card hovers, state changes
3. **Slow animations** (500-700ms): Carousels, major layout shifts
4. **Cinematic effects** (1000-1500ms): Hero transitions, skeleton shimmers

### Easing Functions

- **ease-out**: For enter animations
- **ease-in**: For exit animations
- **ease-in-out**: For bidirectional transitions
- **spring**: For playful, bouncy interactions
- **cinematic**: For dramatic, slow reveals

## Responsive Design

### Breakpoints

- Mobile: < 768px
- Tablet: 768px - 1024px
- Desktop: > 1024px
- Large Desktop: > 1440px

### Responsive Tokens

Use component spacing that adapts:
- `--card-gap`: 16px (scales with viewport)
- `--section-gap`: 48px (larger on desktop)
- `--carousel-gap`: 24px

## Accessibility

### Keyboard Navigation

- All interactive elements are keyboard accessible
- Focus states use visible rings with `--glow-focus`
- Logical tab order follows visual hierarchy

### Color Contrast

- Text on dark backgrounds meets WCAG AA standards
- Primary text: White on dark (#030213)
- Secondary text: #d1d5dc on dark
- Muted text: #a0a0a0 (still readable)

### Motion

- Respects `prefers-reduced-motion` where applicable
- Critical animations (loading states) have reduced alternatives

## Usage Examples

### Using Motion Tokens

```tsx
<motion.div
  whileHover={{
    scale: 1.05,
    transition: { duration: 0.25, ease: [0.4, 0, 0.2, 1] }
  }}
>
  Content
</motion.div>
```

### Using Color Tokens

```tsx
<div
  style={{
    backgroundColor: "var(--surface-card)",
    color: "var(--text-primary)",
    boxShadow: "var(--shadow-glow-green)"
  }}
>
  Content
</div>
```

### Using Spacing Tokens

```tsx
<div
  style={{
    padding: "var(--space-4)",
    gap: "var(--card-gap)",
    marginBottom: "var(--section-gap)"
  }}
>
  Content
</div>
```

## Performance

### Optimizations

1. **Motion values**: Use Motion's `useMotionValue` for performant animations
2. **Layout animations**: Use `layoutId` for shared element transitions
3. **Lazy loading**: Images load on demand
4. **Skeleton states**: Instant perceived performance

### Best Practices

- Keep animations under 500ms for most interactions
- Use `will-change` sparingly for complex animations
- Leverage GPU acceleration with `transform` and `opacity`
- Avoid animating `width`, `height`, or `left/top` directly

## File Structure

```
src/
â”œâ”€â”€ styles/
â”‚   â”œâ”€â”€ tokens.css           # All design tokens
â”‚   â”œâ”€â”€ theme.css           # Base theme config
â”‚   â””â”€â”€ fonts.css           # Font imports
â”œâ”€â”€ app/
â”‚   â””â”€â”€ components/
â”‚       â””â”€â”€ streaming/
â”‚           â”œâ”€â”€ StreamingNav.tsx
â”‚           â”œâ”€â”€ HeroSection.tsx
â”‚           â”œâ”€â”€ StreamingCard.tsx
â”‚           â”œâ”€â”€ SkeletonCard.tsx
â”‚           â””â”€â”€ ContentCarousel.tsx
```

## Extending the System

### Adding New Tokens

1. Define in `/src/styles/tokens.css`
2. Use CSS custom properties: `--token-name: value`
3. Reference in components: `var(--token-name)`

### Creating New Components

1. Import Motion for animations
2. Use existing tokens for consistency
3. Support all interaction states (default, hover, active, focus, disabled)
4. Include loading/skeleton states where applicable
5. Make responsive by default

## Token Export

This system can be exported to:
- **Figma Variables**: Map CSS custom properties to Figma variables
- **Style Dictionary**: Use for multi-platform token generation
- **Tailwind Config**: Extend Tailwind with custom tokens
- **Design Documentation**: Auto-generate from token comments
