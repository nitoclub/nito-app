package club.nito.core.domain

import club.nito.core.data.ParticipantRepository
import club.nito.core.model.ExecuteResult

/**
 * 参加表明するユースケース
 */
sealed interface ParticipateUseCase {
    suspend operator fun invoke(scheduleId: String, comment: String): ExecuteResult<Unit>
}

class ParticipateExecutor(
    private val participantRepository: ParticipantRepository,
) : ParticipateUseCase {
    override suspend fun invoke(scheduleId: String, comment: String): ExecuteResult<Unit> {
        TODO("Not yet implemented")
    }
}
