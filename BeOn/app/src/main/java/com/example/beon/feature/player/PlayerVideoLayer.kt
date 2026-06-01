package com.example.beon.feature.player

import android.graphics.SurfaceTexture
import android.view.TextureView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.VideoSize
import androidx.media3.exoplayer.ExoPlayer
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import java.util.concurrent.atomic.AtomicBoolean

/** Aspect ratio inicial (16:9) hasta que el stream reporte el real. */
private const val DEFAULT_VIDEO_ASPECT_RATIO = 16f / 9f

@Composable
fun PlayerVideoLayer(
    streamUrl: String?,
    isPlaying: Boolean,
    playbackSpeed: Float = 1f,
    onIsPlayingChanged: (Boolean) -> Unit,
    onPositionChanged: (Long) -> Unit,
    onDurationChanged: (Long) -> Unit,
    seekRequestMs: Long?,
    onSeekHandled: () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (streamUrl.isNullOrBlank()) return

    val appContext = LocalContext.current.applicationContext
    val lifecycleOwner = LocalLifecycleOwner.current
    val mainExecutor = remember(appContext) { ContextCompat.getMainExecutor(appContext) }
    val mediaPrepared = remember { AtomicBoolean(false) }

    val exoPlayer = remember {
        ExoPlayer.Builder(appContext).build().apply {
            repeatMode = Player.REPEAT_MODE_OFF
        }
    }

    val playingState = rememberUpdatedState(isPlaying)
    val onPlayingChanged = rememberUpdatedState(onIsPlayingChanged)
    val onPosition = rememberUpdatedState(onPositionChanged)
    val onDuration = rememberUpdatedState(onDurationChanged)

    // El aspect ratio real del stream (ancho/alto) — se actualiza cuando ExoPlayer
    // detecta el tamaño del primer frame. Se aplica al `TextureView` con
    // `Modifier.aspectRatio(...)` para preservar las proporciones del video
    // (letterbox/pillarbox sobre fondo negro en vez de estirar).
    var videoAspectRatio by remember { mutableFloatStateOf(DEFAULT_VIDEO_ASPECT_RATIO) }

    DisposableEffect(exoPlayer) {
        val listener = object : Player.Listener {
            override fun onIsPlayingChanged(playing: Boolean) {
                mainExecutor.execute { onPlayingChanged.value(playing) }
            }

            override fun onPlaybackStateChanged(playbackState: Int) {
                if (playbackState == Player.STATE_READY) {
                    mainExecutor.execute {
                        onDuration.value(exoPlayer.duration.coerceAtLeast(0L))
                    }
                }
            }

            override fun onVideoSizeChanged(videoSize: VideoSize) {
                if (videoSize.width <= 0 || videoSize.height <= 0) return
                val pixelAspect = if (videoSize.pixelWidthHeightRatio > 0f) {
                    videoSize.pixelWidthHeightRatio
                } else {
                    1f
                }
                val ratio = (videoSize.width * pixelAspect) / videoSize.height
                if (ratio.isFinite() && ratio > 0f) {
                    mainExecutor.execute { videoAspectRatio = ratio }
                }
            }
        }
        exoPlayer.addListener(listener)
        onDispose {
            mediaPrepared.set(false)
            exoPlayer.removeListener(listener)
            exoPlayer.stop()
            exoPlayer.clearVideoSurface()
            exoPlayer.release()
        }
    }

    DisposableEffect(lifecycleOwner, exoPlayer) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_PAUSE -> exoPlayer.pause()
                Lifecycle.Event.ON_STOP -> exoPlayer.stop()
                else -> Unit
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    fun prepareIfNeeded() {
        if (!mediaPrepared.compareAndSet(false, true)) return
        exoPlayer.setMediaItem(MediaItem.fromUri(streamUrl))
        exoPlayer.prepare()
        exoPlayer.playWhenReady = playingState.value
        exoPlayer.setPlaybackSpeed(playbackSpeed)
    }

    LaunchedEffect(isPlaying) {
        if (mediaPrepared.get()) {
            exoPlayer.playWhenReady = isPlaying
        }
    }

    LaunchedEffect(playbackSpeed) {
        if (mediaPrepared.get()) {
            exoPlayer.setPlaybackSpeed(playbackSpeed)
        }
    }

    LaunchedEffect(seekRequestMs) {
        if (!mediaPrepared.get()) return@LaunchedEffect
        val target = seekRequestMs ?: return@LaunchedEffect
        val duration = exoPlayer.duration.takeIf { it > 0 } ?: Long.MAX_VALUE
        exoPlayer.seekTo(target.coerceIn(0L, duration))
        onSeekHandled()
    }

    LaunchedEffect(exoPlayer) {
        while (isActive) {
            if (mediaPrepared.get()) {
                try {
                    onPosition.value(exoPlayer.currentPosition.coerceAtLeast(0L))
                    if (exoPlayer.duration > 0) {
                        onDuration.value(exoPlayer.duration)
                    }
                } catch (_: IllegalStateException) {
                    break
                }
            }
            delay(500)
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center,
    ) {
        AndroidView(
            factory = { ctx ->
                TextureView(ctx).apply {
                    surfaceTextureListener = object : TextureView.SurfaceTextureListener {
                        override fun onSurfaceTextureAvailable(
                            surface: SurfaceTexture,
                            width: Int,
                            height: Int,
                        ) {
                            exoPlayer.setVideoTextureView(this@apply)
                            mainExecutor.execute { prepareIfNeeded() }
                        }

                        override fun onSurfaceTextureSizeChanged(
                            surface: SurfaceTexture,
                            width: Int,
                            height: Int,
                        ) = Unit

                        override fun onSurfaceTextureDestroyed(surface: SurfaceTexture): Boolean {
                            mediaPrepared.set(false)
                            exoPlayer.clearVideoTextureView(this@apply)
                            return true
                        }

                        override fun onSurfaceTextureUpdated(surface: SurfaceTexture) = Unit
                    }
                    if (isAvailable) {
                        exoPlayer.setVideoTextureView(this)
                        mainExecutor.execute { prepareIfNeeded() }
                    }
                }
            },
            onRelease = { view ->
                mediaPrepared.set(false)
                exoPlayer.clearVideoTextureView(view as TextureView)
            },
            modifier = Modifier.aspectRatio(videoAspectRatio),
        )
    }
}
