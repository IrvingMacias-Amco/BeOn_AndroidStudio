package com.example.beon.designsystem.showcase

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import com.example.beon.designsystem.components.BeOnButton
import com.example.beon.designsystem.components.BeOnButtonSize
import com.example.beon.designsystem.components.BeOnButtonVariant
import com.example.beon.designsystem.components.BeOnIconButton
import com.example.beon.designsystem.components.BeOnLogo
import com.example.beon.designsystem.components.BeOnLogoSize
import com.example.beon.designsystem.components.BeOnLogoVariant
import com.example.beon.designsystem.theme.BeOnTheme

/**
 * Visual catalog of every BeOn design token.
 *
 * Used as the launch screen of the app during the design-system work for
 * SCRUM-29 so reviewers can spot drift against the Figma reference in a
 * single scroll. Each section reads exclusively from `BeOnTheme.*` —
 * the only hard-coded dimensions here are the dimensions OF the showcase
 * itself (swatch tile size, etc.), never anything an app consumer would
 * read.
 */
@Composable
fun DesignSystemShowcase(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BeOnTheme.colors.background.primary),
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding(),
            contentPadding = PaddingValues(
                start = BeOnTheme.safeArea.mobile,
                end = BeOnTheme.safeArea.mobile,
                top = BeOnTheme.spacing.lg,
                bottom = BeOnTheme.spacing.xxl,
            ),
            verticalArrangement = Arrangement.spacedBy(BeOnTheme.spacing.lg),
        ) {
            item { Header() }
            item { LogoSection() }
            item { ButtonsSection() }
            item { TypographySection() }
            item { ColorsSection() }
            item { GradientsSection() }
            item { SpacingSection() }
            item { RadiusSection() }
            item { GlowsSection() }
        }
    }
}

@Composable
private fun Header() {
    Column {
        Text(
            text = "Hello world",
            style = BeOnTheme.typography.h1,
            color = BeOnTheme.colors.text.primary,
        )
        Spacer(Modifier.height(BeOnTheme.spacing.sm))
        Text(
            text = "BeOn Design System",
            style = BeOnTheme.typography.h1,
            color = BeOnTheme.colors.text.primary,
        )
        Spacer(Modifier.height(BeOnTheme.spacing.xxs))
        Text(
            text = "Phase 1 · Tokens & Theme",
            style = BeOnTheme.typography.bodyLarge,
            color = BeOnTheme.colors.accent.bright,
        )
        Spacer(Modifier.height(BeOnTheme.spacing.xs))
        Text(
            text = "Catálogo vivo de tokens generado desde el pipeline de " +
                "Figma Make. Cada valor que ves aquí se lee de BeOnTheme.* — " +
                "ningún hard-code.",
            style = BeOnTheme.typography.bodySmall,
            color = BeOnTheme.colors.text.secondary,
        )
    }
}

@Composable
private fun TypographySection() {
    SectionCard(title = "Typography · Inter") {
        TypeRow("H1 · 48 / Black", BeOnTheme.typography.h1, "Marty Supremo")
        TypeRow("H2 · 30 / Bold", BeOnTheme.typography.h2, "Recién agregado")
        TypeRow("H3 · 24 / Bold", BeOnTheme.typography.h3, "Top 10 en México")
        TypeRow("H4 · 20 / SemiBold", BeOnTheme.typography.h4, "Continuar viendo")
        TypeRow("Body Large · 18 / Medium", BeOnTheme.typography.bodyLarge, "Disfruta transmisión en vivo 24/7.")
        TypeRow("Body Medium · 16 / Regular", BeOnTheme.typography.bodyMedium, "Series, películas y deportes.")
        TypeRow("Body Small · 14 / Regular", BeOnTheme.typography.bodySmall, "Todo el contenido que amas.")
        TypeRow("Caption · 12 / Regular", BeOnTheme.typography.captionSmall, "2025 · 2h 29min · IMDb 7.5")
        TypeRow("Button Label · 14 / SemiBold", BeOnTheme.typography.buttonLabel, "REPRODUCIR")
    }
}

