package com.example.beon.feature.contentdetail

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.outlined.Cast
import androidx.compose.material3.Icon
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.annotation.DrawableRes
import coil.compose.AsyncImage
import com.example.beon.R
import com.example.beon.designsystem.components.BeOnLogo
import com.example.beon.designsystem.components.BeOnLogoSize
import com.example.beon.designsystem.theme.BeOnTheme
import com.example.beon.feature.home.ContentItem

private val PlaceholderCardColor = Color(0xFFF2F2F2)
private val ImdbYellow = Color(0xFFFDC700)
private val ActionButtonBorder = Color.White.copy(alpha = 0.4f)
private val PlayButtonGlow = Color(0x6622C55E)

@Composable
fun ContentDetailScreen(
    content: ContentDetail?,
    relatedContent: List<ContentItem> = emptyList(),
    isLoading: Boolean,
    errorMessage: String?,
    onBack: () -> Unit,
    onPlayClick: () -> Unit = {},
    onContentClick: (String) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    var selectedTab by remember { mutableStateOf(ContentDetailTab.Related) }

    BackHandler(onBack = onBack)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BeOnTheme.colors.background.primary)
            .windowInsetsPadding(WindowInsets.statusBars),
    ) {
        when {
            isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = BeOnTheme.colors.accent.primary,
                )
            }
            content != null -> {
                ContentDetailBody(
                    content = content,
                    relatedContent = relatedContent,
                    selectedTab = selectedTab,
                    onTabSelected = { selectedTab = it },
                    onPlayClick = onPlayClick,
                    onContentClick = onContentClick,
                )
            }
            else -> {
                Text(
                    text = errorMessage ?: "No se pudo cargar el contenido.",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(BeOnTheme.spacing.md),
                    color = BeOnTheme.colors.text.secondary,
                )
            }
        }
    }
}

@Composable
private fun ContentDetailBody(
    content: ContentDetail,
    relatedContent: List<ContentItem>,
    selectedTab: ContentDetailTab,
    onTabSelected: (ContentDetailTab) -> Unit,
    onPlayClick: () -> Unit,
    onContentClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
    ) {
        item {
            ContentDetailTopBar()
        }

        item {
            ContentDetailHero(imageUrl = content.imageUrl, title = content.title)
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = BeOnTheme.safeArea.mobile),
            ) {
                Spacer(modifier = Modifier.height(BeOnTheme.spacing.md))

                Text(
                    text = content.title,
                    style = BeOnTheme.typography.h3,
                    color = BeOnTheme.colors.text.primary,
                )

                Spacer(modifier = Modifier.height(BeOnTheme.spacing.sm))

                ContentMetadataRow(content = content)

                content.ranking?.let { ranking ->
                    Spacer(modifier = Modifier.height(BeOnTheme.spacing.sm))
                    ContentRankingBadge(
                        position = ranking.position,
                        category = ranking.category,
                    )
                }

                Spacer(modifier = Modifier.height(BeOnTheme.spacing.sm))

                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    content.genres.forEach { genre ->
                        ContentGenrePill(genre = genre)
                    }
                }

                Spacer(modifier = Modifier.height(BeOnTheme.spacing.md))

                Text(
                    text = content.synopsis,
                    style = BeOnTheme.typography.bodySmall,
                    color = BeOnTheme.colors.text.secondary,
                    lineHeight = 22.sp,
                )

                if (content.cast.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(BeOnTheme.spacing.md))
                    Text(
                        text = "Elenco",
                        style = BeOnTheme.typography.captionLarge,
                        color = BeOnTheme.colors.text.secondary,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = content.cast.joinToString(", "),
                        style = BeOnTheme.typography.bodyMedium,
                        color = BeOnTheme.colors.text.primary,
                        lineHeight = 24.sp,
                    )
                }

                Spacer(modifier = Modifier.height(BeOnTheme.spacing.lg))

                ContentPlayButton(
                    onClick = onPlayClick,
                    modifier = Modifier.fillMaxWidth(),
                )

                Spacer(modifier = Modifier.height(BeOnTheme.spacing.lg))

                ContentActionRow()

                Spacer(modifier = Modifier.height(BeOnTheme.spacing.lg))
            }
        }

        item {
            ContentDetailTabs(
                selectedTab = selectedTab,
                onTabSelected = onTabSelected,
            )
        }

        item {
            when (selectedTab) {
                ContentDetailTab.Related -> {
                    RelatedContentRow(
                        items = relatedContent,
                        onContentClick = onContentClick,
                    )
                }
                ContentDetailTab.Trailer -> {
                    ContentTabPlaceholder(text = "Tráiler disponible próximamente.")
                }
                ContentDetailTab.Details -> {
                    ContentTabPlaceholder(text = "Detalles adicionales disponibles próximamente.")
                }
            }
        }

        item {
            ContentDetailFooter()
        }
    }
}

