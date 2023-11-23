package club.nito.core.network.auth

import club.nito.core.model.AuthStatus
import club.nito.core.model.FetchSingleResult
import club.nito.core.model.UserInfo
import kotlinx.coroutines.flow.Flow

public sealed interface AuthRemoteDataSource {
    public val authStatus: Flow<FetchSingleResult<AuthStatus>>

    public suspend fun signIn(email: String, password: String)
    public suspend fun signOut()
    public suspend fun modifyAuthUser(email: String?, password: String?): UserInfo
}