@Composable
private fun TypeRow(label: String, style: TextStyle, sample: String) {
    Column(verticalArrangement = Arrangement.spacedBy(BeOnTheme.spacing.xxs)) {
        Text(
            text = label,
            style = BeOnTheme.typography.captionSmall,
            color = BeOnTheme.colors.text.secondary,
        )
        Text(
            text = sample,
            style = style,
            color = BeOnTheme.colors.text.primary,
        )
    }
}

@Composable
private fun ColorsSection() {
    SectionCard(title = "Color Tokens") {
        SwatchGroup("Background") {
            Swatch("primary", BeOnTheme.colors.background.primary)
            Swatch("surface", BeOnTheme.colors.background.surface)
            Swatch("elevated", BeOnTheme.colors.background.elevated)
            Swatch("overlay", BeOnTheme.colors.background.overlay)
        }
        SwatchGroup("Accent") {
            Swatch("primary", BeOnTheme.colors.accent.primary)
            Swatch("hover", BeOnTheme.colors.accent.hover)
            Swatch("bright", BeOnTheme.colors.accent.bright)
        }
        SwatchGroup("Text") {
            Swatch("primary", BeOnTheme.colors.text.primary)
            Swatch("secondary", BeOnTheme.colors.text.secondary)
            Swatch("disabled", BeOnTheme.colors.text.disabled)
            Swatch("onAccent", BeOnTheme.colors.text.onAccent)
        }
        SwatchGroup("Status") {
            Swatch("live", BeOnTheme.colors.status.live)
            Swatch("new", BeOnTheme.colors.status.new)
            Swatch("error", BeOnTheme.colors.text.error)
            Swatch("warning", BeOnTheme.colors.text.warning)
        }
        SwatchGroup("Badge") {
            Swatch("ad", BeOnTheme.colors.badge.ad)
        }
        SwatchGroup("Border") {
            Swatch("subtle", BeOnTheme.colors.border.subtle)
            Swatch("medium", BeOnTheme.colors.border.medium)
            Swatch("strong", BeOnTheme.colors.border.strong)
        }
    }
}

@Composable
private fun SwatchGroup(label: String, content: @Composable () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(BeOnTheme.spacing.xs)) {
        Text(
            text = label,
            style = BeOnTheme.typography.captionLarge,
            color = BeOnTheme.colors.text.secondary,
        )
        Row(horizontalArrangement = Arrangement.spacedBy(BeOnTheme.spacing.xs)) {
            content()
        }
    }
}

@Composable
private fun Swatch(name: String, color: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(BeOnTheme.spacing.xxs),
        modifier = Modifier.width(SwatchWidth),
    ) {
        Box(
            modifier = Modifier
                .size(SwatchSize)
                .background(color, BeOnTheme.shapes.md)
                .border(
                    width = BeOnTheme.borderWidth.sm,
                    color = BeOnTheme.colors.border.subtle,
                    shape = BeOnTheme.shapes.md,
                ),
        )
        Text(
            text = name,
            style = BeOnTheme.typography.captionTiny,
            color = BeOnTheme.colors.text.secondary,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun GradientsSection() {
    SectionCard(title = "Gradients") {
        GradientRow("Button Primary (CTA)", BeOnTheme.gradients.buttonPrimary)
        GradientRow("Hero Overlay", BeOnTheme.gradients.heroOverlay)
        GradientRow("Card Hover", BeOnTheme.gradients.cardHover)
        GradientRow("Skeleton Shimmer", BeOnTheme.gradients.skeletonShimmer)
    }
}

@Composable
private fun GradientRow(label: String, brush: Brush) {
    Column(verticalArrangement = Arrangement.spacedBy(BeOnTheme.spacing.xxs)) {
        Text(
            text = label,
            style = BeOnTheme.typography.captionSmall,
            color = BeOnTheme.colors.text.secondary,
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(GradientPreviewHeight)
                .background(brush, BeOnTheme.shapes.md),
        )
    }
}

@Composable
private fun SpacingSection() {
    SectionCard(title = "Spacing scale") {
        val items = listOf(
            "xxs · 4dp" to BeOnTheme.spacing.xxs,
            "xs · 8dp" to BeOnTheme.spacing.xs,
            "sm · 16dp" to BeOnTheme.spacing.sm,
            "md · 24dp" to BeOnTheme.spacing.md,
            "lg · 40dp" to BeOnTheme.spacing.lg,
            "xl · 64dp" to BeOnTheme.spacing.xl,
            "xxl · 96dp" to BeOnTheme.spacing.xxl,
        )
        items.forEach { (label, size) ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BeOnTheme.spacing.sm),
            ) {
                Text(
                    text = label,
                    style = BeOnTheme.typography.captionSmall,
                    color = BeOnTheme.colors.text.secondary,
                    modifier = Modifier.width(SpacingLabelWidth),
                )
                Box(
                    modifier = Modifier
                        .height(BeOnTheme.spacing.xxs)
                        .width(size)
                        .background(
                            color = BeOnTheme.colors.accent.primary,
                            shape = BeOnTheme.shapes.full,
                        ),
                )
            }
        }
    }
}

