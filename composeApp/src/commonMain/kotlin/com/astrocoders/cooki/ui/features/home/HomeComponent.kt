package com.astrocoders.cooki.ui.features.home

import com.arkivanov.decompose.ComponentContext
import com.astrocoders.cooki.domain.model.DataResult
import com.astrocoders.cooki.domain.usecase.GetExampleUseCase
import com.astrocoders.cooki.ui.features.BaseComponent
import com.astrocoders.cooki.ui.navigation.AppScreens
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeComponent(
    componentContext: ComponentContext
) : BaseComponent<HomeState, HomeIntent>(componentContext),
    KoinComponent {

    private val useCase: GetExampleUseCase by inject()

    private val _state = MutableStateFlow(HomeState())
    override val state: StateFlow<HomeState> = _state.asStateFlow()

    override fun reduce(intent: HomeIntent) {
        when (intent) {

            HomeIntent.Load -> load()

            HomeIntent.OnBackClicked -> {
                navigateBack()
            }

            is HomeIntent.NavigateToDetail -> {
                navigate(AppScreens.Detail(intent.id))
            }
        }
    }

    private fun load() {
        scope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            when (val result = useCase()) {

                is DataResult.Success -> {
                    _state.update { it.copy(isLoading = false) }
                }

                is DataResult.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = result.error.toString()
                        )
                    }
                }
            }
        }
    }
}
