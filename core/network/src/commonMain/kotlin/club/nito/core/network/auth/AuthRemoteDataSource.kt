package club.nito.core.network.auth

import club.nito.core.model.AuthStatus
import club.nito.core.model.UserInfo
import kotlinx.coroutines.flow.Flow

public sealed interface AuthRemoteDataSource {
    public val authStatus: Flow<AuthStatus>

    public suspend fun login(email: String, password: String)
    public suspend fun logout()
    public suspend fun modifyAuthUser(email: String?, password: String?): UserInfo
    public suspend fun authIfNeeded()
    public suspend fun refreshCurrentSession()
}
