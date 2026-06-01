package com.example.beon.data.remote.bff

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Imagen del BFF — `{ asset: { url }, alt? }`. */
@Serializable
data class BffImageDto(
    val asset: BffAssetDto? = null,
    val alt: String? = null,
)

@Serializable
data class BffAssetDto(
    val url: String? = null,
)

/** Slug — `{ current: "el-secreto-de-sus-ojos" }`. */
@Serializable
data class BffSlugDto(
    val current: String,
)

/** Género — `{ name: "Thriller", slug: { current: "thriller" } }`. */
@Serializable
data class BffGenreDto(
    val name: String? = null,
    val slug: BffSlugDto? = null,
)

/**
 * Película tal como la entrega el BFF (`GET /movies` y `GET /movies/{slug}`).
 *
 * Algunos campos solo vienen en el detalle (`longSynopsis`, `muxAssetId`,
 * `ingestStatus`).
 */
@Serializable
data class BffMovieDto(
    @SerialName("_id")
    val id: String,
    val title: String,
    val slug: BffSlugDto,
    val shortSynopsis: String? = null,
    val longSynopsis: String? = null,
    val poster: BffImageDto? = null,
    val keyArt: BffImageDto? = null,
    val hero: BffImageDto? = null,
    val genres: List<BffGenreDto>? = null,
    val releaseYear: Int? = null,
    val duration: Int? = null,
    val muxPlaybackId: String? = null,
    val muxAssetId: String? = null,
    val ingestStatus: String? = null,
    val tags: List<String>? = null,
    val ratingLabel: String? = null,
    val castNames: List<String>? = null,
)

/** `GET /movies` envuelve la lista. */
@Serializable
data class BffMoviesListResponse(
    val movies: List<BffMovieDto>,
    val count: Int = 0,
)

/** Error response del BFF — `{ error: string, detail?: string }`. */
@Serializable
data class BffErrorResponse(
    val error: String,
    val detail: String? = null,
)
