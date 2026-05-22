package com.example.beon.data.repository

import com.example.beon.core.config.SanityConfig
import com.example.beon.data.mapper.MovieMapper
import com.example.beon.data.model.Movie
import com.example.beon.data.remote.sanity.SanityApi
import com.example.beon.data.remote.sanity.SanityApiFactory
import com.example.beon.data.remote.sanity.SanityQueries
import com.example.beon.feature.contentdetail.ContentDetail
import com.example.beon.feature.home.ContentItem
import com.example.beon.feature.home.HeroSlide
import com.example.beon.feature.home.continueWatchingItems
import com.example.beon.feature.home.featuredGridItems
import com.example.beon.feature.home.heroSlides
import com.example.beon.feature.home.liveChannelItems
import com.example.beon.feature.home.top10Items

data class HomeCatalog(
    val heroSlides: List<HeroSlide>,
    val continueWatching: List<ContentItem>,
    val recentlyAdded: List<ContentItem>,
    val top10: List<ContentItem>,
    val forYou: List<ContentItem>,
    val liveChannels: List<ContentItem>,
    val originals: List<ContentItem>,
    val isFromSanity: Boolean,
)

fun mockHomeCatalog(): HomeCatalog = HomeCatalog(
    heroSlides = heroSlides,
    continueWatching = continueWatchingItems,
    recentlyAdded = featuredGridItems,
    top10 = top10Items,
    forYou = featuredGridItems,
    liveChannels = liveChannelItems,
    originals = featuredGridItems,
    isFromSanity = false,
)

interface MovieRepository {
    suspend fun getHomeCatalog(): HomeCatalog
    suspend fun getMovieDetail(movieId: String): ContentDetail
}

class SanityMovieRepository(
    private val api: SanityApi,
    private val dataset: String = SanityConfig.dataset,
) : MovieRepository {

    override suspend fun getHomeCatalog(): HomeCatalog {
        val movies = fetchMovies()
        if (movies.isEmpty()) return mockHomeCatalog()

        val withVideo = movies.filter { it.hasVideo }
        val heroCandidates = movies.filter { !it.heroUrl.isNullOrBlank() }.ifEmpty { movies }

        return HomeCatalog(
            heroSlides = heroCandidates.take(5).map(MovieMapper::toHeroSlide),
            continueWatching = withVideo.take(4).map { MovieMapper.toContentItem(it, progress = 35) },
            recentlyAdded = movies.take(4).map { MovieMapper.toContentItem(it) },
            top10 = movies.take(10).map { MovieMapper.toContentItem(it) },
            forYou = movies.drop(2).take(4).map { MovieMapper.toContentItem(it) },
            liveChannels = withVideo.take(4).map { MovieMapper.toContentItem(it) },
            originals = movies.takeLast(4).map { MovieMapper.toContentItem(it) },
            isFromSanity = true,
        )
    }

    override suspend fun getMovieDetail(movieId: String): ContentDetail {
        val response = api.queryOne(
            dataset = dataset,
            query = SanityQueries.movieById(movieId),
        )
        val movie = response.result?.let(MovieMapper::toMovie)
            ?: fetchMovies().firstOrNull { it.id == movieId }
            ?: throw NoSuchElementException("Movie not found: $movieId")
        return MovieMapper.toContentDetail(movie)
    }

    private suspend fun fetchMovies(): List<Movie> {
        val response = api.query(
            dataset = dataset,
            query = SanityQueries.moviesHome,
        )
        return response.result.map(MovieMapper::toMovie)
    }
}

object MovieRepositoryProvider {
    val repository: MovieRepository by lazy {
        if (SanityConfig.isConfigured) {
            SanityMovieRepository(SanityApiFactory.create())
        } else {
            MockMovieRepository()
        }
    }
}

private class MockMovieRepository : MovieRepository {
    override suspend fun getHomeCatalog(): HomeCatalog = mockHomeCatalog()

    override suspend fun getMovieDetail(movieId: String): ContentDetail {
        val hero = heroSlides.firstOrNull { it.id == movieId } ?: heroSlides.first()
        return MovieMapper.toContentDetail(
            Movie(
                id = hero.id,
                title = hero.title,
                slug = null,
                releaseYear = hero.year.toIntOrNull(),
                durationMinutes = null,
                shortSynopsis = hero.description,
                longSynopsis = hero.description,
                muxPlaybackId = null,
                muxAssetId = null,
                posterUrl = hero.imageUrl,
                heroUrl = hero.imageUrl,
                genres = hero.genres,
                cast = emptyList(),
                ratingLabel = hero.rating,
            ),
        )
    }
}
