package club.nito.core.network.auth

import club.nito.core.model.AuthStatus
import club.nito.core.model.FetchSingleResult
import kotlinx.coroutines.flow.Flow

sealed interface AuthRemoteDataSource {
    val authStatus: Flow<FetchSingleResult<AuthStatus>>

    suspend fun signIn(email: String, password: String)
    suspend fun signOut()
}
