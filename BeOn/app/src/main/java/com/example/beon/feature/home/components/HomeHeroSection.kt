package com.example.beon.feature.home.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.beon.designsystem.theme.BeOnTheme
import com.example.beon.feature.home.HeroSlide
import com.example.beon.feature.home.heroSlides

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeHeroSection(
    slides: List<HeroSlide> = heroSlides,
    onSlideClick: (String) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState(pageCount = { slides.size })

    BoxWithConstraints(modifier = modifier.fillMaxWidth()) {
        val heroHeight = (maxWidth * 0.72f).coerceIn(260.dp, 420.dp)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(heroHeight),
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize(),
            ) { page ->
                HeroSlideContent(
                    slide = slides[page],
                    pageCount = slides.size,
                    currentPage = pagerState.currentPage,
                    onClick = { onSlideClick(slides[page].id) },
                )
            }
        }
    }
}

@Composable
private fun HeroSlideContent(
    slide: HeroSlide,
    pageCount: Int,
    currentPage: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = BeOnTheme.colors

    Box(
        modifier = modifier
            .fillMaxSize()
            .clickable(onClick = onClick),
    ) {
        AsyncImage(
            model = slide.imageUrl,
            contentDescription = slide.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colorStops = arrayOf(
                            0.0f to Color(0x1A000000),
                            0.6f to Color(0x99000000),
                            1.0f to Color(0xF7000000),
                        ),
                    ),
                ),
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .padding(horizontal = BeOnTheme.safeArea.mobile)
                .padding(bottom = 16.dp),
        ) {
            Text(
                text = slide.title,
                style = BeOnTheme.typography.h3,
                color = colors.text.primary,
            )

            Spacer(modifier = Modifier.height(8.dp))

            HeroMetadataRow(
                year = slide.year,
                duration = slide.duration,
                rating = slide.rating,
            )

            slide.ranking?.let { ranking ->
                Spacer(modifier = Modifier.height(8.dp))
                HeroRankingBadge(
                    position = ranking.position,
                    category = ranking.category,
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                slide.genres.forEach { genre ->
                    HeroGenrePill(genre = genre)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = slide.description,
                style = BeOnTheme.typography.bodySmall,
                color = colors.text.secondary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 20.sp,
            )

            Spacer(modifier = Modifier.height(12.dp))

            HeroPageIndicators(
                pageCount = pageCount,
                currentPage = currentPage,
            )
        }
    }
}

@Composable
private fun HeroMetadataRow(
    year: String,
    duration: String,
    rating: String,
    modifier: Modifier = Modifier,
) {
    val colors = BeOnTheme.colors

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        MetadataText(year)
        MetadataDot()
        MetadataText(duration)
        MetadataDot()
        Text(
            text = rating,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            color = colors.text.secondary,
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = colors.border.strong,
                    shape = BeOnTheme.shapes.sm,
                )
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
private fun HeroRankingBadge(
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
private fun HeroGenrePill(
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
private fun HeroPageIndicators(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
) {
    val accent = BeOnTheme.colors.accent.primary

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(pageCount) { index ->
            val isActive = index == currentPage
            Box(
                modifier = Modifier
                    .height(6.dp)
                    .width(if (isActive) 20.dp else 6.dp)
                    .clip(BeOnTheme.shapes.full)
                    .background(
                        if (isActive) accent else Color(0x4DFFFFFF),
                    ),
            )
        }
    }
}
