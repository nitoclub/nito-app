package club.nito.core.domain

import club.nito.core.data.AuthRepository
import club.nito.core.model.ExecuteResult
import club.nito.core.model.runExecuting

/**
 * ログインするユースケース
 */
public sealed interface LoginUseCase {
    public suspend operator fun invoke(email: String, password: String): ExecuteResult<Unit>
}

public class LoginExecutor(
    private val authRepository: AuthRepository,
) : LoginUseCase {
    override suspend fun invoke(email: String, password: String): ExecuteResult<Unit> = runExecuting {
        authRepository.login(
            email = email,
            password = password,
        )
    }
}
