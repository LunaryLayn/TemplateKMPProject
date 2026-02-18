package com.astrocoders.cooki.ui.features.home

data class HomeState(
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed interface HomeIntent {
    data object Load : HomeIntent
    data class NavigateToDetail(val id: String) : HomeIntent
    data object OnBackClicked : HomeIntent
}
