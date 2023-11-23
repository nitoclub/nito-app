package club.nito.core.domain

import club.nito.core.data.AuthRepository
import club.nito.core.model.ExecuteResult
import club.nito.core.model.runExecuting

/**
 * 直近のスケジュールを取得するユースケース
 */
public sealed interface SignOutUseCase {
    public suspend operator fun invoke(): ExecuteResult<Unit>
}

public class SignOutExecutor(
    private val authRepository: AuthRepository,
) : SignOutUseCase {
    override suspend fun invoke(): ExecuteResult<Unit> = runExecuting {
        authRepository.signOut()
    }
}