@Composable
private fun RadiusSection() {
    SectionCard(title = "Radius") {
        Row(horizontalArrangement = Arrangement.spacedBy(BeOnTheme.spacing.sm)) {
            RadiusSample("sm", BeOnTheme.shapes.sm)
            RadiusSample("md", BeOnTheme.shapes.md)
            RadiusSample("lg", BeOnTheme.shapes.lg)
            RadiusSample("xl", BeOnTheme.shapes.xl)
            RadiusSample("full", BeOnTheme.shapes.full)
        }
    }
}

@Composable
private fun RadiusSample(label: String, shape: Shape) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(BeOnTheme.spacing.xxs),
    ) {
        Box(
            modifier = Modifier
                .size(SwatchSize)
                .background(BeOnTheme.colors.background.elevated, shape)
                .border(
                    width = BeOnTheme.borderWidth.sm,
                    color = BeOnTheme.colors.border.strong,
                    shape = shape,
                ),
        )
        Text(
            text = label,
            style = BeOnTheme.typography.captionTiny,
            color = BeOnTheme.colors.text.secondary,
        )
    }
}

@Composable
private fun GlowsSection() {
    SectionCard(title = "Glow tokens (rendered in Phase 2)") {
        Row(horizontalArrangement = Arrangement.spacedBy(BeOnTheme.spacing.md)) {
            GlowDot("medium", BeOnTheme.elevations.glowMedium.color)
            GlowDot("strong", BeOnTheme.elevations.glowStrong.color)
            GlowDot("live", BeOnTheme.elevations.glowLive.color)
        }
        Text(
            text = "Los halos reales se aplicarán vía Modifier.beOnGlow() en " +
                "los componentes (Fase 2).",
            style = BeOnTheme.typography.captionTiny,
            color = BeOnTheme.colors.text.secondary,
        )
    }
}

@Composable
private fun GlowDot(label: String, color: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(BeOnTheme.spacing.xxs),
    ) {
        Box(
            modifier = Modifier
                .size(GlowDotSize)
                .background(color, CircleShape),
        )
        Text(
            text = label,
            style = BeOnTheme.typography.captionTiny,
            color = BeOnTheme.colors.text.secondary,
        )
    }
}

@Composable
private fun LogoSection() {
    SectionCard(title = "Logo · BeOn") {
        Column(verticalArrangement = Arrangement.spacedBy(BeOnTheme.spacing.sm)) {
            LogoRow("Color · L", BeOnLogoVariant.Color, BeOnLogoSize.Large)
            LogoRow("Color · M", BeOnLogoVariant.Color, BeOnLogoSize.Medium)
            LogoRow("Mono · M", BeOnLogoVariant.Mono, BeOnLogoSize.Medium)
            LogoRow("Framed · L", BeOnLogoVariant.Framed, BeOnLogoSize.Large)
            LogoRow("On · M", BeOnLogoVariant.On, BeOnLogoSize.Medium)
        }
    }
}

@Composable
private fun LogoRow(label: String, variant: BeOnLogoVariant, size: BeOnLogoSize) {
    Column(verticalArrangement = Arrangement.spacedBy(BeOnTheme.spacing.xxs)) {
        Text(
            text = label,
            style = BeOnTheme.typography.captionSmall,
            color = BeOnTheme.colors.text.secondary,
        )
        BeOnLogo(variant = variant, size = size)
    }
}

