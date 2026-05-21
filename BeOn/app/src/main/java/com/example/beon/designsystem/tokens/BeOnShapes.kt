package com.example.beon.designsystem.tokens

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Corner radius and border-width tokens for the BeOn design system.
 *
 * Mirrors `primitives.borderRadius` and `primitives.borderWidth` from the
 * Figma Make export. `radii.full` is implemented as `RoundedCornerShape(50)`
 * (percent-based) so it stays pill-shaped regardless of component height.
 */
@Immutable
data class BeOnRadius(
    val none: Dp,
    val sm: Dp,
    val md: Dp,
    val lg: Dp,
    val xl: Dp,
)

@Immutable
data class BeOnShapes(
    val none: Shape,
    val sm: Shape,
    val md: Shape,
    val lg: Shape,
    val xl: Shape,
    /** Pill / fully circular — for primary buttons and icon buttons. */
    val full: Shape,
)

@Immutable
data class BeOnBorderWidth(
    val none: Dp,
    val sm: Dp,
    val md: Dp,
    val lg: Dp,
)

internal val BeOnDefaultRadius = BeOnRadius(
    none = 0.dp,
    sm = 5.dp, // 4.8 in tokens — rounded up so dp value stays integer
    md = 12.dp,
    lg = 16.dp,
    xl = 24.dp,
)

internal val BeOnDefaultShapes = BeOnShapes(
    none = RoundedCornerShape(0.dp),
    sm = RoundedCornerShape(5.dp),
    md = RoundedCornerShape(12.dp),
    lg = RoundedCornerShape(16.dp),
    xl = RoundedCornerShape(24.dp),
    full = RoundedCornerShape(percent = 50),
)

internal val BeOnDefaultBorderWidth = BeOnBorderWidth(
    none = 0.dp,
    sm = 1.dp,
    md = 2.dp,
    lg = 3.dp,
)
