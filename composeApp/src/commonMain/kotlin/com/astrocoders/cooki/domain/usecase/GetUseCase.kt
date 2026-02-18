package com.astrocoders.cooki.domain.usecase

import com.astrocoders.cooki.domain.model.DataError
import com.astrocoders.cooki.domain.model.DataResult
import com.astrocoders.cooki.domain.repository.ExampleRepository

class GetExampleUseCase(
    private val repository: ExampleRepository
) {
    suspend operator fun invoke(): DataResult<Unit, DataError> {
        return repository.getExample()
    }
}