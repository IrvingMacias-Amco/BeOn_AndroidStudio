package com.example.beon.designsystem.tokens

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.runtime.Immutable

/**
 * Motion tokens — durations, easings and scale factors for the BeOn DS.
 *
 * Mirrors `design-tokens/motion.json`. Durations are expressed in
 * milliseconds (Int) so they can be passed directly to
 * [androidx.compose.animation.core.tween].
 */
@Immutable
data class BeOnDurations(
    val instant: Int,
    val fast: Int,
    val normal: Int,
    val medium: Int,
    val slow: Int,
    val slower: Int,
    val slowest: Int,
)

@Immutable
data class BeOnEasings(
    val linear: Easing,
    val easeIn: Easing,
    val easeOut: Easing,
    val easeInOut: Easing,
    val smooth: Easing,
    val snappy: Easing,
    val bounce: Easing,
)

@Immutable
data class BeOnScales(
    val hover: Float,
    val hoverLarge: Float,
    val pressed: Float,
    val pressedStrong: Float,
)

@Immutable
data class BeOnMotion(
    val durations: BeOnDurations,
    val easings: BeOnEasings,
    val scales: BeOnScales,
)

internal val BeOnDefaultMotion = BeOnMotion(
    durations = BeOnDurations(
        instant = 0,
        fast = 100,
        normal = 200,
        medium = 300,
        slow = 500,
        slower = 800,
        slowest = 1000,
    ),
    easings = BeOnEasings(
        linear = LinearEasing,
        easeIn = CubicBezierEasing(0.4f, 0f, 1f, 1f),
        easeOut = CubicBezierEasing(0f, 0f, 0.2f, 1f),
        easeInOut = CubicBezierEasing(0.4f, 0f, 0.2f, 1f),
        smooth = FastOutSlowInEasing,
        snappy = CubicBezierEasing(0.4f, 0f, 0.6f, 1f),
        bounce = CubicBezierEasing(0.68f, -0.55f, 0.265f, 1.55f),
    ),
    scales = BeOnScales(
        hover = 1.05f,
        hoverLarge = 1.10f,
        pressed = 0.95f,
        pressedStrong = 0.90f,
    ),
)
