package club.nito.core.data

import club.nito.core.model.AuthStatus
import club.nito.core.model.FetchSingleResult
import kotlinx.coroutines.flow.Flow

/**
 * 認証に関するリポジトリ
 */
sealed interface AuthRepository {
    val authStatus: Flow<FetchSingleResult<AuthStatus>>

    suspend fun signIn(email: String, password: String)
    suspend fun signOut()
}
