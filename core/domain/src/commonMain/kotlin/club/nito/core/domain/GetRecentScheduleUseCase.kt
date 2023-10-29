package club.nito.core.domain

import club.nito.core.data.ScheduleRepository
import club.nito.core.model.FetchSingleResult
import club.nito.core.model.Schedule
import club.nito.core.model.toNitoError

/**
 * 直近のスケジュールを取得するユースケース
 */
sealed interface GetRecentScheduleUseCase {
    suspend operator fun invoke(): FetchSingleResult<Schedule>
}

class GetRecentScheduleExecutor(
    private val scheduleRepository: ScheduleRepository,
) : GetRecentScheduleUseCase {
    override suspend fun invoke(): FetchSingleResult<Schedule> {
        val data = try {
            scheduleRepository.getScheduleList(limit = 1)
        } catch (e: Throwable) {
            return FetchSingleResult.Failure(error = e.toNitoError())
        }

        if (data.isEmpty()) {
            return FetchSingleResult.NoContent
        }
        return FetchSingleResult.Success(data.first())
    }
}
