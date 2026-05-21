package com.example.beon.designsystem.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.beon.designsystem.theme.BeOnTheme

/**
 * Botón estándar del Design System BeOn.
 *
 * Sustituye al `Button` de Material para todos los CTAs de producto. Se
 * compone exclusivamente a partir de tokens (`BeOnTheme.colors.accent.*`,
 * `BeOnTheme.gradients.buttonPrimary`, `BeOnTheme.shapes.full`,
 * `BeOnTheme.spacing.*`, `BeOnTheme.motion.*`) de modo que cambiar la
 * paleta del tema actualiza todos los CTAs automáticamente.
 *
 * Variantes:
 * - [BeOnButtonVariant.Primary]: pill con degradado verde, label oscuro. CTA principal.
 * - [BeOnButtonVariant.Secondary]: outline blanco translúcido, label claro.
 * - [BeOnButtonVariant.Ghost]: solo texto, sin fondo ni borde.
 * - [BeOnButtonVariant.Destructive]: fondo error sólido, label claro.
 *
 * Tamaños: [BeOnButtonSize.Small] / [Medium] / [Large] controlan alto
 * mínimo y padding horizontal — tipografía siempre `buttonLabel`.
 */
@Composable
fun BeOnButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: BeOnButtonVariant = BeOnButtonVariant.Primary,
    size: BeOnButtonSize = BeOnButtonSize.Medium,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
) {
    val motion = BeOnTheme.motion
    val typography = BeOnTheme.typography
    val shape = RoundedCornerShape(percent = 50)

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val targetScale = when {
        !enabled -> 1f
        isPressed -> motion.scales.pressed
        else -> 1f
    }
    val scale by animateFloatAsState(
        targetValue = targetScale,
        animationSpec = tween(durationMillis = motion.durations.fast, easing = motion.easings.snappy),
        label = "BeOnButtonScale",
    )

    val backgroundBrush = variant.backgroundBrush(enabled = enabled)
    val borderColor = variant.borderColor(enabled = enabled)
    val labelColor = variant.labelColor(enabled = enabled)
    val verticalPadding = size.verticalPadding
    val horizontalPadding = size.horizontalPadding
    val iconSize = size.iconSize
    val gap = BeOnTheme.spacing.xs

    Box(
        modifier = modifier
            .scale(scale)
            .alpha(if (enabled) 1f else 0.5f)
            .clip(shape)
            .background(brush = backgroundBrush, shape = shape)
            .then(
                if (borderColor != null) {
                    Modifier.border(
                        width = BeOnTheme.borderWidth.sm,
                        color = borderColor,
                        shape = shape,
                    )
                } else Modifier
            )
            .clickable(
                enabled = enabled,
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick,
            )
            .defaultMinSize(minHeight = size.minHeight)
            .heightIn(min = size.minHeight)
            .padding(horizontal = horizontalPadding, vertical = verticalPadding),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(gap),
        ) {
            if (leadingIcon != null) {
                Icon(
                    painter = rememberVectorPainter(leadingIcon),
                    contentDescription = null,
                    tint = labelColor,
                    modifier = Modifier.size(iconSize),
                )
            }
            Text(
                text = text,
                style = typography.buttonLabel,
                color = labelColor,
            )
            if (trailingIcon != null) {
                Icon(
                    painter = rememberVectorPainter(trailingIcon),
                    contentDescription = null,
                    tint = labelColor,
                    modifier = Modifier.size(iconSize),
                )
            }
        }
    }
}

/**
 * Botón circular para acciones puramente icónicas (player, navbar, etc.).
 *
 * Reutiliza los mismos colores semánticos que [BeOnButton] pero forza una
 * silueta cuadrada con esquinas tipo pill.
 */
