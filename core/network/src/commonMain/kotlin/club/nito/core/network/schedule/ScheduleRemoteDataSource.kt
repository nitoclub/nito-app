package club.nito.core.network.schedule

import club.nito.core.model.Schedule

sealed interface ScheduleRemoteDataSource {
    suspend fun getScheduleList(limit: Int): List<Schedule>
    suspend fun getSchedule(id: String): Schedule
}
