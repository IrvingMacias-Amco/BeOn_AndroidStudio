package com.example.beon.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.beon.data.repository.HomeCatalog
import com.example.beon.data.repository.mockHomeCatalog
import com.example.beon.designsystem.theme.BeOnTheme
import com.example.beon.feature.home.components.HomeHeroSection
import com.example.beon.feature.home.components.HomeLandscapeCarousel
import com.example.beon.feature.home.components.HomeLiveChannelsRow
import com.example.beon.feature.home.components.HomeTop10Carousel
import com.example.beon.feature.home.components.HomeTopNav

/**
 * Home screen — espejo de `src/app/page.tsx` del frontend web.
 *
 * Secciones en orden:
 *   1. Top nav + Hero (con auto-rotación de slides)
 *   2. Continuar viendo            (rail landscape, con progreso)
 *   3. Top 10 en México            (carrusel top-10 con números)
 *   4. Agregados Recientemente     (rail landscape)
 *   5. Acción y Thriller           (rail landscape, sólo si hay)
 *   6. Drama                       (rail landscape, sólo si hay)
 *   7. Canales en Vivo             (portrait cards con badge EN VIVO)
 */
@Composable
fun HomeScreen(
    catalog: HomeCatalog,
    isLoading: Boolean,
    errorMessage: String?,
    onContentClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val listState = rememberLazyListState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BeOnTheme.colors.background.primary)
            .windowInsetsPadding(WindowInsets.statusBars),
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = listState,
        ) {
            item { HomeTopNav() }

            item {
                HomeHeroSection(
                    slides = catalog.heroSlides,
                    onSlideClick = onContentClick,
                )
            }

            if (catalog.continueWatching.isNotEmpty()) {
                item {
                    Spacer(modifier = Modifier.height(BeOnTheme.spacing.md))
                    HomeLandscapeCarousel(
                        title = "Continuar viendo",
                        items = catalog.continueWatching,
                        onItemClick = onContentClick,
                    )
                }
            }

            if (catalog.top10.isNotEmpty()) {
                item {
                    Spacer(modifier = Modifier.height(BeOnTheme.spacing.md))
                    HomeTop10Carousel(
                        title = "Top 10 en México",
                        items = catalog.top10,
                        onItemClick = onContentClick,
                    )
                }
            }

            if (catalog.recentlyAdded.isNotEmpty()) {
                item {
                    Spacer(modifier = Modifier.height(BeOnTheme.spacing.md))
                    HomeLandscapeCarousel(
                        title = "Agregados Recientemente",
                        items = catalog.recentlyAdded,
                        onItemClick = onContentClick,
                    )
                }
            }

            if (catalog.actionThriller.isNotEmpty()) {
                item {
                    Spacer(modifier = Modifier.height(BeOnTheme.spacing.md))
                    HomeLandscapeCarousel(
                        title = "Acción y Thriller",
                        items = catalog.actionThriller,
                        onItemClick = onContentClick,
                    )
                }
            }

            if (catalog.drama.isNotEmpty()) {
                item {
                    Spacer(modifier = Modifier.height(BeOnTheme.spacing.md))
                    HomeLandscapeCarousel(
                        title = "Drama",
                        items = catalog.drama,
                        onItemClick = onContentClick,
                    )
                }
            }

            if (catalog.liveChannels.isNotEmpty()) {
                item {
                    Spacer(modifier = Modifier.height(BeOnTheme.spacing.md))
                    HomeLiveChannelsRow(
                        title = "Canales en Vivo",
                        channels = catalog.liveChannels,
                    )
                    Spacer(modifier = Modifier.height(BeOnTheme.spacing.xl))
                }
            }
        }

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = BeOnTheme.colors.accent.primary,
            )
        }

        if (!errorMessage.isNullOrBlank()) {
            Text(
                text = errorMessage,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .background(BeOnTheme.colors.background.overlay)
                    .padding(BeOnTheme.spacing.sm),
                color = BeOnTheme.colors.text.primary,
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000, heightDp = 900)
@Composable
private fun HomeScreenPreview() {
    BeOnTheme {
        HomeScreen(
            catalog = mockHomeCatalog(),
            isLoading = false,
            errorMessage = null,
            onContentClick = {},
        )
    }
}
