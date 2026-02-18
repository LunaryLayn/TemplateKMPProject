package com.astrocoders.cooki.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface AppScreens {

    @Serializable
    data object Home : AppScreens

    @Serializable
    data class Detail(val id: String) : AppScreens
}