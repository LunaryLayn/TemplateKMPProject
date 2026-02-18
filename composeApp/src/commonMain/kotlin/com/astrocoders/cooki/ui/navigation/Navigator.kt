package com.astrocoders.cooki.ui.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class Navigator {

    private val _actions = MutableSharedFlow<NavigationAction>()
    val actions = _actions.asSharedFlow()

    suspend fun push(screen: AppScreens) {
        _actions.emit(NavigationAction.Push(screen))
    }

    suspend fun pop() {
        _actions.emit(NavigationAction.Pop)
    }

    suspend fun up() {
        _actions.emit(NavigationAction.Up)
    }
}
