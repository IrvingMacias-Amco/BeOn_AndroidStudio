package com.example.beon.core.mux

object MuxStreaming {
    /**
     * Public HLS URL for a Mux asset. The playback ID comes from Sanity (`muxPlaybackId`).
     * Mux API tokens are not required on the client for public playback policies.
     */
    fun hlsUrl(playbackId: String): String = "https://stream.mux.com/$playbackId.m3u8"

    fun thumbnailUrl(
        playbackId: String,
        width: Int = 640,
        timeSeconds: Int = 0,
    ): String = "https://image.mux.com/$playbackId/thumbnail.jpg?width=$width&time=$timeSeconds"
}
