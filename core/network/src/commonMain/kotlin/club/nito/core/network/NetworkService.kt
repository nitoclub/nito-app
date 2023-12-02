package club.nito.core.network

import club.nito.core.model.ApiException
import club.nito.core.model.NitoError
import club.nito.core.network.auth.AuthRemoteDataSource
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.ResponseException
import io.ktor.util.cio.ChannelReadException
import kotlinx.coroutines.TimeoutCancellationException

public class NetworkService(
    public val authRemoteDataSource: AuthRemoteDataSource,
) {
    public suspend inline operator fun <reified T : Any> invoke(
        block: () -> T,
    ): T = try {
        authRemoteDataSource.authIfNeeded()
        block()
    } catch (e: Throwable) {
        throw e.toNitoError()
    }
}

public fun Throwable.toNitoError(): NitoError = when (this) {
    is NitoError -> this

    is ResponseException -> ApiException.ServerException(this)

    is ChannelReadException -> ApiException.NetworkException(this)

    is TimeoutCancellationException,
    is HttpRequestTimeoutException,
    is SocketTimeoutException,
    -> ApiException.TimeoutException(this)

    else -> ApiException.UnknownException(this)
}
