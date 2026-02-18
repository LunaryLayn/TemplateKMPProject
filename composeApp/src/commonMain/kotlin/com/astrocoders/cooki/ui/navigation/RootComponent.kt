package com.astrocoders.cooki.ui.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DelicateDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.astrocoders.cooki.ui.features.detail.DetailComponent
import com.astrocoders.cooki.ui.features.home.HomeComponent
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@OptIn(DelicateDecomposeApi::class)
class RootComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext,
    KoinComponent {

    private val navigation = StackNavigation<AppScreens>()
    private val scope = coroutineScope()

    private val navigator: Navigator by inject()

    init {
        scope.launch {
            navigator.actions.collect { action ->
                when (action) {

                    is NavigationAction.Push ->
                        navigation.push(action.screen)

                    NavigationAction.Pop ->
                        navigation.pop()

                    NavigationAction.Up ->
                        navigation.pop()
                }
            }
        }
    }

    val childStack: Value<ChildStack<AppScreens, Child>> =
        childStack(
            source = navigation,
            serializer = AppScreens.serializer(),
            initialConfiguration = AppScreens.Home,
            handleBackButton = true,
            childFactory = ::createChild
        )

    private fun createChild(
        config: AppScreens,
        context: ComponentContext
    ): Child =
        when (config) {

            is AppScreens.Home ->
                Child.Home(HomeComponent(context))

            is AppScreens.Detail ->
                Child.Detail(
                    DetailComponent(
                        componentContext = context,
                        id = config.id
                    )
                )
        }

    sealed class Child {
        data class Home(val component: HomeComponent) : Child()
        data class Detail(val component: DetailComponent) : Child()
    }
}
