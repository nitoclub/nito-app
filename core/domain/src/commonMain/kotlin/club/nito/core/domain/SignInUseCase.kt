package club.nito.core.domain

import club.nito.core.data.AuthRepository

/**
 * 直近のスケジュールを取得するユースケース
 */
sealed interface SignInUseCase {
    suspend operator fun invoke(email: String, password: String)
}

class SignInExecutor(
    private val authRepository: AuthRepository,
) : SignInUseCase {
    override suspend fun invoke(email: String, password: String) = authRepository.signIn(
        email = email,
        password = password,
    )
}
