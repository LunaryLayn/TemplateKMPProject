package com.astrocoders.cooki.domain.repository

import com.astrocoders.cooki.domain.model.DataError
import com.astrocoders.cooki.domain.model.DataResult

interface ExampleRepository {
    suspend fun getExample(): DataResult<Unit, DataError>
}