package com.example.beon.data.repository

import com.example.beon.core.config.BffConfig
import com.example.beon.data.mapper.MovieMapper
import com.example.beon.data.model.Movie
import com.example.beon.data.remote.bff.BffApi
import com.example.beon.data.remote.bff.BffApiFactory
import com.example.beon.feature.contentdetail.ContentDetail
import com.example.beon.feature.home.ContentItem
import com.example.beon.feature.home.HeroSlide
import com.example.beon.feature.home.LiveChannel
import com.example.beon.feature.home.continueWatchingItems
import com.example.beon.feature.home.dramaItems
import com.example.beon.feature.home.heroSlides
import com.example.beon.feature.home.liveChannels
import com.example.beon.feature.home.recentlyAddedItems
import com.example.beon.feature.home.thrillerItems
import com.example.beon.feature.home.top10Items

/**
 * Espejo de la estructura de la home del frontend (`src/app/page.tsx` del repo web).
 *
 * Secciones:
 *   1. Hero (`heroSlides`)
 *   2. Continuar viendo (`continueWatching`)
 *   3. Top 10 en México (`top10`)
 *   4. Agregados Recientemente (`recentlyAdded`)
 *   5. Acción y Thriller (`actionThriller`)
 *   6. Drama (`drama`)
 *   7. Canales en Vivo (`liveChannels`)
 */
data class HomeCatalog(
    val heroSlides: List<HeroSlide>,
    val continueWatching: List<ContentItem>,
    val top10: List<ContentItem>,
    val recentlyAdded: List<ContentItem>,
    val actionThriller: List<ContentItem>,
    val drama: List<ContentItem>,
    val liveChannels: List<LiveChannel>,
    val isFromRemote: Boolean,
)

fun mockHomeCatalog(): HomeCatalog = HomeCatalog(
    heroSlides = heroSlides,
    continueWatching = continueWatchingItems,
    top10 = top10Items,
    recentlyAdded = recentlyAddedItems,
    actionThriller = thrillerItems,
    drama = dramaItems,
    liveChannels = liveChannels,
    isFromRemote = false,
)

interface MovieRepository {
    suspend fun getHomeCatalog(): HomeCatalog
    suspend fun getMovieDetail(movieId: String): ContentDetail
    suspend fun getRecommendations(excludeContentId: String, limit: Int = 10): List<ContentItem>
}

/**
 * Implementación que consume el BFF de BeOn.
 *
 * Endpoints usados:
 *  - `GET {base}/movies`           → catálogo completo
 *  - `GET {base}/movies/{slug}`    → detalle de una película
 *
 * El BFF ya hace el join con Mux y filtra drafts, así que esta capa solo arma
 * los rails con las mismas reglas que `src/app/page.tsx` del frontend web.
 */
class BffMovieRepository(
    private val api: BffApi,
) : MovieRepository {

    @Volatile
    private var cachedMovies: List<Movie>? = null

    override suspend fun getHomeCatalog(): HomeCatalog {
        val movies = fetchMovies()
        if (movies.isEmpty()) return mockHomeCatalog()

        val heroSource = movies
            .filter { !it.heroUrl.isNullOrBlank() || !it.posterUrl.isNullOrBlank() }
            .ifEmpty { movies }

        // Mismos slicings que el frontend (src/app/page.tsx):
        //   heroMovies      = displayMovies.slice(0, 5)
        //   trendingMovies  = displayMovies.slice(0, 10)
        //   recentMovies    = displayMovies.slice(1, 8)
        //   continueWatching= displayMovies.slice(2, 6) con progress [35, 72, 15, 88]
        val continueWatchingProgress = intArrayOf(35, 72, 15, 88)
        val continueWatching = movies
            .drop(2)
            .take(4)
            .mapIndexed { index, movie ->
                MovieMapper.toContentItem(
                    movie,
                    progress = continueWatchingProgress.getOrElse(index) { 50 },
                )
            }

        // Mismos filtros que el frontend:
        //   actionMovies = filter genres in ["Thriller", "Crimen", "Aventura"]
        //   dramaMovies  = filter genres in ["Drama", "Biografía"]
        val actionGenres = setOf("Thriller", "Crimen", "Aventura")
        val dramaGenres = setOf("Drama", "Biografía")

        return HomeCatalog(
            heroSlides = heroSource.take(5).map(MovieMapper::toHeroSlide),
            continueWatching = continueWatching,
            top10 = movies.take(10).map { MovieMapper.toContentItem(it) },
            recentlyAdded = movies.drop(1).take(7).map { MovieMapper.toContentItem(it) },
            actionThriller = movies
                .filter { movie -> movie.genres.any { it in actionGenres } }
                .map { MovieMapper.toContentItem(it) },
            drama = movies
                .filter { movie -> movie.genres.any { it in dramaGenres } }
                .map { MovieMapper.toContentItem(it) },
            liveChannels = liveChannels,
            isFromRemote = true,
        )
    }

    override suspend fun getMovieDetail(movieId: String): ContentDetail {
        val movie = runCatching { api.getMovieBySlug(movieId) }
            .map(MovieMapper::toMovie)
            .getOrNull()
            ?: fetchMovies().firstOrNull { it.id == movieId || it.slug == movieId }
            ?: throw NoSuchElementException("Película no encontrada en el BFF: $movieId")

        return MovieMapper.toContentDetail(movie)
    }

    override suspend fun getRecommendations(excludeContentId: String, limit: Int): List<ContentItem> {
        val movies = fetchMovies()
            .filter { it.id != excludeContentId && it.slug != excludeContentId }
            .filter { !it.posterUrl.isNullOrBlank() || !it.heroUrl.isNullOrBlank() }
        if (movies.isEmpty()) return mockRecommendations(excludeContentId, limit)
        return movies.take(limit).map { MovieMapper.toContentItem(it) }
    }

    private suspend fun fetchMovies(): List<Movie> {
        cachedMovies?.let { return it }
        val response = api.getMovies()
        val movies = response.movies.map(MovieMapper::toMovie)
        cachedMovies = movies
        return movies
    }
}

object MovieRepositoryProvider {
    val repository: MovieRepository by lazy {
        if (BffConfig.isConfigured) {
            runCatching { BffMovieRepository(BffApiFactory.create()) }
                .getOrElse { MockMovieRepository() }
        } else {
            MockMovieRepository()
        }
    }
}

private fun mockRecommendations(excludeContentId: String, limit: Int): List<ContentItem> =
    heroSlides
        .filter { it.id != excludeContentId }
        .take(limit)
        .map { slide ->
            ContentItem(
                id = slide.id,
                title = slide.title,
                imageUrl = slide.imageUrl,
                year = slide.year,
            )
        }

private class MockMovieRepository : MovieRepository {
    override suspend fun getHomeCatalog(): HomeCatalog = mockHomeCatalog()

    override suspend fun getRecommendations(excludeContentId: String, limit: Int): List<ContentItem> =
        mockRecommendations(excludeContentId, limit)

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
