package com.example.beon.designsystem.tokens

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

/**
 * Gradient tokens for the BeOn design system.
 *
 * Mirrors `semantic.gradient` in the Figma Make export. Gradients are
 * exposed as Compose [Brush] so they can be applied directly to any
 * surface via `Modifier.background(BeOnTheme.gradients.buttonPrimary)`.
 *
 * Note on direction: the Web `linear-gradient(135deg, …)` runs from
 * top-left to bottom-right. In Compose that's `Brush.linearGradient` with
 * `start = Offset.Zero` and `end = Offset(size.width, size.height)`, which
 * is exactly what [Brush.linearGradient] without explicit offsets does on
 * a square. For non-square components the visual angle changes slightly
 * but matches the Web's behavior under the same conditions.
 */
@Immutable
data class BeOnGradients(
    /** Primary CTA gradient — green-500 → green-accent at 135°. */
    val buttonPrimary: Brush,
    /** Play CTA on content detail — teal → lime at 90°. */
    val buttonPlay: Brush,
    /** Hero section overlay — left-to-right black fade. */
    val heroOverlay: Brush,
    /** Player top chrome fade. */
    val playerTop: Brush,
    /** Player bottom chrome fade. */
    val playerBottom: Brush,
    /** Card hover bottom-up gradient — reveals metadata. */
    val cardHover: Brush,
    /** Skeleton shimmer base gradient. */
    val skeletonShimmer: Brush,
    /** Skeleton shimmer with green accent tint. */
    val skeletonShimmerAccent: Brush,
)

internal fun beOnDefaultGradients(colors: BeOnColors): BeOnGradients = BeOnGradients(
    buttonPrimary = Brush.linearGradient(
        colors = listOf(colors.accent.primary, colors.accent.bright),
    ),
    buttonPlay = Brush.horizontalGradient(
        colors = listOf(
            Color(0xFF5EEAD4),
            Color(0xFF84CC16),
        ),
    ),
    heroOverlay = Brush.horizontalGradient(
        colorStops = arrayOf(
            0.29f to Color(0xE6000000),
            0.99f to Color(0x99000000),
        ),
    ),
    playerTop = Brush.verticalGradient(
        colors = listOf(Color(0xCC000000), Color.Transparent),
    ),
    playerBottom = Brush.verticalGradient(
        colors = listOf(Color.Transparent, Color(0xCC000000)),
    ),
    cardHover = Brush.verticalGradient(
        colorStops = arrayOf(
            0.0f to Color.Transparent,
            0.5f to Color(0x99000000),
            1.0f to Color(0xE6000000),
        ),
    ),
    skeletonShimmer = Brush.horizontalGradient(
        colors = listOf(
            BeOnPalette.Gray950,
            Color(0xCC333333),
            BeOnPalette.Gray950,
        ),
    ),
    skeletonShimmerAccent = Brush.horizontalGradient(
        colors = listOf(
            BeOnPalette.Gray950,
            Color(0x1A22C55E),
            BeOnPalette.Gray950,
        ),
    ),
)
