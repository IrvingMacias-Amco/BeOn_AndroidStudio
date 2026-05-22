package com.example.beon.data.remote.sanity

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SanityApi {
    @GET("v2024-01-01/data/query/{dataset}")
    suspend fun query(
        @Path("dataset") dataset: String,
        @Query("query") query: String,
    ): SanityQueryResponse<List<SanityMovieDto>>

    @GET("v2024-01-01/data/query/{dataset}")
    suspend fun queryOne(
        @Path("dataset") dataset: String,
        @Query("query") query: String,
    ): SanityQueryResponse<SanityMovieDto?>
}
