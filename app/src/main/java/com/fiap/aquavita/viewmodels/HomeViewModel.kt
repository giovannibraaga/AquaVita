package com.fiap.aquavita.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fiap.aquavita.models.Article
import com.fiap.aquavita.models.provideNewsService
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor() : ViewModel() {

    private val service = provideNewsService()

    var uiState by mutableStateOf(HomeUiState())
        private set

    init { fetchNews() }

    fun fetchNews() = viewModelScope.launch {
        uiState = uiState.copy(loading = true, error = null)
        runCatching { service.getWaterNews() }
            .onSuccess { res -> uiState = uiState.copy(loading = false, articles = res.articles) }
            .onFailure { e   -> uiState = uiState.copy(loading = false, error = e.localizedMessage) }
    }
}

data class HomeUiState(
    val loading: Boolean = false,
    val articles: List<Article> = emptyList(),
    val error: String? = null
)