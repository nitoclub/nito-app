package club.nito.core.network.auth

import club.nito.core.model.ApiException
import club.nito.core.model.AuthStatus
import club.nito.core.model.UserInfo
import club.nito.core.model.UserSession
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.SessionStatus
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock

public class SupabaseAuthRemoteDataSource(
    private val goTrue: Auth,
) : AuthRemoteDataSource {
    override val authStatus: Flow<AuthStatus> = goTrue.sessionStatus.map {
        when (it) {
            is SessionStatus.Authenticated -> AuthStatus.Authenticated(
                session = UserSession(
                    accessToken = it.session.accessToken,
                    refreshToken = it.session.refreshToken,
                    providerRefreshToken = it.session.providerRefreshToken,
                    providerToken = it.session.providerToken,
                    expiresIn = it.session.expiresIn,
                    tokenType = it.session.tokenType,
                    user = it.session.user?.let(SupabaseAuthRemoteDataSourceMapper::transformToUserInfo),
                    type = it.session.type,
                    expiresAt = it.session.expiresAt,
                ),
            )

            is SessionStatus.NotAuthenticated -> AuthStatus.NotAuthenticated
            is SessionStatus.LoadingFromStorage -> AuthStatus.Loading
            is SessionStatus.NetworkError -> AuthStatus.NetworkError
        }
    }

    override suspend fun login(email: String, password: String): Unit = goTrue.signInWith(Email) {
        this.email = email
        this.password = password
    }

    override suspend fun logout(): Unit = goTrue.signOut()

    override suspend fun modifyAuthUser(email: String?, password: String?): UserInfo = goTrue.modifyUser {
        this.email = email
        this.password = password
    }.let(SupabaseAuthRemoteDataSourceMapper::transformToUserInfo)

    override suspend fun authIfNeeded() {
        val session = goTrue.currentSessionOrNull() ?: throw ApiException.SessionNotFoundException(cause = null)

        if (session.expiresAt <= Clock.System.now()) {
            goTrue.refreshCurrentSession()
        }
    }

    override suspend fun refreshCurrentSession(): Unit = goTrue.refreshCurrentSession()

    override suspend fun currentUserOrNull(): UserInfo? {
        return goTrue.currentUserOrNull()?.let(SupabaseAuthRemoteDataSourceMapper::transformToUserInfo)
    }
}
