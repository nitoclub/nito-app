package club.nito.core.network.schedule

import club.nito.core.model.Schedule
import club.nito.core.network.schedule.model.NetworkSchedule
import club.nito.core.network.schedule.model.createFakeNetworkSchedule

data object FakeScheduleRemoteDataSource : ScheduleRemoteDataSource {
    override suspend fun getScheduleList(limit: Long): List<Schedule> {
        return (1..limit).map {
            createFakeNetworkSchedule(
                id = it.toString(),
                scheduledAt = "2021/01/$it",
            )
        }.map(NetworkSchedule::toSchedule)
    }

    override suspend fun getSchedule(id: String): Schedule {
        return createFakeNetworkSchedule(
            id = id,
            scheduledAt = "2021/01/$id",
        ).toSchedule()
    }
}
