package com.astrocoders.cooki.data.repository

import com.astrocoders.cooki.domain.repository.ExampleRepository
import com.astrocoders.cooki.domain.model.DataError
import com.astrocoders.cooki.domain.model.DataResult

class ExampleRepositoryImpl : ExampleRepository {
    override suspend fun getExample(): DataResult<Unit, DataError> {
        TODO("Not yet implemented")
    }

}