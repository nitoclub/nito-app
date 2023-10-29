package club.nito.core.network.schedule

import club.nito.core.model.Schedule

sealed interface ScheduleRemoteDataSource {
    suspend fun getScheduleList(limit: Long): List<Schedule>
    suspend fun getSchedule(id: String): Schedule
}
