package club.nito.core.domain

import club.nito.core.data.AuthRepository
import club.nito.core.data.ParticipantRepository
import club.nito.core.model.ExecuteResult
import club.nito.core.model.participant.ParticipantDeclaration
import club.nito.core.model.runExecuting

/**
 * 参加表明するユースケース
 */
public sealed interface ParticipateUseCase {
    public suspend operator fun invoke(scheduleId: String, comment: String): ExecuteResult<Unit>
}

public class ParticipateExecutor(
    private val authRepository: AuthRepository,
    private val participantRepository: ParticipantRepository,
) : ParticipateUseCase {
    override suspend fun invoke(scheduleId: String, comment: String): ExecuteResult<Unit> = runExecuting {
        participantRepository.participate(
            declaration = ParticipantDeclaration(
                scheduleId = scheduleId,
                memberId = authRepository.currentUser().id,
                comment = comment,
            ),
        )
    }
}
