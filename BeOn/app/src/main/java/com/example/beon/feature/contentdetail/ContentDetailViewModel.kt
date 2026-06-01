package com.example.beon.feature.contentdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beon.data.repository.MovieRepositoryProvider
import com.example.beon.feature.home.ContentItem
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ContentDetailUiState(
    val isLoading: Boolean = true,
    val content: ContentDetail? = null,
    val relatedContent: List<ContentItem> = emptyList(),
    val errorMessage: String? = null,
)

class ContentDetailViewModel(
    private val contentId: String,
) : ViewModel() {
    private val repository = MovieRepositoryProvider.repository

    private val _uiState = MutableStateFlow(ContentDetailUiState())
    val uiState: StateFlow<ContentDetailUiState> = _uiState.asStateFlow()

    init {
        loadContent()
    }

    fun loadContent() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            val detailDeferred = async { repository.getMovieDetail(contentId) }
            val relatedDeferred = async {
                repository.getRecommendations(excludeContentId = contentId, limit = 12)
            }

            runCatching { detailDeferred.await() }
                .onSuccess { detail ->
                    val related = relatedDeferred.await()
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            content = detail,
                            relatedContent = related,
                            errorMessage = null,
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            content = null,
                            relatedContent = emptyList(),
                            errorMessage = error.message,
                        )
                    }
                }
        }
    }
}
