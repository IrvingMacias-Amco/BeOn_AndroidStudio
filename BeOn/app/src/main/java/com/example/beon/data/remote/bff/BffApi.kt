package com.example.beon.data.remote.bff

import retrofit2.http.GET
import retrofit2.http.Path

interface BffApi {
    @GET("movies")
    suspend fun getMovies(): BffMoviesListResponse

    @GET("movies/{slug}")
    suspend fun getMovieBySlug(@Path("slug") slug: String): BffMovieDto
}
