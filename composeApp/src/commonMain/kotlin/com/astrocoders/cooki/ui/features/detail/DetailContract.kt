package com.astrocoders.cooki.ui.features.detail

data class DetailState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val id: String = ""
)

sealed interface DetailIntent {
    data object Load : DetailIntent
    data object OnBackClicked : DetailIntent
}
