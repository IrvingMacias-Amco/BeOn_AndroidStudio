package com.example.beon.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beon.data.repository.HomeCatalog
import com.example.beon.data.repository.MovieRepositoryProvider
import com.example.beon.data.repository.mockHomeCatalog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class HomeUiState(
    val isLoading: Boolean = true,
    val catalog: HomeCatalog = mockHomeCatalog(),
    val errorMessage: String? = null,
)

class HomeViewModel : ViewModel() {
    private val repository = MovieRepositoryProvider.repository

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadHome()
    }

    fun loadHome() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            runCatching { repository.getHomeCatalog() }
                .onSuccess { catalog ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            catalog = catalog,
                            errorMessage = null,
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            catalog = mockHomeCatalog(),
                            errorMessage = error.message,
                        )
                    }
                }
        }
    }
}
