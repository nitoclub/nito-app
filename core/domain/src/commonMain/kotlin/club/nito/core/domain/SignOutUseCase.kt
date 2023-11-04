package club.nito.core.domain

import club.nito.core.data.AuthRepository
import club.nito.core.model.ExecuteResult
import club.nito.core.model.runExecuting

/**
 * 直近のスケジュールを取得するユースケース
 */
sealed interface SignOutUseCase {
    suspend operator fun invoke(): ExecuteResult<Unit>
}

class SignOutExecutor(
    private val authRepository: AuthRepository,
) : SignOutUseCase {
    override suspend fun invoke(): ExecuteResult<Unit> = runExecuting {
        authRepository.signOut()
    }
}
