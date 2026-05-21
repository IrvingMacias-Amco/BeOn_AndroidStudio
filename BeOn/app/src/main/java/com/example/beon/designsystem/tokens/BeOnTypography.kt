package com.example.beon.designsystem.tokens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.example.beon.R

/**
 * Typography tokens for the BeOn design system.
 *
 * Mirrors `design-tokens/typography.json`. Inter is loaded on-demand via
 * the Google Fonts downloadable-fonts API, so no font binaries are
 * shipped inside the APK.
 *
 * Each text style includes the full set of properties from the Figma
 * token (size, weight, line-height, letter-spacing).
 */
@Immutable
data class BeOnTypography(
    val h1: TextStyle,
    val h2: TextStyle,
    val h3: TextStyle,
    val h4: TextStyle,
    val bodyLarge: TextStyle,
    val bodyMedium: TextStyle,
    val bodySmall: TextStyle,
    val captionLarge: TextStyle,
    val captionSmall: TextStyle,
    val captionTiny: TextStyle,
    /** Standalone button label style — uppercase, semibold. */
    val buttonLabel: TextStyle,
)

private val GoogleFontsProvider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs,
)

private val InterFont = GoogleFont("Inter")

private val InterFontFamily = FontFamily(
    Font(googleFont = InterFont, fontProvider = GoogleFontsProvider, weight = FontWeight.Normal),
    Font(googleFont = InterFont, fontProvider = GoogleFontsProvider, weight = FontWeight.Medium),
    Font(googleFont = InterFont, fontProvider = GoogleFontsProvider, weight = FontWeight.SemiBold),
    Font(googleFont = InterFont, fontProvider = GoogleFontsProvider, weight = FontWeight.Bold),
    Font(googleFont = InterFont, fontProvider = GoogleFontsProvider, weight = FontWeight.Black),
)

@Composable
internal fun beOnDefaultTypography(): BeOnTypography {
    return BeOnTypography(
        h1 = TextStyle(
            fontFamily = InterFontFamily,
            fontSize = 48.sp,
            lineHeight = 58.sp, // 1.2 * 48
            fontWeight = FontWeight.Black,
            letterSpacing = (-2.4).sp,
        ),
        h2 = TextStyle(
            fontFamily = InterFontFamily,
            fontSize = 30.sp,
            lineHeight = 38.sp, // ≈ 1.27 * 30
            fontWeight = FontWeight.Bold,
            letterSpacing = (-0.75).sp,
        ),
        h3 = TextStyle(
            fontFamily = InterFontFamily,
            fontSize = 24.sp,
            lineHeight = 29.sp, // 1.2 * 24
            fontWeight = FontWeight.Bold,
        ),
        h4 = TextStyle(
            fontFamily = InterFontFamily,
            fontSize = 20.sp,
            lineHeight = 30.sp, // 1.5 * 20
            fontWeight = FontWeight.SemiBold,
        ),
        bodyLarge = TextStyle(
            fontFamily = InterFontFamily,
            fontSize = 18.sp,
            lineHeight = 27.sp,
            fontWeight = FontWeight.Medium,
        ),
        bodyMedium = TextStyle(
            fontFamily = InterFontFamily,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.Normal,
        ),
        bodySmall = TextStyle(
            fontFamily = InterFontFamily,
            fontSize = 14.sp,
            lineHeight = 21.sp,
            fontWeight = FontWeight.Normal,
        ),
        captionLarge = TextStyle(
            fontFamily = InterFontFamily,
            fontSize = 14.sp,
            lineHeight = 21.sp,
            fontWeight = FontWeight.Medium,
        ),
        captionSmall = TextStyle(
            fontFamily = InterFontFamily,
            fontSize = 12.sp,
            lineHeight = 18.sp,
            fontWeight = FontWeight.Normal,
        ),
        captionTiny = TextStyle(
            fontFamily = InterFontFamily,
            fontSize = 10.sp,
            lineHeight = 15.sp,
            fontWeight = FontWeight.Normal,
        ),
        buttonLabel = TextStyle(
            fontFamily = InterFontFamily,
            fontSize = 14.sp,
            lineHeight = 21.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 0.5.sp,
        ),
    )
}
