package com.example.beon.data.remote.bff

import com.example.beon.core.config.BffConfig
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit

private val bffJson = Json {
    ignoreUnknownKeys = true
    explicitNulls = false
    isLenient = true
}

object BffApiFactory {
    fun create(): BffApi {
        require(BffConfig.isConfigured) {
            "BFF no configurado. Define BFF_BASE_URL en local.properties y haz Sync Gradle."
        }

        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(BffConfig.baseUrl.ensureTrailingSlash())
            .client(client)
            .addConverterFactory(bffJson.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(BffApi::class.java)
    }

    private fun String.ensureTrailingSlash(): String =
        if (endsWith("/")) this else "$this/"
}
