package com.example.beon.feature.player

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Forward10
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Replay10
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.outlined.Cast
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.beon.designsystem.theme.BeOnTheme
import kotlin.math.max

private val PlayButtonGlow = Color(0x6622C55E)
private val ChromeButtonBackground = Color(0x99000000)
private val SkipButtonBorder = Color.White.copy(alpha = 0.35f)

@Composable
fun PlayerScreen(
    uiState: PlayerUiState,
    onBack: () -> Unit,
    onRecommendationClick: (String) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    var controlsVisible by remember { mutableStateOf(true) }
    var isPlaying by remember { mutableStateOf(true) }
    var positionMs by remember { mutableLongStateOf(0L) }
    var durationMs by remember { mutableLongStateOf(0L) }
    var seekRequestMs by remember { mutableStateOf<Long?>(null) }
    val menuState = rememberPlayerMenuState()

    BackHandler(onBack = onBack)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black),
    ) {
        when {
            uiState.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = BeOnTheme.colors.accent.primary,
                )
            }
            else -> {
                PlayerBackdrop(
                    posterUrl = uiState.posterUrl,
                    streamUrl = uiState.streamUrl,
                    isPlaying = isPlaying,
                    playbackSpeed = menuState.playbackSpeed,
                    onIsPlayingChanged = { isPlaying = it },
                    onPositionChanged = { positionMs = it },
                    onDurationChanged = { durationMs = it },
                    seekRequestMs = seekRequestMs,
                    onSeekHandled = { seekRequestMs = null },
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(BeOnTheme.gradients.playerTop)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = { controlsVisible = !controlsVisible },
                        ),
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(BeOnTheme.gradients.playerBottom)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = { controlsVisible = !controlsVisible },
                        ),
                )

                AnimatedVisibility(
                    visible = controlsVisible,
                    enter = fadeIn(),
                    exit = fadeOut(),
                ) {
                    val skipBack: () -> Unit = {
                        seekRequestMs = max(0L, positionMs - 10_000L)
                    }
                    val skipForward: () -> Unit = {
                        val maxPosition = if (durationMs > 0) durationMs else positionMs + 10_000L
                        seekRequestMs = (positionMs + 10_000L).coerceAtMost(maxPosition)
                    }
                    PlayerControlsOverlay(
                        title = uiState.title,
                        recommendations = uiState.recommendations,
                        menuState = menuState,
                        isPlaying = isPlaying,
                        positionMs = positionMs,
                        durationMs = durationMs,
                        onBack = onBack,
                        onTogglePlay = { isPlaying = !isPlaying },
                        onSkipBack = skipBack,
                        onSkipForward = skipForward,
                        onSeekTo = { target ->
                            val clamped = if (durationMs > 0) {
                                target.coerceIn(0L, durationMs)
                            } else {
                                target.coerceAtLeast(0L)
                            }
                            seekRequestMs = clamped
                        },
                        onRecommendationClick = onRecommendationClick,
                    )
                }

                if (uiState.errorMessage != null && uiState.streamUrl == null) {
                    Text(
                        text = "Vista previa sin streaming. ${uiState.errorMessage}",
                        color = Color.White.copy(alpha = 0.7f),
                        fontSize = 12.sp,
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = 56.dp),
                    )
                }
            }
        }
    }
}

