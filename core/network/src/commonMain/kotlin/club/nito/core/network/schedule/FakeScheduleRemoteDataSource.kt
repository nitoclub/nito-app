package club.nito.core.network.schedule

import club.nito.core.model.Order
import club.nito.core.model.schedule.Schedule
import club.nito.core.model.schedule.ScheduleId
import club.nito.core.network.schedule.model.NetworkSchedule
import club.nito.core.network.schedule.model.createFakeNetworkSchedule
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus

public data object FakeScheduleRemoteDataSource : ScheduleRemoteDataSource {
    override suspend fun getScheduleList(
        limit: Int,
        order: Order,
        after: Instant?,
    ): List<Schedule> {
        val instant = Clock.System.now()
        val timeZone = TimeZone.currentSystemDefault()

        return (1..limit).map {
            createFakeNetworkSchedule(
                id = it.toString(),
                scheduledAt = instant.plus(1, DateTimeUnit.DAY, timeZone),
            )
        }.map(NetworkSchedule::toSchedule)
    }

    override suspend fun fetchSchedule(id: ScheduleId): Schedule {
        return createFakeNetworkSchedule(
            id = id,
            scheduledAt = Clock.System.now(),
        ).let(NetworkSchedule::toSchedule)
    }
}
