package club.nito.core.domain

import club.nito.core.data.AuthRepository
import club.nito.core.data.ParticipantRepository
import club.nito.core.model.ExecuteResult
import club.nito.core.model.participant.Participant
import club.nito.core.model.participant.ParticipantDeclaration
import club.nito.core.model.participant.ParticipantStatus
import club.nito.core.model.runExecuting

/**
 * 参加表明するユースケース
 */
public sealed interface ParticipateUseCase {
    public suspend operator fun invoke(scheduleId: String, status: ParticipantStatus): ExecuteResult<Participant>
}

public class ParticipateExecutor(
    private val authRepository: AuthRepository,
    private val participantRepository: ParticipantRepository,
) : ParticipateUseCase {
    override suspend fun invoke(scheduleId: String, status: ParticipantStatus): ExecuteResult<Participant> = runExecuting {
        val currentUserId = authRepository.currentUser().id
        val exist = participantRepository.existParticipantByUserId(
            scheduleId = scheduleId,
            userId = currentUserId,
        )

        if (exist) {
            participantRepository.updateParticipate(
                declaration = ParticipantDeclaration(
                    scheduleId = scheduleId,
                    userId = currentUserId,
                    status = status,
                ),
            )
        } else {
            participantRepository.insertParticipate(
                declaration = ParticipantDeclaration(
                    scheduleId = scheduleId,
                    userId = currentUserId,
                    status = status,
                ),
            )
        }
    }
}
