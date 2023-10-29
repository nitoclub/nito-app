package club.nito.core.network.schedule

import club.nito.core.model.Schedule
import club.nito.core.network.schedule.model.NetworkSchedule
import club.nito.core.network.schedule.model.createFakeNetworkSchedule

class FakeScheduleApi : ScheduleApi {
    override suspend fun getScheduleList(): List<Schedule> {
        return (1..10).map {
            createFakeNetworkSchedule(
                id = it.toString(),
                title = "Title $it",
                description = "Description $it",
                date = "2021/01/0$it",
                location = "Location $it",
                url = "https://example.com/$it",
                isOnline = true,
                isFavorite = false,
            )
        }.map(NetworkSchedule::toSchedule)
    }

    override suspend fun getSchedule(id: String): Schedule {
        return createFakeNetworkSchedule(
            id = id,
            title = "Title $id",
            description = "Description $id",
            date = "2021/01/0$id",
            location = "Location $id",
            url = "https://example.com/$id",
            isOnline = true,
            isFavorite = false,
        ).toSchedule()
    }
}
