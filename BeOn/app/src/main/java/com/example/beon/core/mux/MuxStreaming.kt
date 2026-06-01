package com.example.beon.core.mux

import com.example.beon.core.config.MuxConfig

object MuxStreaming {
    /**
     * Public HLS URL for a Mux asset. The playback ID comes from Sanity (`muxPlaybackId`).
     * Mux API tokens ([MuxConfig]) are not required on the client for public playback policies.
     */
    fun hlsUrl(playbackId: String): String =
        "${MuxConfig.STREAM_BASE_URL}/$playbackId.m3u8"

    fun thumbnailUrl(
        playbackId: String,
        width: Int = 640,
        timeSeconds: Int = 0,
    ): String =
        "${MuxConfig.IMAGE_BASE_URL}/$playbackId/thumbnail.jpg?width=$width&time=$timeSeconds"
}
