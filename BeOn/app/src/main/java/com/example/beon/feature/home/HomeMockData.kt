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
    val year: String? = null,
    val progress: Int? = null,
)

/**
 * Canal en vivo (mock — futuro: vendrá del BFF o de una fuente EPG).
 * Mirror de `LiveChannel` del frontend web (`src/app/page.tsx`).
 */
data class LiveChannel(
    val id: String,
    val name: String,
    val currentProgram: String,
    val category: String,
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

// Pool de placeholders derivado de los hero slides — se usa cuando el BFF
// no responde. En cuanto llegan datos reales, estas listas se reemplazan en
// `HomeCatalog` por las que arma `BffMovieRepository.getHomeCatalog`.
private fun mockContent(prefix: String, count: Int, progress: ((Int) -> Int?)? = null): List<ContentItem> =
    List(count) { index ->
        val slide = heroSlides[index % heroSlides.size]
        ContentItem(
            id = "$prefix-$index",
            title = slide.title,
            imageUrl = slide.imageUrl,
            year = slide.year,
            progress = progress?.invoke(index),
        )
    }

// Mismos números de progreso que el frontend (page.tsx: [35, 72, 15, 88])
private val continueWatchingProgress = intArrayOf(35, 72, 15, 88)

internal val continueWatchingItems = mockContent("cw", 4) { continueWatchingProgress.getOrElse(it) { 50 } }

internal val top10Items = mockContent("top", 10)

internal val recentlyAddedItems = mockContent("recent", 7)

internal val thrillerItems = mockContent("thriller", 4)

internal val dramaItems = mockContent("drama", 3)

// Canales en vivo mock — espelho de DUMMY_LIVE_CHANNELS de page.tsx del frontend web.
internal val liveChannels = listOf(
    LiveChannel(
        id = "live-1",
        name = "BeOn Cine",
        currentProgram = "Maratón de Thriller",
        category = "Películas",
    ),
    LiveChannel(
        id = "live-2",
        name = "BeOn Series",
        currentProgram = "Temporada 3 - Ep. 7",
        category = "Series",
    ),
    LiveChannel(
        id = "live-3",
        name = "BeOn Docs",
        currentProgram = "Naturaleza Extrema",
        category = "Documentales",
    ),
    LiveChannel(
        id = "live-4",
        name = "BeOn Kids",
        currentProgram = "Aventuras Animadas",
        category = "Infantil",
    ),
    LiveChannel(
        id = "live-5",
        name = "BeOn Deportes",
        currentProgram = "Liga MX en Vivo",
        category = "Deportes",
    ),
)
