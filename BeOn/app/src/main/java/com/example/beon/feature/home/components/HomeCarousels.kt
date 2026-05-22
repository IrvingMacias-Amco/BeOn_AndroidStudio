package com.example.beon.feature.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.beon.designsystem.theme.BeOnTheme
import com.example.beon.feature.home.ContentItem

private val PlaceholderCardColor = Color(0xFFF2F2F2)

@Composable
fun HomeSectionHeader(
    title: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = BeOnTheme.colors.text.primary,
            letterSpacing = (-0.3).sp,
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = BeOnTheme.colors.text.primary,
            modifier = Modifier.width(20.dp),
        )
    }
}

@Composable
fun HomeLandscapeCarousel(
    title: String,
    items: List<ContentItem>,
    onItemClick: (String) -> Unit = {},
    cardWidth: androidx.compose.ui.unit.Dp = 200.dp,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        HomeSectionHeader(
            title = title,
            modifier = Modifier.padding(horizontal = BeOnTheme.safeArea.mobile),
        )
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow(
            contentPadding = androidx.compose.foundation.layout.PaddingValues(
                horizontal = BeOnTheme.safeArea.mobile,
            ),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            itemsIndexed(items) { _, item ->
                ContentLandscapeCard(
                    imageUrl = item.imageUrl,
                    width = cardWidth,
                    onClick = { onItemClick(item.id) },
                )
            }
        }
    }
}

@Composable
fun HomeFeaturedGridSection(
    title: String,
    items: List<ContentItem>,
    onItemClick: (String) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = BeOnTheme.safeArea.mobile),
    ) {
        HomeSectionHeader(title = title)
        Spacer(modifier = Modifier.height(12.dp))
        ContentLandscapeCard(
            imageUrl = items.firstOrNull()?.imageUrl,
            width = androidx.compose.ui.unit.Dp.Unspecified,
            modifier = Modifier.fillMaxWidth(),
            onClick = { items.firstOrNull()?.id?.let(onItemClick) },
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            val portraitItems = items.take(3)
            portraitItems.forEach { item ->
                ContentPortraitCard(
                    imageUrl = item.imageUrl,
                    modifier = Modifier.weight(1f),
                    onClick = { onItemClick(item.id) },
                )
            }
        }
    }
}

@Composable
fun HomeTop10Carousel(
    title: String,
    items: List<ContentItem>,
    onItemClick: (String) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        HomeSectionHeader(
            title = title,
            modifier = Modifier.padding(horizontal = BeOnTheme.safeArea.mobile),
        )
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow(
            contentPadding = androidx.compose.foundation.layout.PaddingValues(
                horizontal = BeOnTheme.safeArea.mobile,
            ),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            itemsIndexed(items) { index, item ->
                Top10Card(
                    rank = index + 1,
                    imageUrl = item.imageUrl,
                    onClick = { onItemClick(item.id) },
                )
            }
        }
    }
}

@Composable
private fun ContentLandscapeCard(
    imageUrl: String?,
    width: androidx.compose.ui.unit.Dp,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .then(
                if (width != androidx.compose.ui.unit.Dp.Unspecified) {
                    Modifier.width(width)
                } else {
                    Modifier
                },
            )
            .aspectRatio(16f / 9f)
            .clip(BeOnTheme.shapes.md)
            .background(PlaceholderCardColor)
            .clickable(onClick = onClick),
    ) {
        if (!imageUrl.isNullOrBlank()) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}

@Composable
private fun ContentPortraitCard(
    imageUrl: String?,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .aspectRatio(2f / 3f)
            .clip(BeOnTheme.shapes.md)
            .background(PlaceholderCardColor)
            .clickable(onClick = onClick),
    ) {
        if (!imageUrl.isNullOrBlank()) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}

@Composable
private fun Top10Card(
    rank: Int,
    imageUrl: String? = null,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val rankFontSize = if (rank == 10) 96.sp else 112.sp
    val cardOffset = if (rank == 10) 96.dp else 72.dp
    val cardWidth = 120.dp

    Box(
        modifier = modifier
            .width(if (rank == 10) 240.dp else 210.dp)
            .height(180.dp),
    ) {
        Text(
            text = rank.toString(),
            modifier = Modifier.align(Alignment.BottomStart),
            style = TextStyle(
                fontSize = rankFontSize,
                fontWeight = FontWeight.Black,
                color = Color.White,
                drawStyle = Stroke(width = 3f),
            ),
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .offset(x = cardOffset, y = (-8).dp)
                .width(cardWidth)
                .height(160.dp)
                .clip(BeOnTheme.shapes.lg)
                .background(PlaceholderCardColor)
                .clickable(onClick = onClick),
        ) {
            if (!imageUrl.isNullOrBlank()) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}
