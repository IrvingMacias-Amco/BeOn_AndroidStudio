package com.example.beon.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import com.example.beon.designsystem.tokens.BeOnBorderWidth
import com.example.beon.designsystem.tokens.BeOnColors
import com.example.beon.designsystem.tokens.BeOnDarkColors
import com.example.beon.designsystem.tokens.BeOnDefaultBorderWidth
import com.example.beon.designsystem.tokens.BeOnDefaultElevations
import com.example.beon.designsystem.tokens.BeOnDefaultMotion
import com.example.beon.designsystem.tokens.BeOnDefaultRadius
import com.example.beon.designsystem.tokens.BeOnDefaultSafeArea
import com.example.beon.designsystem.tokens.BeOnDefaultShapes
import com.example.beon.designsystem.tokens.BeOnDefaultSpacing
import com.example.beon.designsystem.tokens.BeOnDefaultSpacingScale
import com.example.beon.designsystem.tokens.BeOnElevations
import com.example.beon.designsystem.tokens.BeOnGradients
import com.example.beon.designsystem.tokens.BeOnMotion
import com.example.beon.designsystem.tokens.BeOnRadius
import com.example.beon.designsystem.tokens.BeOnSafeArea
import com.example.beon.designsystem.tokens.BeOnShapes
import com.example.beon.designsystem.tokens.BeOnSpacing
import com.example.beon.designsystem.tokens.BeOnSpacingScale
import com.example.beon.designsystem.tokens.BeOnTypography
import com.example.beon.designsystem.tokens.beOnDefaultGradients
import com.example.beon.designsystem.tokens.beOnDefaultTypography

/**
 * Root theme for the BeOn streaming platform.
 *
 * Wrap your app (or any preview) in [BeOnTheme] to expose the full token
 * set through the [BeOnTheme] accessor object. Material3 is also kept
 * configured underneath so that built-in Material components inherit the
 * BeOn palette automatically.
 *
 * Example:
 * ```
 * BeOnTheme {
 *   Text(
 *     text = "Marty Supremo",
 *     style = BeOnTheme.typography.h1,
 *     color = BeOnTheme.colors.text.primary,
 *   )
 * }
 * ```
 *
 * The theme is dark-only by design — BeOn is an OTT product where light
 * mode is not currently in scope. The signature exposes a `darkTheme`
 * flag for future light-mode support.
 */
@Composable
fun BeOnTheme(
    @Suppress("UNUSED_PARAMETER") darkTheme: Boolean = true,
    content: @Composable () -> Unit,
) {
    val colors = BeOnDarkColors
    val typography = beOnDefaultTypography()
    val gradients = beOnDefaultGradients(colors)

    val materialColorScheme = darkColorScheme(
        primary = colors.accent.primary,
        onPrimary = colors.text.onAccent,
        secondary = colors.accent.bright,
        onSecondary = colors.text.onAccent,
        background = colors.background.primary,
        onBackground = colors.text.primary,
        surface = colors.background.surface,
        onSurface = colors.text.primary,
        surfaceVariant = colors.background.elevated,
        onSurfaceVariant = colors.text.secondary,
        error = colors.text.error,
        onError = colors.text.primary,
        outline = colors.border.strong,
        outlineVariant = colors.border.medium,
    )

    CompositionLocalProvider(
        LocalBeOnColors provides colors,
        LocalBeOnTypography provides typography,
        LocalBeOnSpacing provides BeOnDefaultSpacing,
        LocalBeOnSpacingScale provides BeOnDefaultSpacingScale,
        LocalBeOnSafeArea provides BeOnDefaultSafeArea,
        LocalBeOnRadius provides BeOnDefaultRadius,
        LocalBeOnShapes provides BeOnDefaultShapes,
        LocalBeOnBorderWidth provides BeOnDefaultBorderWidth,
        LocalBeOnMotion provides BeOnDefaultMotion,
        LocalBeOnGradients provides gradients,
        LocalBeOnElevations provides BeOnDefaultElevations,
    ) {
        MaterialTheme(
            colorScheme = materialColorScheme,
            content = content,
        )
    }
}

