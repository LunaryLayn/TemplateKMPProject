package com.astrocoders.cooki.ui.features

import androidx.lifecycle.coroutineScope
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.astrocoders.cooki.ui.navigation.AppScreens
import com.astrocoders.cooki.ui.navigation.Navigator
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.contracts.Effect

abstract class BaseComponent<State, Intent>(
    componentContext: ComponentContext
) : KoinComponent {

    protected val scope = componentContext.coroutineScope()

    protected val navigator: Navigator by inject()

    abstract fun reduce(intent: Intent)

    abstract val state: StateFlow<State>

    protected fun navigate(screen: AppScreens) {
        scope.launch {
            navigator.push(screen)
        }
    }

    protected fun navigatePop() {
        scope.launch {
            navigator.pop()
        }
    }

    protected fun navigateBack() {
        scope.launch {
            navigator.up()
        }
    }
}
