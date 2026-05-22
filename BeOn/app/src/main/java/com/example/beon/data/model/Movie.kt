package com.example.beon.data.model

data class Movie(
    val id: String,
    val title: String,
    val slug: String?,
    val releaseYear: Int?,
    val durationMinutes: Int?,
    val shortSynopsis: String?,
    val longSynopsis: String?,
    val muxPlaybackId: String?,
    val muxAssetId: String?,
    val posterUrl: String?,
    val heroUrl: String?,
    val genres: List<String>,
    val cast: List<String>,
    val ratingLabel: String?,
) {
    val synopsis: String
        get() = longSynopsis?.takeIf { it.isNotBlank() }
            ?: shortSynopsis.orEmpty()

    val hasVideo: Boolean
        get() = !muxPlaybackId.isNullOrBlank()
}
