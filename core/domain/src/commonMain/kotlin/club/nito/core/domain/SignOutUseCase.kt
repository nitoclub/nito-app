package club.nito.core.domain

import club.nito.core.data.AuthRepository

/**
 * 直近のスケジュールを取得するユースケース
 */
sealed interface SignOutUseCase {
    suspend operator fun invoke()
}

class SignOutExecutor(
    private val authRepository: AuthRepository,
) : SignOutUseCase {
    override suspend fun invoke() = authRepository.signOut()
}