@Composable
private fun ContentDetailTopBar(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = BeOnTheme.safeArea.mobile,
                vertical = BeOnTheme.spacing.sm,
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BeOnLogo(size = BeOnLogoSize.Nav)
        Icon(
            imageVector = Icons.Outlined.Cast,
            contentDescription = "Transmitir",
            tint = BeOnTheme.colors.text.primary,
            modifier = Modifier.size(22.dp),
        )
    }
}

@Composable
private fun ContentDetailHero(
    imageUrl: String,
    title: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(240.dp),
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colorStops = arrayOf(
                            0.0f to Color(0x33000000),
                            0.7f to Color(0x99000000),
                            1.0f to BeOnTheme.colors.background.primary,
                        ),
                    ),
                ),
        )
    }
}

@Composable
private fun ContentMetadataRow(
    content: ContentDetail,
    modifier: Modifier = Modifier,
) {
    val colors = BeOnTheme.colors

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        MetadataText(content.year)
        MetadataDot()
        MetadataText(content.duration)
        content.imdbRating?.let { rating ->
            MetadataDot()
            Text(
                text = "IMDb",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = ImdbYellow,
            )
            Text(
                text = rating,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = colors.text.secondary,
            )
        }
        MetadataDot()
        Text(
            text = content.rating,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            color = colors.text.secondary,
            modifier = Modifier
                .border(1.dp, colors.border.strong, BeOnTheme.shapes.sm)
                .padding(horizontal = 6.dp, vertical = 2.dp),
        )
    }
}

@Composable
private fun MetadataText(text: String) {
    Text(
        text = text,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        color = BeOnTheme.colors.text.secondary,
    )
}

@Composable
private fun MetadataDot() {
    Text(
        text = "•",
        fontSize = 12.sp,
        color = BeOnTheme.colors.border.medium,
    )
}

@Composable
private fun ContentRankingBadge(
    position: Int,
    category: String,
    modifier: Modifier = Modifier,
) {
    val colors = BeOnTheme.colors

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Box(
            modifier = Modifier
                .size(22.dp)
                .border(1.dp, colors.accent.primary.copy(alpha = 0.4f), CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = Icons.Filled.TrendingUp,
                contentDescription = null,
                tint = colors.accent.primary,
                modifier = Modifier.size(14.dp),
            )
        }
        Text(
            text = "Número $position en $category",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = colors.accent.primary,
        )
    }
}

@Composable
private fun ContentGenrePill(
    genre: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = genre,
        modifier = modifier
            .clip(BeOnTheme.shapes.full)
            .background(Color(0x66333333))
            .padding(horizontal = 10.dp, vertical = 4.dp),
        fontSize = 11.sp,
        color = BeOnTheme.colors.text.primary,
    )
}

@Composable
private fun ContentPlayButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val shape = RoundedCornerShape(percent = 50)

    Row(
        modifier = modifier
            .height(60.dp)
            .shadow(
                elevation = 15.dp,
                shape = shape,
                spotColor = PlayButtonGlow,
                ambientColor = PlayButtonGlow,
            )
            .clip(shape)
            .background(BeOnTheme.gradients.buttonPlay)
            .clickable(onClick = onClick)
            .padding(horizontal = 32.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = Icons.Filled.PlayArrow,
            contentDescription = null,
            tint = BeOnTheme.colors.text.onAccent,
            modifier = Modifier.size(22.dp),
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = "REPRODUCIR",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = BeOnTheme.colors.text.onAccent,
            letterSpacing = 0.5.sp,
        )
    }
}

@Composable
private fun ContentActionRow(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        ContentActionItem(
            label = "Mi Lista",
            iconRes = R.drawable.ic_beon_plus,
        )
        ContentActionItem(
            label = "Me gusta",
            iconRes = R.drawable.ic_beon_thumb_up,
        )
        ContentActionItem(
            label = "Compartir",
            iconRes = R.drawable.ic_beon_share,
        )
        ContentActionItem(
            label = "Descargar",
            iconRes = R.drawable.ic_beon_download,
        )
    }
}