@Composable
private fun PlayerBackdrop(
    posterUrl: String,
    streamUrl: String?,
    isPlaying: Boolean,
    playbackSpeed: Float,
    onIsPlayingChanged: (Boolean) -> Unit,
    onPositionChanged: (Long) -> Unit,
    onDurationChanged: (Long) -> Unit,
    seekRequestMs: Long?,
    onSeekHandled: () -> Unit,
) {
    if (!streamUrl.isNullOrBlank()) {
        key(streamUrl) {
            PlayerVideoLayer(
                streamUrl = streamUrl,
                isPlaying = isPlaying,
                playbackSpeed = playbackSpeed,
                onIsPlayingChanged = onIsPlayingChanged,
                onPositionChanged = onPositionChanged,
                onDurationChanged = onDurationChanged,
                seekRequestMs = seekRequestMs,
                onSeekHandled = onSeekHandled,
            )
        }
    } else if (posterUrl.isNotBlank()) {
        AsyncImage(
            model = posterUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@Composable
private fun PlayerControlsOverlay(
    title: String,
    recommendations: List<PlayerRecommendation>,
    menuState: PlayerMenuStateHolder,
    isPlaying: Boolean,
    positionMs: Long,
    durationMs: Long,
    onBack: () -> Unit,
    onTogglePlay: () -> Unit,
    onSkipBack: () -> Unit,
    onSkipForward: () -> Unit,
    onSeekTo: (Long) -> Unit,
    onRecommendationClick: (String) -> Unit,
) {
    val accent = BeOnTheme.colors.accent.primary

    Box(modifier = Modifier.fillMaxSize()) {
        PlayerTopBar(
            onBack = onBack,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(horizontal = 20.dp, vertical = 16.dp),
        )

        PlayerCenterControls(
            isPlaying = isPlaying,
            onTogglePlay = onTogglePlay,
            onSkipBack = onSkipBack,
            onSkipForward = onSkipForward,
            modifier = Modifier.align(Alignment.Center),
        )

        if (recommendations.isNotEmpty()) {
            PlayerRecommendationsSection(
                recommendations = recommendations,
                onRecommendationClick = onRecommendationClick,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 20.dp)
                    .fillMaxHeight(0.55f),
            )
        }

        // Bottom column con altura FIJA — no crece cuando se abre un menu,
        // asi no desacomoda el slider ni el skip button.
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
        ) {
            PlayerSkipButton(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(bottom = 12.dp),
            )
            PlayerProgressBar(
                positionMs = positionMs,
                durationMs = durationMs,
                onSeekTo = onSeekTo,
            )
            Spacer(modifier = Modifier.height(10.dp))
            PlayerBottomBar(
                isPlaying = isPlaying,
                positionMs = positionMs,
                durationMs = durationMs,
                languagesActive = menuState.activePanel == PlayerMenuPanel.Languages,
                settingsActive = menuState.activePanel == PlayerMenuPanel.Settings,
                accentColor = accent,
                onTogglePlay = onTogglePlay,
                onSkipBack = onSkipBack,
                onSkipForward = onSkipForward,
                onLanguagesClick = { menuState.toggleLanguages() },
                onSettingsClick = { menuState.toggleSettings() },
            )
        }

        // Menu flotante — anclado a BottomEnd del overlay completo, fuera del
        // Column de controles. Asi se dibuja ENCIMA de los controles sin
        // afectar su layout (es decir: no empuja el slider, no mueve el skip).
        when (menuState.activePanel) {
            PlayerMenuPanel.Languages -> {
                PlayerLanguagesMenu(
                    menuState = menuState,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 20.dp, bottom = 86.dp),
                )
            }
            PlayerMenuPanel.Settings -> {
                PlayerSettingsMenu(
                    menuState = menuState,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 20.dp, bottom = 86.dp),
                )
            }
            PlayerMenuPanel.None -> Unit
        }
    }
}

@Composable
private fun PlayerTopBar(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PlayerChromeIconButton(
            icon = Icons.Filled.Close,
            contentDescription = "Cerrar",
            onClick = onBack,
        )
        PlayerChromeIconButton(
            icon = Icons.Outlined.Cast,
            contentDescription = "Transmitir",
            onClick = {},
        )
    }
}

@Composable
private fun PlayerCenterControls(
    isPlaying: Boolean,
    onTogglePlay: () -> Unit,
    onSkipBack: () -> Unit,
    onSkipForward: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(28.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PlayerSkipTenButton(
            icon = Icons.Filled.Replay10,
            contentDescription = "Retroceder 10 segundos",
            onClick = onSkipBack,
        )
        PlayerMainPlayButton(
            isPlaying = isPlaying,
            onClick = onTogglePlay,
        )
        PlayerSkipTenButton(
            icon = Icons.Filled.Forward10,
            contentDescription = "Avanzar 10 segundos",
            onClick = onSkipForward,
        )
    }
}

@Composable
private fun PlayerSkipTenButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(64.dp)
            .clip(CircleShape)
            .border(1.5.dp, SkipButtonBorder, CircleShape)
            .background(ChromeButtonBackground)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = Color.White,
            modifier = Modifier.size(36.dp),
        )
    }
}

@Composable
private fun PlayerMainPlayButton(
    isPlaying: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(88.dp)
            .shadow(
                elevation = 18.dp,
                shape = CircleShape,
                spotColor = PlayButtonGlow,
                ambientColor = PlayButtonGlow,
            )
            .clip(CircleShape)
            .background(BeOnTheme.gradients.buttonPlay)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = if (isPlaying) Icons.Filled.Pause else Icons.Filled.PlayArrow,
            contentDescription = if (isPlaying) "Pausar" else "Reproducir",
            tint = Color.Black,
            modifier = Modifier.size(40.dp),
        )
    }
}

@Composable
private fun PlayerRecommendationsSection(
    recommendations: List<PlayerRecommendation>,
    onRecommendationClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.width(220.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Text(
            text = "Te puede gustar...",
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(end = 8.dp),
        ) {
            items(recommendations, key = { it.id }) { item ->
                PlayerRecommendationCard(
                    item = item,
                    onClick = { onRecommendationClick(item.id) },
                )
            }
        }
    }
}

@Composable
private fun PlayerRecommendationCard(
    item: PlayerRecommendation,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .width(110.dp)
            .height(150.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onClick),
    ) {
        AsyncImage(
            model = item.imageUrl,
            contentDescription = item.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colorStops = arrayOf(
                            0.5f to Color.Transparent,
                            1.0f to Color(0xE6000000),
                        ),
                    ),
                ),
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(8.dp),
        ) {
            Text(
                text = item.title,
                color = Color.White,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2,
            )
            Text(
                text = item.year,
                color = Color.White.copy(alpha = 0.75f),
                fontSize = 10.sp,
            )
        }
    }
}

