package club.nito.core.domain

import club.nito.core.data.AuthRepository
import club.nito.core.data.ParticipantRepository
import club.nito.core.model.participant.ParticipantStatus
import club.nito.core.model.schedule.ScheduleId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * 自身の参加状況を取得するユースケース
 */
public sealed interface MyParticipantStatusStreamUseCase {
    public operator fun invoke(id: ScheduleId): Flow<ParticipantStatus>
}

public class FetchMyParticipantStatusExecutor(
    private val authRepository: AuthRepository,
    private val participantRepository: ParticipantRepository,
) : MyParticipantStatusStreamUseCase {
    override fun invoke(id: ScheduleId): Flow<ParticipantStatus> = flow {
        val currentUserId = authRepository.currentUser().id
        participantRepository.participantStatusStream(
            scheduleId = id,
            userId = currentUserId,
        ).collect {
            emit(it)
        }
    }
}
