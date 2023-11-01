package club.nito.core.network.schedule

import club.nito.core.model.Schedule
import club.nito.core.network.schedule.model.NetworkSchedule
import club.nito.core.network.schedule.model.createFakeNetworkSchedule
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus

data object FakeScheduleRemoteDataSource : ScheduleRemoteDataSource {
    override suspend fun getScheduleList(limit: Int): List<Schedule> {
        val instant = Clock.System.now()
        val timeZone = TimeZone.currentSystemDefault()

        return (1..limit).map {
            createFakeNetworkSchedule(
                id = it.toLong(),
                scheduledAt = instant.plus(1, DateTimeUnit.DAY, timeZone),
            )
        }.map(NetworkSchedule::toSchedule)
    }

    override suspend fun getSchedule(id: String): Schedule {
        return createFakeNetworkSchedule(
            id = id.toLong(),
            scheduledAt = Clock.System.now(),
        ).toSchedule()
    }
}
