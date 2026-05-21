package com.example.beon.designsystem.tokens

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Shadow and glow tokens for the BeOn design system.
 *
 * Android's elevation model differs from CSS box-shadow: Compose can't
 * recreate the colored "glow" effects out of the box. We expose two
 * representations:
 *  - [card]: a standard [Dp] elevation passable to Material3 / Surface.
 *  - [glowMedium], [glowStrong], [glowLive]: colored glows simulated as
 *    semi-transparent halos rendered behind the component via
 *    `Modifier.glow()` (see `foundation/BeOnGlow.kt`).
 */
@Immutable
data class BeOnElevations(
    val card: Dp,
    val glowMedium: BeOnGlow,
    val glowStrong: BeOnGlow,
    val glowLive: BeOnGlow,
)

@Immutable
data class BeOnGlow(
    val color: Color,
    val radius: Dp,
)

internal val BeOnDefaultElevations = BeOnElevations(
    card = 6.dp,
    glowMedium = BeOnGlow(
        color = Color(0x7305DF72), // rgba(5, 223, 114, 0.45)
        radius = 24.dp,
    ),
    glowStrong = BeOnGlow(
        color = Color(0x9905DF72), // rgba(5, 223, 114, 0.60)
        radius = 35.dp,
    ),
    glowLive = BeOnGlow(
        color = Color(0x99EF4444), // rgba(239, 68, 68, 0.60)
        radius = 15.dp,
    ),
)
