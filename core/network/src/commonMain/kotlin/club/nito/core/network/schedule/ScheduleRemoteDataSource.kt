package club.nito.core.network.schedule

import club.nito.core.model.Order
import club.nito.core.model.Schedule
import kotlinx.datetime.Instant

public sealed interface ScheduleRemoteDataSource {
    public suspend fun getScheduleList(
        limit: Int,
        order: Order = Order.DESCENDING,
        after: Instant? = null,
    ): List<Schedule>

    public suspend fun getSchedule(id: String): Schedule
}
