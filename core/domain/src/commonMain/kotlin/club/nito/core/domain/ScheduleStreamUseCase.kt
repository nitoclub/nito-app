package club.nito.core.domain

import club.nito.core.data.ScheduleRepository
import club.nito.core.model.FetchSingleContentResult
import club.nito.core.model.schedule.ScheduleId
import club.nito.core.model.schedule.ScheduleWithPlace
import club.nito.core.model.toNitoError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlin.coroutines.cancellation.CancellationException

/**
 * スケジュールを取得するユースケース
 */
public sealed interface ScheduleStreamUseCase {
    public operator fun invoke(id: ScheduleId): Flow<FetchSingleContentResult<ScheduleWithPlace>>
}

public class ScheduleStreamExecutor(
    private val scheduleRepository: ScheduleRepository,
) : ScheduleStreamUseCase {
    override fun invoke(id: ScheduleId): Flow<FetchSingleContentResult<ScheduleWithPlace>> {
        return scheduleRepository
            .scheduleWithPlaceStream(id = id)
            .map {
                it?.let { FetchSingleContentResult.Success(it) }
                    ?: FetchSingleContentResult.NoContent
            }
            .catch { e ->
                if (e is CancellationException) {
                    throw e
                }

                emit(FetchSingleContentResult.Failure(e.toNitoError()))
            }
    }
}
