package com.example.beon.core.config

import com.example.beon.BuildConfig

object SanityConfig {
    val projectId: String = BuildConfig.SANITY_PROJECT_ID
    val dataset: String = BuildConfig.SANITY_DATASET
    val apiToken: String = BuildConfig.SANITY_API_TOKEN

    val isConfigured: Boolean
        get() = projectId.isNotBlank() && dataset.isNotBlank() && apiToken.isNotBlank()

    val baseUrl: String
        get() = "https://$projectId.api.sanity.io/"
}