@Composable
fun BeOnIconButton(
    icon: ImageVector,
    onClick: () -> Unit,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    variant: BeOnButtonVariant = BeOnButtonVariant.Secondary,
    size: BeOnButtonSize = BeOnButtonSize.Medium,
    enabled: Boolean = true,
) {
    val motion = BeOnTheme.motion
    val shape = RoundedCornerShape(percent = 50)
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = if (isPressed && enabled) motion.scales.pressed else 1f,
        animationSpec = tween(durationMillis = motion.durations.fast, easing = motion.easings.snappy),
        label = "BeOnIconButtonScale",
    )

    val backgroundBrush = variant.backgroundBrush(enabled = enabled)
    val borderColor = variant.borderColor(enabled = enabled)
    val tint = variant.labelColor(enabled = enabled)

    Box(
        modifier = modifier
            .scale(scale)
            .alpha(if (enabled) 1f else 0.5f)
            .size(size.minHeight)
            .clip(shape)
            .background(brush = backgroundBrush, shape = shape)
            .then(
                if (borderColor != null) {
                    Modifier.border(
                        width = BeOnTheme.borderWidth.sm,
                        color = borderColor,
                        shape = shape,
                    )
                } else Modifier
            )
            .clickable(
                enabled = enabled,
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = rememberVectorPainter(icon),
            contentDescription = contentDescription,
            tint = tint,
            modifier = Modifier.size(size.iconSize),
        )
    }
}

enum class BeOnButtonVariant {
    Primary,
    Secondary,
    Ghost,
    Destructive,
}

enum class BeOnButtonSize(
    val minHeight: Dp,
    val horizontalPadding: Dp,
    val verticalPadding: Dp,
    val iconSize: Dp,
) {
    Small(minHeight = 32.dp, horizontalPadding = 16.dp, verticalPadding = 4.dp, iconSize = 16.dp),
    Medium(minHeight = 44.dp, horizontalPadding = 24.dp, verticalPadding = 8.dp, iconSize = 18.dp),
    Large(minHeight = 56.dp, horizontalPadding = 32.dp, verticalPadding = 12.dp, iconSize = 22.dp),
}

@Composable
private fun BeOnButtonVariant.backgroundBrush(enabled: Boolean): Brush {
    val colors = BeOnTheme.colors
    val gradients = BeOnTheme.gradients
    return when (this) {
        BeOnButtonVariant.Primary ->
            if (enabled) gradients.buttonPrimary
            else SolidColor(colors.background.elevated)
        BeOnButtonVariant.Secondary -> SolidColor(Color.Transparent)
        BeOnButtonVariant.Ghost -> SolidColor(Color.Transparent)
        BeOnButtonVariant.Destructive -> SolidColor(colors.text.error)
    }
}

@Composable
private fun BeOnButtonVariant.borderColor(enabled: Boolean): Color? {
    val colors = BeOnTheme.colors
    return when (this) {
        BeOnButtonVariant.Primary -> null
        BeOnButtonVariant.Secondary ->
            if (enabled) colors.border.strong else colors.border.medium
        BeOnButtonVariant.Ghost -> null
        BeOnButtonVariant.Destructive -> null
    }
}

@Composable
private fun BeOnButtonVariant.labelColor(enabled: Boolean): Color {
    val colors = BeOnTheme.colors
    return when (this) {
        BeOnButtonVariant.Primary -> colors.text.onAccent
        BeOnButtonVariant.Secondary -> colors.text.primary
        BeOnButtonVariant.Ghost ->
            if (enabled) colors.accent.bright else colors.text.disabled
        BeOnButtonVariant.Destructive -> colors.text.primary
    }
}

@Suppress("UnusedPrivateMember")
@Preview(showBackground = true, backgroundColor = 0xFF000000, widthDp = 360)
@Composable
private fun BeOnButtonPreview() {
    BeOnTheme {
        Box(
            modifier = Modifier
                .background(BeOnTheme.colors.background.primary)
                .padding(BeOnTheme.spacing.md),
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(BeOnTheme.spacing.sm)) {
                BeOnButton(text = "REPRODUCIR", onClick = {})
                BeOnButton(
                    text = "MI LISTA",
                    onClick = {},
                    variant = BeOnButtonVariant.Secondary,
                )
                BeOnButton(
                    text = "MÁS",
                    onClick = {},
                    variant = BeOnButtonVariant.Ghost,
                )
            }
        }
    }
}
