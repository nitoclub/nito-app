package club.nito.core.domain

import club.nito.core.data.AuthRepository
import club.nito.core.model.AuthStatus
import club.nito.core.model.FetchSingleResult
import kotlinx.coroutines.flow.Flow

/**
 * 認証状態を購読するユースケース
 */
sealed interface ObserveAuthStatusUseCase {
    operator fun invoke(): Flow<FetchSingleResult<AuthStatus>>
}

class ObserveAuthStatusExecutor(
    private val authRepository: AuthRepository,
) : ObserveAuthStatusUseCase {
    override fun invoke(): Flow<FetchSingleResult<AuthStatus>> = authRepository.authStatus
}
