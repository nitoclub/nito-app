package club.nito.core.network.auth

import club.nito.core.model.AuthStatus
import club.nito.core.model.FetchSingleResult
import club.nito.core.model.UserInfo
import club.nito.core.model.UserSession
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class FakeAuthRemoteDataSource(
    coroutineScope: CoroutineScope,
) : AuthRemoteDataSource {

    private val _authStatus = MutableStateFlow<FetchSingleResult<AuthStatus>>(FetchSingleResult.Loading)
    override val authStatus: Flow<FetchSingleResult<AuthStatus>> = _authStatus

    init {
        coroutineScope.launch {
            delay(1000)

            _authStatus.value = FetchSingleResult.Success(
                AuthStatus.Authenticated(
                    session = authenticatedUserSession,
                ),
            )
        }
    }

    private val authenticatedUserSession = UserSession(
        accessToken = "accessToken",
        refreshToken = "refreshToken",
        providerRefreshToken = "providerRefreshToken",
        providerToken = "providerToken",
        expiresIn = 360000,
        tokenType = "tokenType",
        user = UserInfo(
            aud = "aud",
            confirmationSentAt = null,
            confirmedAt = null,
            createdAt = null,
            email = "email",
            emailConfirmedAt = null,
            factors = emptyList(),
            id = "id",
            lastSignInAt = null,
            phone = null,
            role = null,
            updatedAt = null,
            emailChangeSentAt = null,
            newEmail = null,
            invitedAt = null,
            recoverySentAt = null,
            phoneConfirmedAt = null,
            actionLink = null,
        ),
        type = "type",
    )

    override suspend fun signIn(email: String, password: String) = _authStatus.emit(
        FetchSingleResult.Success(
            AuthStatus.Authenticated(session = authenticatedUserSession),
        ),
    )

    override suspend fun signOut() = _authStatus.emit(
        FetchSingleResult.Success(AuthStatus.NotAuthenticated),
    )
}
