package com.example.beon.feature.home

data class HeroSlide(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val year: String,
    val duration: String,
    val rating: String,
    val genres: List<String>,
    val ranking: Ranking? = null,
)

data class Ranking(
    val position: Int,
    val category: String,
)

data class ContentItem(
    val id: String,
    val title: String = "",
    val imageUrl: String? = null,
    val progress: Int? = null,
)

internal val homeNavItems = listOf(
    "Inicio",
    "Guía de TV",
    "Películas",
    "Series",
    "Deportes",
)

internal val heroSlides = listOf(
    HeroSlide(
        id = "1",
        title = "Marty Supremo",
        description = "Un agente deportivo con más estilo que ética descubre una última oportunidad de reden...",
        imageUrl = "https://images.unsplash.com/photo-1745948080908-b7e5fe4cba90?w=800",
        year = "2025",
        duration = "2h 29min",
        rating = "PG-13",
        genres = listOf("Comedia", "Deporte", "Drama"),
        ranking = Ranking(5, "películas de Drama"),
    ),
    HeroSlide(
        id = "2",
        title = "Urban Legends",
        description = "En las calles oscuras de la ciudad, las leyendas cobran vida.",
        imageUrl = "https://images.unsplash.com/photo-1758405282251-26903f4b7fcb?w=800",
        year = "2025",
        duration = "2h 05min",
        rating = "R",
        genres = listOf("Suspenso", "Terror", "Misterio"),
        ranking = Ranking(2, "películas de Terror"),
    ),
    HeroSlide(
        id = "3",
        title = "Neon Nights",
        description = "Una ciudad que nunca duerme, secretos que nunca descansan.",
        imageUrl = "https://images.unsplash.com/photo-1762417419967-d5ccd2ebe463?w=800",
        year = "2024",
        duration = "1h 52min",
        rating = "PG-13",
        genres = listOf("Acción", "Thriller", "Ciencia Ficción"),
    ),
    HeroSlide(
        id = "4",
        title = "Speed Demon",
        description = "Cuando la velocidad es tu única salvación y cada segundo cuenta.",
        imageUrl = "https://images.unsplash.com/photo-1627736990081-602486bcb4d3?w=800",
        year = "2025",
        duration = "2h 05min",
        rating = "PG-13",
        genres = listOf("Acción", "Drama", "Deporte"),
        ranking = Ranking(1, "películas de Acción"),
    ),
)

internal val continueWatchingItems = List(4) { ContentItem(id = "cw-$it") }

internal val landscapeRowItems = List(4) { ContentItem(id = "land-$it") }

internal val portraitRowItems = List(4) { ContentItem(id = "port-$it") }

internal val top10Items = List(10) { index ->
    ContentItem(id = "top-${index + 1}")
}

internal val featuredGridItems = List(4) { ContentItem(id = "feat-$it") }

internal val liveChannelItems = List(4) { ContentItem(id = "live-$it") }
