package club.nito.core.data

import club.nito.core.model.Schedule
import kotlinx.coroutines.flow.Flow

/**
 * スケジュールに関するリポジトリ
 */
sealed interface ScheduleRepository {
    val scheduleListFlow: Flow<List<Schedule>>
    suspend fun getScheduleList(limit: Long): List<Schedule>
    fun scheduleFlow(id: String): Flow<Schedule>
}
