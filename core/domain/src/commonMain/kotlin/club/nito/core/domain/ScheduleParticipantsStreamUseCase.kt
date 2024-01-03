package club.nito.core.domain

import club.nito.core.data.ParticipantRepository
import club.nito.core.model.FetchMultipleContentResult
import club.nito.core.model.participant.ParticipantUser
import club.nito.core.model.schedule.ScheduleId
import club.nito.core.model.toNitoError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlin.coroutines.cancellation.CancellationException

/**
 * 該当スケジュールの参加者情報のストリームを取得するユースケース
 */
public sealed interface ScheduleParticipantsStreamUseCase {
    public operator fun invoke(id: ScheduleId): Flow<FetchMultipleContentResult<ParticipantUser>>
}

public class ScheduleParticipantsStreamExecutor(
    private val participantRepository: ParticipantRepository,
) : ScheduleParticipantsStreamUseCase {
    override fun invoke(id: ScheduleId): Flow<FetchMultipleContentResult<ParticipantUser>> {
        return participantRepository
            .participantUsersStream(scheduleId = id)
            .map {
                if (it.isEmpty()) {
                    return@map FetchMultipleContentResult.NoContent
                }

                FetchMultipleContentResult.Success(it)
            }
            .catch { e ->
                if (e is CancellationException) {
                    throw e
                }

                emit(FetchMultipleContentResult.Failure(e.toNitoError()))
            }
    }
}
