package com.example.beon.feature.player

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.beon.designsystem.theme.BeOnTheme

internal enum class PlayerMenuPanel {
    None,
    Languages,
    Settings,
}

internal enum class LanguageTab {
    Subtitles,
    Audio,
}

internal enum class SettingsTab {
    Quality,
    Speed,
}

@Composable
internal fun rememberPlayerMenuState(): PlayerMenuStateHolder {
    val activePanelState = remember { mutableStateOf(PlayerMenuPanel.None) }
    val languageTabState = remember { mutableStateOf(LanguageTab.Subtitles) }
    val settingsTabState = remember { mutableStateOf(SettingsTab.Quality) }
    val subtitleTrackState = remember { mutableStateOf("off") }
    val audioTrackState = remember { mutableStateOf("es") }
    val qualityState = remember { mutableStateOf("auto") }
    val playbackSpeedState = remember { mutableFloatStateOf(1f) }

    return remember {
        PlayerMenuStateHolder(
            activePanelState = activePanelState,
            languageTabState = languageTabState,
            settingsTabState = settingsTabState,
            subtitleTrackState = subtitleTrackState,
            audioTrackState = audioTrackState,
            qualityState = qualityState,
            playbackSpeedState = playbackSpeedState,
        )
    }
}

@Stable
internal class PlayerMenuStateHolder(
    private val activePanelState: MutableState<PlayerMenuPanel>,
    private val languageTabState: MutableState<LanguageTab>,
    private val settingsTabState: MutableState<SettingsTab>,
    private val subtitleTrackState: MutableState<String>,
    private val audioTrackState: MutableState<String>,
    private val qualityState: MutableState<String>,
    private val playbackSpeedState: MutableFloatState,
) {
    var activePanel: PlayerMenuPanel
        get() = activePanelState.value
        set(value) { activePanelState.value = value }

    var languageTab: LanguageTab
        get() = languageTabState.value
        set(value) { languageTabState.value = value }

    var settingsTab: SettingsTab
        get() = settingsTabState.value
        set(value) { settingsTabState.value = value }

    var subtitleTrack: String
        get() = subtitleTrackState.value
        set(value) { subtitleTrackState.value = value }

    var audioTrack: String
        get() = audioTrackState.value
        set(value) { audioTrackState.value = value }

    var quality: String
        get() = qualityState.value
        set(value) { qualityState.value = value }

    var playbackSpeed: Float
        get() = playbackSpeedState.floatValue
        set(value) { playbackSpeedState.floatValue = value }

    fun toggleLanguages() {
        activePanel = if (activePanel == PlayerMenuPanel.Languages) {
            PlayerMenuPanel.None
        } else {
            PlayerMenuPanel.Languages
        }
    }

    fun toggleSettings() {
        activePanel = if (activePanel == PlayerMenuPanel.Settings) {
            PlayerMenuPanel.None
        } else {
            PlayerMenuPanel.Settings
        }
    }

    fun dismiss() {
        activePanel = PlayerMenuPanel.None
    }
}

@Composable
internal fun PlayerLanguagesMenu(
    menuState: PlayerMenuStateHolder,
    modifier: Modifier = Modifier,
) {
    PlayerMenuPopup(
        modifier = modifier,
        tabs = listOf("Subtítulos", "Audio"),
        selectedTabIndex = if (menuState.languageTab == LanguageTab.Subtitles) 0 else 1,
        onTabSelected = { index ->
            menuState.languageTab = if (index == 0) LanguageTab.Subtitles else LanguageTab.Audio
        },
    ) {
        when (menuState.languageTab) {
            LanguageTab.Subtitles -> {
                PlayerMenuOption(
                    label = "Off",
                    selected = menuState.subtitleTrack == "off",
                    onClick = { menuState.subtitleTrack = "off" },
                )
                PlayerMenuOption(
                    label = "Español",
                    selected = menuState.subtitleTrack == "es",
                    onClick = { menuState.subtitleTrack = "es" },
                )
                PlayerMenuOption(
                    label = "Inglés",
                    selected = menuState.subtitleTrack == "en",
                    onClick = { menuState.subtitleTrack = "en" },
                )
                PlayerMenuOption(
                    label = "Portugués",
                    selected = menuState.subtitleTrack == "pt",
                    onClick = { menuState.subtitleTrack = "pt" },
                )
            }
            LanguageTab.Audio -> {
                PlayerMenuOption(
                    label = "Español",
                    selected = menuState.audioTrack == "es",
                    onClick = { menuState.audioTrack = "es" },
                )
                PlayerMenuOption(
                    label = "Inglés",
                    selected = menuState.audioTrack == "en",
                    onClick = { menuState.audioTrack = "en" },
                )
                PlayerMenuOption(
                    label = "Portugués",
                    selected = menuState.audioTrack == "pt",
                    onClick = { menuState.audioTrack = "pt" },
                )
                PlayerMenuOption(
                    label = "Original",
                    selected = menuState.audioTrack == "original",
                    onClick = { menuState.audioTrack = "original" },
                )
            }
        }
    }
}

