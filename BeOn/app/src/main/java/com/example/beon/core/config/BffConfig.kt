package com.example.beon.core.config

import com.example.beon.BuildConfig

/**
 * Configuración del Backend-For-Frontend (BFF) de BeOn.
 *
 * El BFF expone los endpoints REST que consume esta app:
 *   - `GET /movies`           → lista del catálogo
 *   - `GET /movies/{slug}`    → detalle de una película
 *
 * `BFF_BASE_URL` se inyecta en compile-time desde `local.properties`.
 * Si no se define, el default apunta al BFF corriendo en el host del
 * emulador (`http://10.0.2.2:3000/api/`).
 *
 * Para producción/Amplify usar algo como:
 *   `BFF_BASE_URL=https://main.d2abcde.amplifyapp.com/api/`
 */
object BffConfig {
    val baseUrl: String = BuildConfig.BFF_BASE_URL

    val isConfigured: Boolean
        get() = baseUrl.isNotBlank()
}
