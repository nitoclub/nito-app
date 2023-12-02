package club.nito.core.domain

import club.nito.core.data.AuthRepository
import club.nito.core.model.AuthStatus
import kotlinx.coroutines.flow.Flow

/**
 * 認証状態を購読するユースケース
 */
public sealed interface AuthStatusStreamUseCase {
    public operator fun invoke(): Flow<AuthStatus>
}

public class AuthStatusStreamExecutor(
    private val authRepository: AuthRepository,
) : AuthStatusStreamUseCase {
    override fun invoke(): Flow<AuthStatus> = authRepository.authStatus
}