@Composable
internal fun PlayerSettingsMenu(
    menuState: PlayerMenuStateHolder,
    modifier: Modifier = Modifier,
) {
    PlayerMenuPopup(
        modifier = modifier,
        tabs = listOf("Calidad", "Velocidad"),
        selectedTabIndex = if (menuState.settingsTab == SettingsTab.Quality) 0 else 1,
        onTabSelected = { index ->
            menuState.settingsTab = if (index == 0) SettingsTab.Quality else SettingsTab.Speed
        },
    ) {
        when (menuState.settingsTab) {
            SettingsTab.Quality -> {
                val qualityOptions = listOf(
                    "auto" to "Auto",
                    "1080" to "1080p",
                    "720" to "720p",
                    "480" to "480p",
                    "360" to "360p",
                )
                for ((id, label) in qualityOptions) {
                    key(id) {
                        PlayerMenuOption(
                            label = label,
                            selected = menuState.quality == id,
                            onClick = { menuState.quality = id },
                        )
                    }
                }
            }
            SettingsTab.Speed -> {
                val speedOptions = listOf(
                    0.5f to "0.5x",
                    0.75f to "0.75x",
                    1f to "Normal",
                    1.25f to "1.25x",
                    1.5f to "1.5x",
                    2f to "2x",
                )
                for ((speed, label) in speedOptions) {
                    key(speed) {
                        PlayerMenuOption(
                            label = label,
                            selected = menuState.playbackSpeed == speed,
                            onClick = { menuState.playbackSpeed = speed },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun PlayerMenuPopup(
    tabs: List<String>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val accent = BeOnTheme.colors.accent.primary
    val shape = RoundedCornerShape(12.dp)

    Column(
        modifier = modifier
            .width(200.dp)
            .clip(shape)
            .background(MenuPanelBackground)
            .padding(vertical = 8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            tabs.forEachIndexed { index, title ->
                val selected = index == selectedTabIndex
                Column(
                    modifier = Modifier
                        .clickable { onTabSelected(index) }
                        .padding(vertical = 6.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = title,
                        color = if (selected) accent else Color.White.copy(alpha = 0.7f),
                        fontSize = 13.sp,
                        fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Medium,
                    )
                    if (selected) {
                        Box(
                            modifier = Modifier
                                .padding(top = 4.dp)
                                .width(28.dp)
                                .height(2.dp)
                                .background(accent, RoundedCornerShape(1.dp)),
                        )
                    }
                }
            }
        }

        HorizontalDivider(
            color = Color.White.copy(alpha = 0.12f),
            modifier = Modifier.padding(vertical = 4.dp),
        )

        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
            content()
        }
    }
}

@Composable
private fun PlayerMenuOption(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val accent = BeOnTheme.colors.accent.primary
    val background = if (selected) accent.copy(alpha = 0.35f) else Color.Transparent

    Text(
        text = label,
        color = if (selected) accent else Color.White,
        fontSize = 14.sp,
        fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(background)
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 10.dp),
    )
}

private val MenuPanelBackground = Color(0xE6141414)
