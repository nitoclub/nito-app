package club.nito.core.model

import kotlinx.coroutines.TimeoutCancellationException

public sealed class NitoError : RuntimeException {
    protected constructor(message: String?, cause: Throwable?) : super(message, cause)
    protected constructor(cause: Throwable?) : super(cause)
}

public sealed class ApiException(cause: Throwable?) : NitoError(cause = cause) {
    public class NetworkException(cause: Throwable?) : ApiException(cause = cause)
    public class ServerException(cause: Throwable?) : ApiException(cause = cause)
    public class TimeoutException(cause: Throwable?) : ApiException(cause = cause)
    public class SessionNotFoundException(cause: Throwable?) : NitoError(cause = cause)
    public class UnknownException(cause: Throwable?) : NitoError(cause = cause)
}

public class UnknownException(cause: Throwable?) : NitoError(cause = cause)

public fun Throwable.toNitoError(): NitoError {
    return when (this) {
        is NitoError -> this
        is TimeoutCancellationException,
        -> ApiException.TimeoutException(this)

        else -> UnknownException(this)
    }
}
