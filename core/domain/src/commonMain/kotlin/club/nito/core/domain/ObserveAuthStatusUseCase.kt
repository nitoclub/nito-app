package club.nito.core.domain

import club.nito.core.data.AuthRepository
import club.nito.core.model.AuthStatus
import kotlinx.coroutines.flow.Flow

/**
 * 直近のスケジュールを取得するユースケース
 */
sealed interface ObserveAuthStatusUseCase {
    operator fun invoke(): Flow<AuthStatus>
}

class ObserveAuthStatusExecutor(
    private val authRepository: AuthRepository,
) : ObserveAuthStatusUseCase {
    override fun invoke(): Flow<AuthStatus> = authRepository.authStatus
}
