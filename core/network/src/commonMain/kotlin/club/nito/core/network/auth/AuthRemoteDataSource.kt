package club.nito.core.network.auth

import club.nito.core.model.AuthStatus
import club.nito.core.model.FetchSingleResult
import club.nito.core.model.UserInfo
import kotlinx.coroutines.flow.Flow

sealed interface AuthRemoteDataSource {
    val authStatus: Flow<FetchSingleResult<AuthStatus>>

    suspend fun signIn(email: String, password: String)
    suspend fun signOut()
    suspend fun modifyAuthUser(email: String?, password: String?): UserInfo
}
