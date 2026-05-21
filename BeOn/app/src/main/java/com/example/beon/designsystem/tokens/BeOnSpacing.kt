package com.example.beon.designsystem.tokens

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Semantic spacing scale for the BeOn streaming platform.
 *
 * Mirrors `semantic.spacing` in `design-tokens/semantic.json`. The raw
 * primitive scale (`spacing.0` .. `spacing.24`) is also exposed via
 * [BeOnSpacingScale] for cases where finer-grained spacing is needed.
 */
@Immutable
data class BeOnSpacing(
    /** 4dp — separación mínima entre elementos hermanos */
    val xxs: Dp,
    /** 8dp — gap entre íconos y texto en chips */
    val xs: Dp,
    /** 16dp — padding interno de la mayoría de componentes */
    val sm: Dp,
    /** 24dp — separación entre secciones cercanas */
    val md: Dp,
    /** 40dp — padding lateral de pantallas en tablet */
    val lg: Dp,
    /** 64dp — safe area horizontal en desktop */
    val xl: Dp,
    /** 96dp — padding bottom del hero */
    val xxl: Dp,
)

@Immutable
data class BeOnSpacingScale(
    val space0: Dp,
    val space1: Dp,
    val space2: Dp,
    val space3: Dp,
    val space4: Dp,
    val space5: Dp,
    val space6: Dp,
    val space7: Dp,
    val space8: Dp,
    val space9: Dp,
    val space10: Dp,
    val space12: Dp,
    val space14: Dp,
    val space16: Dp,
    val space20: Dp,
    val space24: Dp,
)

@Immutable
data class BeOnSafeArea(
    val mobile: Dp,
    val tablet: Dp,
    val desktop: Dp,
)

internal val BeOnDefaultSpacing = BeOnSpacing(
    xxs = 4.dp,
    xs = 8.dp,
    sm = 16.dp,
    md = 24.dp,
    lg = 40.dp,
    xl = 64.dp,
    xxl = 96.dp,
)

internal val BeOnDefaultSpacingScale = BeOnSpacingScale(
    space0 = 0.dp,
    space1 = 4.dp,
    space2 = 8.dp,
    space3 = 12.dp,
    space4 = 16.dp,
    space5 = 20.dp,
    space6 = 24.dp,
    space7 = 28.dp,
    space8 = 32.dp,
    space9 = 36.dp,
    space10 = 40.dp,
    space12 = 48.dp,
    space14 = 56.dp,
    space16 = 64.dp,
    space20 = 80.dp,
    space24 = 96.dp,
)

internal val BeOnDefaultSafeArea = BeOnSafeArea(
    mobile = 16.dp,
    tablet = 32.dp,
    desktop = 64.dp,
)
