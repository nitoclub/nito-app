package club.nito.core.network.schedule

import club.nito.core.model.Order
import club.nito.core.model.schedule.Schedule
import club.nito.core.model.schedule.ScheduleId
import kotlinx.datetime.Instant

public sealed interface ScheduleRemoteDataSource {
    public suspend fun getScheduleList(
        limit: Int,
        order: Order = Order.DESCENDING,
        after: Instant? = null,
    ): List<Schedule>

    /**
     * リモートからスケジュールを取得する
     */
    public suspend fun fetchSchedule(id: ScheduleId): Schedule
}
