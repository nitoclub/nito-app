package club.nito.core.data

import club.nito.core.model.AuthStatus
import kotlinx.coroutines.flow.Flow

/**
 * 認証に関するリポジトリ
 */
sealed interface AuthRepository {
    val authStatus: Flow<AuthStatus>
}