@Composable
private fun ContentActionItem(
    label: String,
    @DrawableRes iconRes: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .border(2.dp, ActionButtonBorder, CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                painter = painterResource(iconRes),
                contentDescription = label,
                tint = BeOnTheme.colors.text.primary,
                modifier = Modifier.size(24.dp),
            )
        }
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            color = BeOnTheme.colors.text.secondary,
        )
    }
}

@Composable
private fun ContentDetailTabs(
    selectedTab: ContentDetailTab,
    onTabSelected: (ContentDetailTab) -> Unit,
    modifier: Modifier = Modifier,
) {
    val tabs = ContentDetailTab.entries
    val selectedIndex = tabs.indexOf(selectedTab)
    val accent = BeOnTheme.colors.accent.primary

    ScrollableTabRow(
        selectedTabIndex = selectedIndex,
        modifier = modifier.fillMaxWidth(),
        containerColor = BeOnTheme.colors.background.primary,
        contentColor = BeOnTheme.colors.text.primary,
        edgePadding = BeOnTheme.safeArea.mobile,
        indicator = { tabPositions ->
            if (selectedIndex in tabPositions.indices) {
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedIndex]),
                    color = accent,
                    height = 2.dp,
                )
            }
        },
        divider = {},
    ) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                selected = selectedIndex == index,
                onClick = { onTabSelected(tab) },
                text = {
                    Text(
                        text = tab.label,
                        fontSize = 14.sp,
                        fontWeight = if (selectedIndex == index) FontWeight.SemiBold else FontWeight.Medium,
                        color = if (selectedIndex == index) {
                            accent
                        } else {
                            BeOnTheme.colors.text.secondary
                        },
                    )
                },
            )
        }
    }
}

private val ContentDetailTab.label: String
    get() = when (this) {
        ContentDetailTab.Related -> "Relacionado"
        ContentDetailTab.Trailer -> "Tráiler"
        ContentDetailTab.Details -> "Detalles"
    }

@Composable
private fun RelatedContentRow(
    items: List<ContentItem>,
    onContentClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (items.isEmpty()) {
        ContentTabPlaceholder(
            text = "No hay contenido relacionado disponible.",
            modifier = modifier.padding(top = BeOnTheme.spacing.md),
        )
        return
    }

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = BeOnTheme.spacing.md, bottom = BeOnTheme.spacing.lg),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(
            horizontal = BeOnTheme.safeArea.mobile,
        ),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(items, key = { it.id }) { item ->
            Box(
                modifier = Modifier
                    .width(120.dp)
                    .aspectRatio(2f / 3f)
                    .clip(BeOnTheme.shapes.md)
                    .clickable { onContentClick(item.id) },
            ) {
                if (!item.imageUrl.isNullOrBlank()) {
                    AsyncImage(
                        model = item.imageUrl,
                        contentDescription = item.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize(),
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(PlaceholderCardColor),
                    )
                }
                if (item.title.isNotBlank()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colorStops = arrayOf(
                                        0.55f to Color.Transparent,
                                        1.0f to Color(0xE6000000),
                                    ),
                                ),
                            ),
                    )
                    Text(
                        text = item.title,
                        color = BeOnTheme.colors.text.primary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 2,
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(8.dp),
                    )
                }
            }
        }
    }
}

@Composable
private fun ContentTabPlaceholder(
    text: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = BeOnTheme.safeArea.mobile,
                vertical = BeOnTheme.spacing.xl,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = BeOnTheme.typography.bodySmall,
            color = BeOnTheme.colors.text.secondary,
        )
    }
}

@Composable
private fun ContentDetailFooter(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = BeOnTheme.spacing.xl),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(BeOnTheme.spacing.lg),
    ) {
        BeOnLogo(size = BeOnLogoSize.Medium)
        Text(
            text = "Términos y Aviso de Privacidad • Comentarios • Ayuda",
            style = BeOnTheme.typography.captionSmall,
            color = BeOnTheme.colors.text.secondary,
        )
        Spacer(modifier = Modifier.height(BeOnTheme.spacing.md))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000, heightDp = 1200)
@Composable
private fun ContentDetailScreenPreview() {
    BeOnTheme {
        ContentDetailScreen(
            content = ContentDetail(
                id = "preview",
                title = "Marty Supremo",
                synopsis = "Sinopsis de ejemplo.",
                imageUrl = "",
                year = "2025",
                duration = "2h 29min",
                imdbRating = "7.5",
                rating = "PG-13",
                genres = listOf("Comedia", "Deporte"),
                cast = listOf("Timothée Chalamet"),
            ),
            isLoading = false,
            errorMessage = null,
            onBack = {},
        )
    }
}
