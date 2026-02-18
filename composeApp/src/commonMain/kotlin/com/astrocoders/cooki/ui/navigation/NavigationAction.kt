package com.astrocoders.cooki.ui.navigation

sealed interface NavigationAction {
    data class Push(val screen: AppScreens) : NavigationAction
    data object Pop : NavigationAction
    data object Up : NavigationAction
}
