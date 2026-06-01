package com.example.beon.data.mapper

import com.example.beon.data.model.Movie
import com.example.beon.data.remote.bff.BffMovieDto
import com.example.beon.feature.contentdetail.ContentDetail
import com.example.beon.feature.home.ContentItem
import com.example.beon.feature.home.HeroSlide

object MovieMapper {

    fun toMovie(dto: BffMovieDto): Movie {
        val slug = dto.slug.current
        val poster = dto.poster?.imageUrl()
        val keyArt = dto.keyArt?.imageUrl()
        val hero = dto.hero?.imageUrl()

        return Movie(
            id = slug.ifBlank { dto.id },
            title = dto.title,
            slug = slug,
            releaseYear = dto.releaseYear,
            durationMinutes = dto.duration,
            shortSynopsis = dto.shortSynopsis,
            longSynopsis = dto.longSynopsis,
            muxPlaybackId = dto.muxPlaybackId?.trim()?.takeIf { it.isNotBlank() },
            muxAssetId = dto.muxAssetId?.trim()?.takeIf { it.isNotBlank() },
            posterUrl = poster ?: keyArt ?: hero,
            heroUrl = hero ?: keyArt ?: poster,
            genres = dto.genres.orEmpty().mapNotNull { it.name?.takeIf(String::isNotBlank) },
            cast = dto.castNames.orEmpty().filter { it.isNotBlank() },
            ratingLabel = dto.ratingLabel,
        )
    }

    fun toHeroSlide(movie: Movie): HeroSlide = HeroSlide(
        id = movie.id,
        title = movie.title,
        description = movie.shortSynopsis ?: movie.synopsis,
        imageUrl = movie.heroUrl.orEmpty(),
        year = movie.releaseYear?.toString().orEmpty(),
        duration = formatDuration(movie.durationMinutes),
        rating = movie.ratingLabel ?: "NR",
        genres = movie.genres,
    )

    fun toContentItem(movie: Movie, progress: Int? = null): ContentItem = ContentItem(
        id = movie.id,
        title = movie.title,
        imageUrl = movie.posterUrl ?: movie.heroUrl,
        year = movie.releaseYear?.toString(),
        progress = progress,
    )

    fun toContentDetail(movie: Movie): ContentDetail = ContentDetail(
        id = movie.id,
        title = movie.title,
        synopsis = movie.synopsis,
        imageUrl = movie.heroUrl.orEmpty(),
        year = movie.releaseYear?.toString().orEmpty(),
        duration = formatDuration(movie.durationMinutes),
        imdbRating = null,
        rating = movie.ratingLabel ?: "NR",
        genres = movie.genres,
        cast = movie.cast,
        muxPlaybackId = movie.muxPlaybackId,
    )

    private fun formatDuration(minutes: Int?): String {
        if (minutes == null || minutes <= 0) return "--"
        val hours = minutes / 60
        val remaining = minutes % 60
        return when {
            hours > 0 && remaining > 0 -> "${hours}h ${remaining}min"
            hours > 0 -> "${hours}h"
            else -> "${remaining}min"
        }
    }

    private fun com.example.beon.data.remote.bff.BffImageDto.imageUrl(): String? =
        asset?.url?.takeIf { it.isNotBlank() }
}
