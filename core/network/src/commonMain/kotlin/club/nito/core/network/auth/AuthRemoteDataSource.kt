package club.nito.core.network.auth

import club.nito.core.model.AuthStatus
import kotlinx.coroutines.flow.Flow

sealed interface AuthRemoteDataSource {
    val authStatus: Flow<AuthStatus>

    suspend fun signIn(email: String, password: String)
    suspend fun signOut()
}
