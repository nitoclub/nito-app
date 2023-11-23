package club.nito.core.domain

import club.nito.core.data.AuthRepository
import club.nito.core.model.ExecuteResult
import club.nito.core.model.UserInfo
import club.nito.core.model.runExecuting

/**
 * 直近のスケジュールを取得するユースケース
 */
public sealed interface ModifyPasswordUseCase {
    public suspend operator fun invoke(password: String): ExecuteResult<UserInfo>
}

public class ModifyPasswordExecutor(
    private val authRepository: AuthRepository,
) : ModifyPasswordUseCase {
    override suspend fun invoke(password: String): ExecuteResult<UserInfo> = runExecuting {
        authRepository.modifyAuthUser(
            email = null,
            password = password,
        )
    }
}