/**
 * Accessor object that mirrors the Material3 `MaterialTheme` pattern.
 * All UI code should read tokens through this object so swapping a
 * single value at the theme level propagates everywhere.
 */
object BeOnTheme {
    val colors: BeOnColors
        @Composable
        @ReadOnlyComposable
        get() = LocalBeOnColors.current

    val typography: BeOnTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalBeOnTypography.current

    val spacing: BeOnSpacing
        @Composable
        @ReadOnlyComposable
        get() = LocalBeOnSpacing.current

    val spacingScale: BeOnSpacingScale
        @Composable
        @ReadOnlyComposable
        get() = LocalBeOnSpacingScale.current

    val safeArea: BeOnSafeArea
        @Composable
        @ReadOnlyComposable
        get() = LocalBeOnSafeArea.current

    val radius: BeOnRadius
        @Composable
        @ReadOnlyComposable
        get() = LocalBeOnRadius.current

    val shapes: BeOnShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalBeOnShapes.current

    val borderWidth: BeOnBorderWidth
        @Composable
        @ReadOnlyComposable
        get() = LocalBeOnBorderWidth.current

    val motion: BeOnMotion
        @Composable
        @ReadOnlyComposable
        get() = LocalBeOnMotion.current

    val gradients: BeOnGradients
        @Composable
        @ReadOnlyComposable
        get() = LocalBeOnGradients.current

    val elevations: BeOnElevations
        @Composable
        @ReadOnlyComposable
        get() = LocalBeOnElevations.current
}

// CompositionLocals — internal so consumers go through BeOnTheme.x

internal val LocalBeOnColors = staticCompositionLocalOf<BeOnColors> {
    error("BeOnColors not provided. Wrap your composable tree in BeOnTheme { }.")
}

internal val LocalBeOnTypography = staticCompositionLocalOf<BeOnTypography> {
    error("BeOnTypography not provided. Wrap your composable tree in BeOnTheme { }.")
}

internal val LocalBeOnSpacing = staticCompositionLocalOf<BeOnSpacing> {
    error("BeOnSpacing not provided. Wrap your composable tree in BeOnTheme { }.")
}

internal val LocalBeOnSpacingScale = staticCompositionLocalOf<BeOnSpacingScale> {
    error("BeOnSpacingScale not provided. Wrap your composable tree in BeOnTheme { }.")
}

internal val LocalBeOnSafeArea = staticCompositionLocalOf<BeOnSafeArea> {
    error("BeOnSafeArea not provided. Wrap your composable tree in BeOnTheme { }.")
}

internal val LocalBeOnRadius = staticCompositionLocalOf<BeOnRadius> {
    error("BeOnRadius not provided. Wrap your composable tree in BeOnTheme { }.")
}

internal val LocalBeOnShapes = staticCompositionLocalOf<BeOnShapes> {
    error("BeOnShapes not provided. Wrap your composable tree in BeOnTheme { }.")
}

internal val LocalBeOnBorderWidth = staticCompositionLocalOf<BeOnBorderWidth> {
    error("BeOnBorderWidth not provided. Wrap your composable tree in BeOnTheme { }.")
}

internal val LocalBeOnMotion = staticCompositionLocalOf<BeOnMotion> {
    error("BeOnMotion not provided. Wrap your composable tree in BeOnTheme { }.")
}

/** Gradients depend on the current color scheme, so use [compositionLocalOf]. */
internal val LocalBeOnGradients = compositionLocalOf<BeOnGradients> {
    error("BeOnGradients not provided. Wrap your composable tree in BeOnTheme { }.")
}

internal val LocalBeOnElevations = staticCompositionLocalOf<BeOnElevations> {
    error("BeOnElevations not provided. Wrap your composable tree in BeOnTheme { }.")
}
