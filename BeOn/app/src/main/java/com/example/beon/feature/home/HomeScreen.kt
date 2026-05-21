package com.example.beon.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.beon.designsystem.theme.BeOnTheme
import com.example.beon.feature.home.components.HomeFeaturedGridSection
import com.example.beon.feature.home.components.HomeHeroSection
import com.example.beon.feature.home.components.HomeLandscapeCarousel
import com.example.beon.feature.home.components.HomeTop10Carousel
import com.example.beon.feature.home.components.HomeTopNav

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(BeOnTheme.colors.background.primary)
            .windowInsetsPadding(WindowInsets.statusBars),
        state = listState,
    ) {
        item {
            HomeTopNav()
        }

        item {
            HomeHeroSection()
        }

        item {
            Spacer(modifier = Modifier.height(BeOnTheme.spacing.md))
            HomeLandscapeCarousel(
                title = "Continuar viendo",
                items = continueWatchingItems,
            )
        }

        item {
            Spacer(modifier = Modifier.height(BeOnTheme.spacing.md))
            HomeFeaturedGridSection(
                title = "Recién Agregado",
                items = featuredGridItems,
            )
        }

        item {
            Spacer(modifier = Modifier.height(BeOnTheme.spacing.md))
            HomeTop10Carousel(
                title = "Top 10 Hoy",
                items = top10Items,
            )
        }

        item {
            Spacer(modifier = Modifier.height(BeOnTheme.spacing.md))
            HomeFeaturedGridSection(
                title = "Para ti",
                items = featuredGridItems,
            )
        }

        item {
            Spacer(modifier = Modifier.height(BeOnTheme.spacing.md))
            HomeLandscapeCarousel(
                title = "Canales en Vivo",
                items = liveChannelItems,
                cardWidth = 180.dp,
            )
        }

        item {
            Spacer(modifier = Modifier.height(BeOnTheme.spacing.md))
            HomeFeaturedGridSection(
                title = "Originales Beon",
                items = featuredGridItems,
            )
            Spacer(modifier = Modifier.height(BeOnTheme.spacing.xl))
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000, heightDp = 900)
@Composable
private fun HomeScreenPreview() {
    BeOnTheme {
        HomeScreen()
    }
}
