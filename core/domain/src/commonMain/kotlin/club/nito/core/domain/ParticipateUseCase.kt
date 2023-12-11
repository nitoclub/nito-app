package club.nito.core.domain

import club.nito.core.data.AuthRepository
import club.nito.core.data.ParticipantRepository
import club.nito.core.model.ExecuteResult
import club.nito.core.model.participant.Participant
import club.nito.core.model.participant.ParticipantStatus
import club.nito.core.model.toNitoError
import kotlin.coroutines.cancellation.CancellationException

/**
 * 参加表明するユースケース
 */
public sealed interface ParticipateUseCase {
    public suspend operator fun invoke(
        scheduleId: String,
        oldStatus: ParticipantStatus,
        newStatus: ParticipantStatus,
    ): ExecuteResult<Participant>
}

public class ParticipateExecutor(
    private val authRepository: AuthRepository,
    private val participantRepository: ParticipantRepository,
) : ParticipateUseCase {
    override suspend fun invoke(
        scheduleId: String,
        oldStatus: ParticipantStatus,
        newStatus: ParticipantStatus,
    ): ExecuteResult<Participant> {
        val currentUserId = authRepository.currentUser().id

        // NOTE: 失敗時の復元用キャッシュ
        val cachedParticipant = Participant(
            scheduleId = scheduleId,
            userId = currentUserId,
            status = oldStatus,
        )

        // NOTE: 成功可否に関わらず一旦選択した状態を反映する
        participantRepository.upsertLocalParticipate(
            participant = Participant(
                scheduleId = scheduleId,
                userId = currentUserId,
                status = newStatus,
            ),
        )

        try {
            val participant = participantRepository.upsertParticipate(
                participant = Participant(
                    scheduleId = scheduleId,
                    userId = currentUserId,
                    status = newStatus,
                ),
            )
            return ExecuteResult.Success(data = participant)
        } catch (e: Exception) {
            when (e) {
                is CancellationException -> throw e
                else -> {
                    // NOTE: 失敗時はキャッシュを復元する
                    participantRepository.upsertLocalParticipate(participant = cachedParticipant)
                    return ExecuteResult.Failure(error = e.toNitoError())
                }
            }
        }
    }
}
