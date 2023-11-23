package club.nito.core.domain

import club.nito.core.data.AuthRepository
import club.nito.core.model.AuthStatus
import club.nito.core.model.FetchSingleResult
import kotlinx.coroutines.flow.Flow

/**
 * 認証状態を購読するユースケース
 */
public sealed interface ObserveAuthStatusUseCase {
    public operator fun invoke(): Flow<FetchSingleResult<AuthStatus>>
}

public class ObserveAuthStatusExecutor(
    private val authRepository: AuthRepository,
) : ObserveAuthStatusUseCase {
    override fun invoke(): Flow<FetchSingleResult<AuthStatus>> = authRepository.authStatus
}
