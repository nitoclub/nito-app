package club.nito.core.domain

import club.nito.core.data.AuthRepository
import club.nito.core.data.ParticipantRepository
import club.nito.core.model.FetchSingleContentResult
import club.nito.core.model.participant.ParticipantStatus
import club.nito.core.model.runFetchSingleContent
import club.nito.core.model.schedule.ScheduleId

/**
 * 自身の参加状況を取得するユースケース
 */
public sealed interface FetchMyParticipantStatusUseCase {
    public suspend operator fun invoke(id: ScheduleId): FetchSingleContentResult<ParticipantStatus>
}

public class FetchMyParticipantStatusExecutor(
    private val authRepository: AuthRepository,
    private val participantRepository: ParticipantRepository,
) : FetchMyParticipantStatusUseCase {
    override suspend fun invoke(id: ScheduleId): FetchSingleContentResult<ParticipantStatus> =
        runFetchSingleContent {
            val currentUserId = authRepository.currentUser().id
            participantRepository.fetchParticipantStatus(
                scheduleId = id,
                userId = currentUserId,
            )
        }
}
