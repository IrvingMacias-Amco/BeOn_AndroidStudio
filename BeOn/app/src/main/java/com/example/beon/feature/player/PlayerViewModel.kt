package com.example.beon.feature.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beon.core.mux.MuxStreaming
import com.example.beon.data.repository.MovieRepositoryProvider
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class PlayerUiState(
    val isLoading: Boolean = true,
    val title: String = "",
    val posterUrl: String = "",
    val streamUrl: String? = null,
    val recommendations: List<PlayerRecommendation> = emptyList(),
    val errorMessage: String? = null,
)

class PlayerViewModel(
    private val contentId: String,
) : ViewModel() {
    private val repository = MovieRepositoryProvider.repository

    private val _uiState = MutableStateFlow(PlayerUiState())
    val uiState: StateFlow<PlayerUiState> = _uiState.asStateFlow()

    init {
        loadContent()
    }

    private fun loadContent() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            val detailDeferred = async { repository.getMovieDetail(contentId) }
            val recommendationsDeferred = async {
                repository.getRecommendations(excludeContentId = contentId, limit = 10)
            }

            val detailResult = runCatching { detailDeferred.await() }
            val recommendationsResult = runCatching { recommendationsDeferred.await() }

            detailResult
                .onSuccess { detail ->
                    val streamUrl = detail.muxPlaybackId?.let(MuxStreaming::hlsUrl)
                    val posterUrl = detail.imageUrl.ifBlank {
                        detail.muxPlaybackId?.let { MuxStreaming.thumbnailUrl(it) }.orEmpty()
                    }
                    val recommendations = recommendationsResult
                        .getOrDefault(emptyList())
                        .map { it.toPlayerRecommendation() }
                        .filter { it.imageUrl.isNotBlank() }

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            title = detail.title,
                            posterUrl = posterUrl,
                            streamUrl = streamUrl,
                            recommendations = recommendations,
                            errorMessage = null,
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = error.message,
                        )
                    }
                }
        }
    }
}
