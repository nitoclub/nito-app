package club.nito.core.domain

import club.nito.core.data.AuthRepository
import club.nito.core.model.ExecuteResult
import club.nito.core.model.UserInfo
import club.nito.core.model.runExecuting

/**
 * 直近のスケジュールを取得するユースケース
 */
sealed interface ModifyPasswordUseCase {
    suspend operator fun invoke(password: String): ExecuteResult<UserInfo>
}

class ModifyPasswordExecutor(
    private val authRepository: AuthRepository,
) : ModifyPasswordUseCase {
    override suspend fun invoke(password: String) = runExecuting {
        authRepository.modifyAuthUser(
            email = null,
            password = password,
        )
    }
}
