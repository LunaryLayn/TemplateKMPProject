package com.astrocoders.cooki.ui.features.detail

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.astrocoders.cooki.domain.model.DataResult
import com.astrocoders.cooki.domain.usecase.GetExampleUseCase
import com.astrocoders.cooki.ui.features.BaseComponent
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DetailComponent(
    componentContext: ComponentContext,
    id : String
) : BaseComponent<DetailState, DetailIntent>(componentContext),
    KoinComponent {

    private val useCase: GetExampleUseCase by inject()

    private val _state = MutableStateFlow(DetailState(id = id))
    override val state: StateFlow<DetailState> = _state.asStateFlow()

    override fun reduce(intent: DetailIntent) {
        when (intent) {

            DetailIntent.Load -> load()

            DetailIntent.OnBackClicked -> {
                navigateBack()
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
