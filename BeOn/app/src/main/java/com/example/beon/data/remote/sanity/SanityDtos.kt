package com.example.beon.data.remote.sanity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SanityQueryResponse<T>(
    val result: T,
    val ms: Long? = null,
)

@Serializable
data class SanityMovieDto(
    @SerialName("_id")
    val id: String,
    val title: String,
    val slug: String? = null,
    val releaseYear: Int? = null,
    val duration: Int? = null,
    val shortSynopsis: String? = null,
    val longSynopsis: String? = null,
    val muxPlaybackId: String? = null,
    val muxAssetId: String? = null,
    val posterUrl: String? = null,
    val heroUrl: String? = null,
    val genres: List<String?>? = null,
    val cast: List<String?>? = null,
    val ratingLabel: String? = null,
)
