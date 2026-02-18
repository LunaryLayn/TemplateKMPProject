package com.astrocoders.cooki.domain.model

sealed interface RootError

sealed class DataResult<out D, out E : RootError> {
    data class Success<out D, out E : RootError>(val data: D) : DataResult<D, E>()
    data class Error<out D, out E : RootError>(val error: E) : DataResult<D, E>()
}

sealed interface DataError : RootError {

    sealed class Generic(val code: Int? = null) : DataError {
        data object NetworkError : Generic(null)
        data object ServerError : Generic(500)
        data object UnknownError : Generic(600)
        data object NoData : Generic(null)
        data object BadRequest : Generic(400)
        data object Unauthorized : Generic(401)
        data object Forbidden : Generic(403)
        data object NotFound : Generic(404)
        data object Conflict : Generic(409)
        data object Timeout : Generic(null)

        // Errores internos de parsing / mapping → asignamos "500" como estándar
        data object DeserializationError : Generic(550)
        data object MappingError : Generic(551)
        data object Canceled : Generic(null)
    }

    sealed class UserAuth(val code: Int? = null) : DataError {
        data object InvalidCredentials : UserAuth(401)
        data object EmptyFields : UserAuth()
        data object WrongVerificationCode : UserAuth(401)
        data object TooManyVerificationAttempts : UserAuth(429)
        data object UserAlreadyExists : UserAuth(208)
        data object UserNotVerified : UserAuth()
        data object UserDoesNotExist : UserAuth(401)
    }

    sealed class Match(val code: Int? = null) : DataError {
        data object InvalidDate : Match(418)
        data object CourtUnavailable : Match(422)
        data object CourtUnavailableHalfConflict : Match(423)
        data object CourtUnavailableFullConflict : Match(424)
    }
    sealed class HomeStory(val code: Int? = null) : DataError {
        data object AlreadyRequestedToday : HomeStory()
    }
}