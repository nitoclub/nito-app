package club.nito.core.domain

import club.nito.core.data.ScheduleRepository
import club.nito.core.model.FetchSingleResult
import club.nito.core.model.Schedule
import club.nito.core.model.toNitoError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * 直近のスケジュールを取得するユースケース
 */
sealed interface GetRecentScheduleUseCase {
    operator fun invoke(): Flow<FetchSingleResult<Schedule>>
}

class GetRecentScheduleExecutor(
    private val scheduleRepository: ScheduleRepository,
) : GetRecentScheduleUseCase {
    override fun invoke(): Flow<FetchSingleResult<Schedule>> = flow {
        val data = try {
            scheduleRepository.getScheduleList(limit = 1)
        } catch (e: Throwable) {
            emit(FetchSingleResult.Failure(error = e.toNitoError()))
            return@flow
        }

        if (data.isEmpty()) {
            emit(FetchSingleResult.NoContent)
            return@flow
        }
        emit(FetchSingleResult.Success(data.first()))
    }
}
