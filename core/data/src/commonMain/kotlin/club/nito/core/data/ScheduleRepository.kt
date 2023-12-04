package club.nito.core.data

import club.nito.core.model.Order
import club.nito.core.model.schedule.Schedule
import club.nito.core.model.schedule.ScheduleId
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Instant

/**
 * スケジュールに関するリポジトリ
 */
public sealed interface ScheduleRepository {
    public val scheduleListFlow: Flow<List<Schedule>>
    public suspend fun getScheduleList(
        limit: Int,
        order: Order = Order.DESCENDING,
        after: Instant? = null,
    ): List<Schedule>

    /**
     * スケジュールを取得する
     */
    public suspend fun fetchSchedule(id: ScheduleId): Schedule
}
