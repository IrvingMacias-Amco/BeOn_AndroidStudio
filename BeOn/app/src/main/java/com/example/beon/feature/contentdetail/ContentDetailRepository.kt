package com.example.beon.feature.contentdetail

import com.example.beon.feature.home.Ranking
import com.example.beon.feature.home.heroSlides

private val martySupremoDetail = ContentDetail(
    id = "1",
    title = "Marty Supremo",
    synopsis = "El camino hacia la cima es solitario pero glorioso. Un agente deportivo con más estilo que ética descubre una última oportunidad en el ping pong, apostando todo en Marty, una estrella en ascenso del ping pong con talento desmedido — y un fuerte deseo de volver a ver triunfar a su padre.",
    imageUrl = "https://images.unsplash.com/photo-1745948080908-b7e5fe4cba90?w=1200",
    year = "2025",
    duration = "2h 29min",
    imdbRating = "7.5",
    rating = "PG-13",
    genres = listOf("Comedia", "Deporte", "Drama"),
    cast = listOf(
        "Timothée Chalamet",
        "Gwyneth Paltrow",
        "Odessa A'zion",
        "Abel Ferrara",
    ),
    ranking = Ranking(5, "películas de Drama"),
)

private val contentDetailsById = buildMap {
    heroSlides.forEach { slide ->
        put(
            slide.id,
            martySupremoDetail.copy(
                id = slide.id,
                title = slide.title,
                synopsis = slide.description,
                imageUrl = slide.imageUrl,
                year = slide.year,
                duration = slide.duration,
                rating = slide.rating,
                genres = slide.genres,
                ranking = slide.ranking,
                imdbRating = if (slide.id == "1") "7.5" else null,
                cast = if (slide.id == "1") martySupremoDetail.cast else emptyList(),
            ),
        )
    }
}

fun getContentDetail(contentId: String): ContentDetail {
    return contentDetailsById[contentId] ?: martySupremoDetail.copy(id = contentId)
}
