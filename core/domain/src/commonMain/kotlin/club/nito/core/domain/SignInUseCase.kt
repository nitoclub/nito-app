package club.nito.core.domain

import club.nito.core.data.AuthRepository
import club.nito.core.model.ExecuteResult
import club.nito.core.model.runExecuting

/**
 * 直近のスケジュールを取得するユースケース
 */
public sealed interface SignInUseCase {
    public suspend operator fun invoke(email: String, password: String): ExecuteResult<Unit>
}

public class SignInExecutor(
    private val authRepository: AuthRepository,
) : SignInUseCase {
    override suspend fun invoke(email: String, password: String): ExecuteResult<Unit> = runExecuting {
        authRepository.signIn(
            email = email,
            password = password,
        )
    }
}
