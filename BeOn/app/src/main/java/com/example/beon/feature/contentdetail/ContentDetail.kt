package com.example.beon.feature.contentdetail

import com.example.beon.feature.home.Ranking

data class ContentDetail(
    val id: String,
    val title: String,
    val synopsis: String,
    val imageUrl: String,
    val year: String,
    val duration: String,
    val imdbRating: String?,
    val rating: String,
    val genres: List<String>,
    val cast: List<String>,
    val ranking: Ranking? = null,
    val muxPlaybackId: String? = null,
)

enum class ContentDetailTab {
    Related,
    Trailer,
    Details,
}
