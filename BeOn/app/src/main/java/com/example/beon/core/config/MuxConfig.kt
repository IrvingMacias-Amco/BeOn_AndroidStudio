package com.example.beon.core.config

/**
 * Mux configuration notes for BeOn.
 *
 * **Reproducción en Android:** solo hace falta `muxPlaybackId` en Sanity.
 * El player usa URLs públicas HLS (`MuxStreaming.hlsUrl`) — no requiere tokens en la app.
 *
 * **MUX_TOKEN_ID / MUX_TOKEN_SECRET:** van en `local.properties` y `.env` (gitignored)
 * para scripts, backend o operaciones de API (crear assets, Data, etc.).
 * No se incluyen en [BuildConfig] ni en el APK.
 */
object MuxConfig {
    const val STREAM_BASE_URL = "https://stream.mux.com"
    const val IMAGE_BASE_URL = "https://image.mux.com"
    const val API_BASE_URL = "https://api.mux.com"
}
