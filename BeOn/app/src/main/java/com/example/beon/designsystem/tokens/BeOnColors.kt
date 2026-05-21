package com.example.beon.designsystem.tokens

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/**
 * Semantic color tokens for the BeOn streaming platform.
 *
 * These map 1:1 against `design-tokens/semantic.json` and represent the
 * only color surface UI code should consume. Access via
 * `BeOnTheme.colors.background.surface`, etc.
 */
@Immutable
data class BeOnColors(
    val background: Background,
    val text: Text,
    val accent: Accent,
    val border: Border,
    val status: Status,
    val badge: Badge,
) {
    @Immutable
    data class Background(
        val primary: Color,
        val surface: Color,
        val elevated: Color,
        val overlay: Color,
    )

    @Immutable
    data class Text(
        val primary: Color,
        val secondary: Color,
        val disabled: Color,
        val success: Color,
        val error: Color,
        val warning: Color,
        val onAccent: Color,
    )

    @Immutable
    data class Accent(
        val primary: Color,
        val hover: Color,
        val bright: Color,
    )

    @Immutable
    data class Border(
        val default: Color,
        val subtle: Color,
        val medium: Color,
        val strong: Color,
    )

    @Immutable
    data class Status(
        val live: Color,
        val new: Color,
    )

    /**
     * Badges sobre miniaturas y cards: chip de "AD", "+18", "LIVE", etc.
     * Mapea contra `color-ad-badge` del Figma DS.
     */
    @Immutable
    data class Badge(
        val ad: Color,
    )
}

internal val BeOnDarkColors = BeOnColors(
    background = BeOnColors.Background(
        primary = BeOnPalette.Black,
        surface = BeOnPalette.Gray950,
        elevated = BeOnPalette.Gray850,
        overlay = Color(0xCC000000),
    ),
    text = BeOnColors.Text(
        primary = BeOnPalette.White,
        secondary = BeOnPalette.Gray400,
        disabled = BeOnPalette.Gray600,
        success = BeOnPalette.Green500,
        error = BeOnPalette.RedError,
        warning = BeOnPalette.Yellow500,
        onAccent = BeOnPalette.Black,
    ),
    accent = BeOnColors.Accent(
        primary = BeOnPalette.Green500,
        hover = BeOnPalette.GreenHover,
        bright = BeOnPalette.GreenAccent,
    ),
    border = BeOnColors.Border(
        default = BeOnPalette.Gray900,
        subtle = Color(0x0DFFFFFF),
        medium = Color(0x1AFFFFFF),
        strong = Color(0x33FFFFFF),
    ),
    status = BeOnColors.Status(
        live = BeOnPalette.Red500,
        new = BeOnPalette.Green500,
    ),
    badge = BeOnColors.Badge(
        ad = BeOnPalette.Gray800,
    ),
)
