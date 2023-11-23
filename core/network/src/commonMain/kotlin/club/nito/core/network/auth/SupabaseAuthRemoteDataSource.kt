package club.nito.core.network.auth

import club.nito.core.model.AuthStatus
import club.nito.core.model.FetchSingleResult
import club.nito.core.model.UserInfo
import club.nito.core.model.UserSession
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.gotrue.SessionStatus
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

public class SupabaseAuthRemoteDataSource(
    private val goTrue: GoTrue,
) : AuthRemoteDataSource {
    override val authStatus: Flow<FetchSingleResult<AuthStatus>> = goTrue.sessionStatus.map {
        when (it) {
            is SessionStatus.Authenticated -> FetchSingleResult.Success(
                AuthStatus.Authenticated(
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
                ),
            )

            is SessionStatus.NotAuthenticated -> FetchSingleResult.Success(AuthStatus.NotAuthenticated)
            is SessionStatus.LoadingFromStorage -> FetchSingleResult.Loading
            is SessionStatus.NetworkError -> FetchSingleResult.Failure(null)
        }
    }

    override suspend fun login(email: String, password: String): Unit = goTrue.loginWith(Email) {
        this.email = email
        this.password = password
    }

    override suspend fun logout(): Unit = goTrue.logout()

    override suspend fun modifyAuthUser(email: String?, password: String?): UserInfo = goTrue.modifyUser {
        this.email = email
        this.password = password
    }.let(SupabaseAuthRemoteDataSourceMapper::transformToUserInfo)
}
