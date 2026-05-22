package com.example.beon.data.remote.sanity

import com.example.beon.core.config.SanityConfig
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

private val sanityJson = Json {
    ignoreUnknownKeys = true
    explicitNulls = false
    isLenient = true
}

object SanityApiFactory {
    fun create(): SanityApi {
        require(SanityConfig.isConfigured) {
            "Sanity is not configured. Add SANITY_* keys to local.properties and rebuild."
        }

        val authInterceptor = Interceptor { chain ->
            val request = chain.request().newBuilder()
                .header("Authorization", "Bearer ${SanityConfig.apiToken}")
                .build()
            chain.proceed(request)
        }

        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(logging)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(SanityConfig.baseUrl)
            .client(client)
            .addConverterFactory(sanityJson.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(SanityApi::class.java)
    }
}
