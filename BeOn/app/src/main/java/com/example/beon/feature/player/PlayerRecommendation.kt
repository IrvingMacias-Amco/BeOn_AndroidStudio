package com.example.beon.feature.player

import com.example.beon.feature.home.ContentItem

data class PlayerRecommendation(
    val id: String,
    val title: String,
    val year: String,
    val imageUrl: String,
)

fun ContentItem.toPlayerRecommendation(): PlayerRecommendation = PlayerRecommendation(
    id = id,
    title = title,
    year = year.orEmpty(),
    imageUrl = imageUrl.orEmpty(),
)