@Composable
private fun PlayerSkipButton(
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Saltar",
        color = Color.White,
        fontSize = 13.sp,
        fontWeight = FontWeight.Medium,
        modifier = modifier
            .clip(RoundedCornerShape(percent = 50))
            .background(ChromeButtonBackground)
            .clickable(onClick = {})
            .padding(horizontal = 18.dp, vertical = 8.dp),
    )
}

/**
 * Barra de tiempo arrastrable.
 *
 * - Mientras el usuario arrastra (o tap), `scrubFraction` toma control y el slider
 *   muestra la posición tentativa, sin tocar al ExoPlayer todavía.
 * - Al soltar (`onValueChangeFinished`), convierte la fracción a milisegundos y
 *   emite `onSeekTo` para que `PlayerScreen` mande el seek al player.
 * - Cuando el usuario no está arrastrando, el slider sigue la posición real
 *   reportada por ExoPlayer (`positionMs`).
 */
@Composable
private fun PlayerProgressBar(
    positionMs: Long,
    durationMs: Long,
    onSeekTo: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    val hasDuration = durationMs > 0L
    var scrubFraction by remember { mutableStateOf<Float?>(null) }
    val livePosition = if (hasDuration) {
        (positionMs.toFloat() / durationMs.toFloat()).coerceIn(0f, 1f)
    } else {
        0f
    }
    val sliderValue = scrubFraction ?: livePosition

    Slider(
        value = sliderValue,
        onValueChange = { scrubFraction = it.coerceIn(0f, 1f) },
        onValueChangeFinished = {
            val target = scrubFraction
            scrubFraction = null
            if (target != null && hasDuration) {
                onSeekTo((target * durationMs).toLong())
            }
        },
        enabled = hasDuration,
        valueRange = 0f..1f,
        colors = SliderDefaults.colors(
            thumbColor = Color.White,
            activeTrackColor = Color.White,
            inactiveTrackColor = Color.White.copy(alpha = 0.25f),
            disabledThumbColor = Color.White.copy(alpha = 0.4f),
            disabledActiveTrackColor = Color.White.copy(alpha = 0.4f),
            disabledInactiveTrackColor = Color.White.copy(alpha = 0.2f),
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(24.dp),
    )
}

@Composable
private fun PlayerBottomBar(
    isPlaying: Boolean,
    positionMs: Long,
    durationMs: Long,
    languagesActive: Boolean,
    settingsActive: Boolean,
    accentColor: Color,
    onTogglePlay: () -> Unit,
    onSkipBack: () -> Unit,
    onSkipForward: () -> Unit,
    onLanguagesClick: () -> Unit,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            Icon(
                imageVector = if (isPlaying) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(22.dp)
                    .clickable(onClick = onTogglePlay),
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = "Retroceder 10 segundos",
                tint = Color.White,
                modifier = Modifier
                    .size(22.dp)
                    .clickable(onClick = onSkipBack),
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Avanzar 10 segundos",
                tint = Color.White,
                modifier = Modifier
                    .size(22.dp)
                    .clickable(onClick = onSkipForward),
            )
            Icon(
                imageVector = Icons.Filled.VolumeUp,
                contentDescription = "Volumen",
                tint = Color.White,
                modifier = Modifier
                    .size(22.dp)
                    .clickable(onClick = {}),
            )
            Text(
                text = "${formatPlaybackTime(positionMs)} / ${formatPlaybackTime(durationMs)}",
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Icon(
                imageVector = Icons.Outlined.Cast,
                contentDescription = "Transmitir",
                tint = Color.White,
                modifier = Modifier
                    .size(20.dp)
                    .clickable(onClick = {}),
            )
            Text(
                text = "Idiomas",
                color = if (languagesActive) accentColor else Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(
                        if (languagesActive) accentColor.copy(alpha = 0.25f) else Color.Transparent,
                    )
                    .clickable(onClick = onLanguagesClick)
                    .padding(horizontal = 10.dp, vertical = 6.dp),
            )
            Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = "Opciones",
                tint = if (settingsActive) accentColor else Color.White,
                modifier = Modifier
                    .size(20.dp)
                    .clickable(onClick = onSettingsClick),
            )
        }
    }

}

@Composable
private fun PlayerChromeIconButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(ChromeButtonBackground)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = Color.White,
            modifier = Modifier.size(22.dp),
        )
    }
}

private fun formatPlaybackTime(ms: Long): String {
    val totalSeconds = (ms / 1000).coerceAtLeast(0)
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return "%d:%02d".format(minutes, seconds)
}

@Preview(widthDp = 844, heightDp = 390, showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun PlayerScreenPreview() {
    BeOnTheme {
        PlayerScreen(
            uiState = PlayerUiState(
                isLoading = false,
                title = "Marty Supremo",
                posterUrl = "",
                streamUrl = null,
            ),
            onBack = {},
        )
    }
}
