package com.example.beon.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.beon.designsystem.theme.BeOnTheme

/**
 * Wordmark tipográfico de BeOn para navegación cuando los PNG del logo
 * no están disponibles en el APK.
 */
@Composable
fun BeOnWordmark(
    modifier: Modifier = Modifier,
    height: Dp = 28.dp,
) {
    val fontSize = (height.value * 0.78f).sp

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "BE",
            color = BeOnTheme.colors.text.primary,
            fontSize = fontSize,
            fontWeight = FontWeight.Black,
            letterSpacing = (-0.5).sp,
        )
        Box(
            modifier = Modifier
                .padding(horizontal = 1.dp)
                .size(height * 0.72f)
                .clip(CircleShape)
                .background(BeOnTheme.colors.accent.primary),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "O",
                color = BeOnTheme.colors.text.onAccent,
                fontSize = (fontSize.value * 0.65f).sp,
                fontWeight = FontWeight.Black,
            )
        }
        Text(
            text = "n",
            color = BeOnTheme.colors.text.primary,
            fontSize = fontSize,
            fontWeight = FontWeight.Black,
            letterSpacing = (-0.5).sp,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun BeOnWordmarkPreview() {
    BeOnTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            BeOnWordmark()
        }
    }
}
