package club.nito.core.model

import kotlinx.coroutines.TimeoutCancellationException

sealed class NitoError : RuntimeException {
    constructor(message: String?, cause: Throwable?) : super(message, cause)
    constructor(cause: Throwable?) : super(cause)
}

sealed class ApiException(cause: Throwable?) : NitoError(cause = cause) {
    class NetworkException(cause: Throwable?) : ApiException(cause = cause)
    class ServerException(cause: Throwable?) : ApiException(cause = cause)
    class TimeoutException(cause: Throwable?) : ApiException(cause = cause)
    class SessionNotFoundException(cause: Throwable?) : NitoError(cause = cause)
    class UnknownException(cause: Throwable?) : NitoError(cause = cause)
}

class UnknownException(cause: Throwable?) : NitoError(cause = cause)

fun Throwable.toNitoError(): NitoError {
    return when (this) {
        is NitoError -> this
        is TimeoutCancellationException,
        -> ApiException.TimeoutException(this)

        else -> UnknownException(this)
    }
}
