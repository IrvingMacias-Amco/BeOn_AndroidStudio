# Design Token System

## Production-Ready Token Architecture for OTT Streaming Platform

This design token system provides a complete, scalable, and maintainable architecture for the streaming platform's visual design system.

---

## ðŸ“ File Structure

```
design-tokens/
â”œâ”€â”€ index.json           # Master token file with metadata and references
â”œâ”€â”€ primitives.json      # Base primitive values (colors, spacing, typography)
â”œâ”€â”€ semantic.json        # Semantic tokens (meaningful names for application context)
â”œâ”€â”€ typography.json      # Complete typography system
â”œâ”€â”€ motion.json          # Motion tokens (durations, easings, scales, transitions)
â”œâ”€â”€ components.json      # Component-specific tokens
â””â”€â”€ README.md           # This file
```

---

## ðŸŽ¯ Token Categories

### 1. **Primitives** (`primitives.json`)
Base values that form the foundation of the design system:
- **Colors**: Black, white, gray scale, green accent, red status, yellow warning
- **Spacing**: 0-96px scale
- **Border Radius**: sm (4.8px) to full (9999px)
- **Border Width**: none, sm (1px), md (2px), lg (3px)
- **Opacity**: 0-100 scale
- **Font Families**: Inter
- **Font Sizes**: 10px-64px
- **Font Weights**: Regular (400) to Black (900)
- **Letter Spacing**: Tight, normal, wide
- **Line Heights**: Tight (1.2), normal (1.5), relaxed (1.75)

### 2. **Semantic Tokens** (`semantic.json`)
Meaningful tokens that reference primitives:
- **Background**: Primary, surface, elevated, overlay
- **Text**: Primary, secondary, disabled, success, error, warning
- **Accent**: Primary, hover, bright
- **Border**: Default, subtle, medium, strong
- **Status**: Live, new
- **Safe Area**: Mobile (16px), tablet (32px), desktop (64px), ultrawide (auto)
- **Gradients**: Button, hero overlay, player overlays, card hover
- **Shadows**: Card, glow (medium, strong, live)

### 3. **Typography** (`typography.json`)
Complete text styling system:
- **Headings**: H1, H2, H3, H4
- **Body**: Large, medium, small
- **Captions**: Large, small, tiny
- Each level includes: fontSize, fontWeight, lineHeight, letterSpacing

### 4. **Motion** (`motion.json`)
Animation and transition tokens:
- **Durations**: Instant (0ms) to slowest (1000ms)
- **Easings**: Linear, ease, easeIn, easeOut, easeInOut, smooth, snappy, bounce
- **Scales**: Hover (1.05), hoverLarge (1.1), pressed (0.95), pressedStrong (0.9)
- **Animations**: FadeIn, fadeOut, slideUp, shimmer
- **Transitions**: Default, fast, opacity, transform

### 5. **Components** (`components.json`)
Component-specific design tokens:

#### **Button**
- Primary: Background gradient, text color, border radius, padding, shadow, hover/pressed states
- Ghost: Transparent with border
- Icon Only: Sizes (small: 40px, medium: 56px, large: 70px, xlarge: 110px)

#### **Card**
- Portrait: 2:3 aspect ratio (192x288px)
- Landscape: 16:9 aspect ratio (320x188px)
- Top 10: Special layout with ranking numbers (180x270px card, 256px ranking font)

#### **Carousel**
- Spacing: Gap, padding, title margin
- Title: Typography specifications
- Navigation: Size (48px), background, hover scale

#### **Nav**
- Height: 72px
- Background: rgba(0, 0, 0, 0.95)
- Backdrop blur: 20px
- Border bottom, padding

#### **Hero**
- Height: 675px
- Gradient overlay
- Content padding

#### **Player**
- Controls: Height (80px), auto-hide delay (3000ms)
- Seek bar: Height (4px), colors, border radius
- Buttons: Play (110px), skip (70px), control (32px)

#### **Badge**
- Live: Red background, glow effect
- New: Green accent background
- Tag: Semi-transparent gray background

#### **Skeleton**
- Shimmer duration: 800ms
- Gradient: Dark gray shimmer
- Gradient accent: Green accent shimmer

---

## ðŸ”— Token Reference System

Tokens use a reference system with curly braces `{}`:

```json
{
  "color": {
    "background": {
      "primary": {
        "$value": "{color.black}"
      }
    }
  }
}
```

This creates a semantic reference that:
1. **Maintains consistency** - Changes to primitives cascade to semantic tokens
2. **Improves maintainability** - Update once, apply everywhere
3. **Creates meaningful names** - `color.background.primary` is clearer than `#000000`

---

## ðŸ“¦ Export Formats

This token system is designed for compatibility with:

- **Tokens Studio** - Import/export for Figma
- **Style Dictionary** - Transform to CSS, SCSS, JavaScript, etc.
- **Figma Variables** - Direct sync with Figma design files
- **Frontend Frameworks** - CSS custom properties, JavaScript constants

---

## ðŸŽ¨ Design System Principles

### **Semantic Naming**
Tokens use meaningful names that describe their purpose, not their value:
- âœ… `color.background.primary`
- âŒ `color.black`

### **Layered Architecture**
Three-tier system for maximum flexibility:
1. **Primitives** - Raw values
2. **Semantic** - Application context
3. **Components** - Specific use cases

### **Scalability**
Token structure supports:
- Multiple themes (dark/light)
- Multiple platforms (web, mobile, TV)
- Component variations
- State management (hover, pressed, disabled)

---

## ðŸš€ Usage Examples

### CSS Custom Properties

```css
:root {
  /* Primitives */
  --color-black: #000000;
  --spacing-4: 16px;
  
  /* Semantic */
  --color-background-primary: var(--color-black);
  --spacing-sm: var(--spacing-4);
  
  /* Components */
  --button-primary-background: linear-gradient(135deg, #22C55E 0%, #05DF72 100%);
  --card-portrait-width: 192px;
}
```

### JavaScript/TypeScript

```typescript
export const tokens = {
  color: {
    background: {
      primary: '#000000',
    },
  },
  spacing: {
    sm: 16,
  },
  motion: {
    duration: {
      normal: 200,
    },
  },
};
```

---

## ðŸ“Š Token Statistics

- **Total Token Categories**: 6 (Primitives, Semantic, Typography, Motion, Components, States)
- **Color Tokens**: 40+
- **Spacing Tokens**: 24
- **Typography Tokens**: 30+
- **Motion Tokens**: 25+
- **Component Tokens**: 100+

---

## ðŸ”„ Version History

- **v1.0.0** - Initial production-ready token system
  - Complete primitive foundation
  - Semantic token layer
  - Typography system
  - Motion architecture
  - Component-specific tokens
  - State management tokens
  - Responsive system
  - Skeleton loading tokens

---

## ðŸ“ Contributing

When adding new tokens:
1. Start with primitives if introducing new base values
2. Create semantic tokens for application context
3. Add component-specific tokens as needed
4. Document usage and rationale
5. Maintain naming conventions
6. Use token references instead of hardcoded values

---

## ðŸŽ¯ Next Steps

1. **Transform tokens** - Use Style Dictionary to generate platform-specific outputs
2. **Sync with Figma** - Import tokens into Figma Variables
3. **Implement in code** - Replace hardcoded values with token references
4. **Create themes** - Extend system with light mode, alternate color schemes
5. **Add platform variants** - Mobile, TV-specific token overrides

---

**Design System**: Cinematic OTT Streaming Platform  
**Token Format**: Design Tokens Community Group Format  
**Version**: 1.0.0  
**Status**: Production Ready
