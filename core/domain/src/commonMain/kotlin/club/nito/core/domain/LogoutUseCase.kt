package club.nito.core.domain

import club.nito.core.data.AuthRepository
import club.nito.core.model.ExecuteResult
import club.nito.core.model.runExecuting

/**
 * ログアウトするユースケース
 */
public sealed interface LogoutUseCase {
    public suspend operator fun invoke(): ExecuteResult<Unit>
}

public class LogoutExecutor(
    private val authRepository: AuthRepository,
) : LogoutUseCase {
    override suspend fun invoke(): ExecuteResult<Unit> = runExecuting {
        authRepository.logout()
    }
}
