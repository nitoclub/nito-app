package club.nito.core.data

import club.nito.core.model.Order
import club.nito.core.model.Schedule
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Instant

/**
 * スケジュールに関するリポジトリ
 */
sealed interface ScheduleRepository {
    val scheduleListFlow: Flow<List<Schedule>>
    suspend fun getScheduleList(
        limit: Int,
        order: Order = Order.DESCENDING,
        after: Instant? = null,
    ): List<Schedule>

    fun scheduleFlow(id: String): Flow<Schedule>
}
