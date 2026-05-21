package com.example.beon.designsystem.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.beon.R
import com.example.beon.designsystem.theme.BeOnTheme

/**
 * Logo de BeOn exportado desde el Design System de Figma.
 *
 * El logo combina la marca denominativa "BeOn" con la "O" tratada como un
 * patrón degradado neón. Se distribuye como PNG en alta resolución (en
 * `res/drawable-nodpi/`) porque el SVG original incluye una imagen
 * embebida que no puede convertirse a [androidx.compose.ui.graphics.vector.ImageVector]
 * sin pérdida visual.
 *
 * Para evitar inconsistencias entre pantallas, las únicas dimensiones
 * permitidas se exponen vía [BeOnLogoSize]. La proporción del recurso
 * original es 527:173 y se respeta forzando solo la altura.
 */
@Composable
fun BeOnLogo(
    modifier: Modifier = Modifier,
    variant: BeOnLogoVariant = BeOnLogoVariant.Color,
    size: BeOnLogoSize = BeOnLogoSize.Medium,
) {
    Image(
        painter = painterResource(id = variant.drawableRes),
        contentDescription = "BeOn",
        contentScale = ContentScale.Fit,
        modifier = modifier.height(size.height),
    )
}

/**
 * Variante del logo lockup expuesta por el DS.
 *
 * - [Color]: full color sobre fondo oscuro (uso por defecto).
 * - [Mono]: monocromática blanca para superposiciones de baja contraste.
 * - [Framed]: variante con marco/fondo, para splash y onboarding.
 * - [On]: con indicador "ON" — usada en estados activos.
 */
enum class BeOnLogoVariant(@DrawableRes internal val drawableRes: Int) {
    Color(R.drawable.beon_logo),
    Mono(R.drawable.beon_logo_mono),
    Framed(R.drawable.beon_logo_framed),
    On(R.drawable.beon_logo_on),
}

/**
 * Escala vertical del logo. La proporción horizontal se calcula automáticamente.
 */
enum class BeOnLogoSize(internal val height: Dp) {
    Small(20.dp),
    Medium(32.dp),
    Large(56.dp),
    XLarge(96.dp),
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun BeOnLogoPreview() {
    BeOnTheme {
        Box(
            modifier = Modifier
                .background(BeOnTheme.colors.background.primary)
                .padding(BeOnTheme.spacing.md),
        ) {
            BeOnLogo(size = BeOnLogoSize.Large)
        }
    }
}