@Composable
private fun ButtonsSection() {
    SectionCard(title = "Buttons") {
        Column(verticalArrangement = Arrangement.spacedBy(BeOnTheme.spacing.sm)) {
            ButtonGroupLabel("Primary · CTA con degradado")
            Row(horizontalArrangement = Arrangement.spacedBy(BeOnTheme.spacing.xs)) {
                BeOnButton(
                    text = "REPRODUCIR",
                    onClick = {},
                    leadingIcon = Icons.Filled.PlayArrow,
                    size = BeOnButtonSize.Large,
                )
                BeOnButton(text = "VER AHORA", onClick = {})
            }

            ButtonGroupLabel("Secondary · Outlined")
            Row(horizontalArrangement = Arrangement.spacedBy(BeOnTheme.spacing.xs)) {
                BeOnButton(
                    text = "MI LISTA",
                    onClick = {},
                    variant = BeOnButtonVariant.Secondary,
                    leadingIcon = Icons.Filled.Add,
                )
                BeOnButton(
                    text = "TRÁILER",
                    onClick = {},
                    variant = BeOnButtonVariant.Secondary,
                    size = BeOnButtonSize.Small,
                )
            }

            ButtonGroupLabel("Ghost · Solo texto")
            Row(horizontalArrangement = Arrangement.spacedBy(BeOnTheme.spacing.xs)) {
                BeOnButton(
                    text = "MÁS INFO",
                    onClick = {},
                    variant = BeOnButtonVariant.Ghost,
                )
                BeOnButton(
                    text = "OMITIR",
                    onClick = {},
                    variant = BeOnButtonVariant.Ghost,
                    size = BeOnButtonSize.Small,
                )
            }

            ButtonGroupLabel("Destructive · Acciones críticas")
            Row(horizontalArrangement = Arrangement.spacedBy(BeOnTheme.spacing.xs)) {
                BeOnButton(
                    text = "ELIMINAR",
                    onClick = {},
                    variant = BeOnButtonVariant.Destructive,
                )
            }

            ButtonGroupLabel("Estados")
            Row(horizontalArrangement = Arrangement.spacedBy(BeOnTheme.spacing.xs)) {
                BeOnButton(text = "HABILITADO", onClick = {})
                BeOnButton(text = "DESHABILITADO", onClick = {}, enabled = false)
            }

            ButtonGroupLabel("Icon buttons")
            Row(horizontalArrangement = Arrangement.spacedBy(BeOnTheme.spacing.xs)) {
                BeOnIconButton(
                    icon = Icons.Filled.PlayArrow,
                    onClick = {},
                    contentDescription = "Reproducir",
                    variant = BeOnButtonVariant.Primary,
                )
                BeOnIconButton(
                    icon = Icons.Filled.Add,
                    onClick = {},
                    contentDescription = "Añadir a mi lista",
                )
                BeOnIconButton(
                    icon = Icons.Filled.Favorite,
                    onClick = {},
                    contentDescription = "Favorito",
                    variant = BeOnButtonVariant.Ghost,
                )
            }
        }
    }
}

@Composable
private fun ButtonGroupLabel(label: String) {
    Text(
        text = label,
        style = BeOnTheme.typography.captionSmall,
        color = BeOnTheme.colors.text.secondary,
    )
}

@Composable
private fun SectionCard(title: String, content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = BeOnTheme.colors.background.surface,
                shape = BeOnTheme.shapes.lg,
            )
            .border(
                width = BeOnTheme.borderWidth.sm,
                color = BeOnTheme.colors.border.subtle,
                shape = BeOnTheme.shapes.lg,
            )
            .padding(BeOnTheme.spacing.sm),
        verticalArrangement = Arrangement.spacedBy(BeOnTheme.spacing.sm),
    ) {
        Text(
            text = title,
            style = BeOnTheme.typography.h3,
            color = BeOnTheme.colors.text.primary,
        )
        content()
    }
}

// Showcase-only presentational dimensions. These are dimensions OF the
// catalog itself, not tokens that the app consumes.
private val SwatchSize = 56.dp
private val SwatchWidth = 72.dp
private val GradientPreviewHeight = 48.dp
private val SpacingLabelWidth = 96.dp
private val GlowDotSize = 40.dp

@Preview(
    showBackground = true,
    backgroundColor = 0xFF000000,
    widthDp = 360,
    heightDp = 800,
)
@Composable
private fun DesignSystemShowcasePreview() {
    BeOnTheme {
        DesignSystemShowcase()
    }
}
