package club.nito.core.network.schedule

import club.nito.core.model.Schedule

interface ScheduleApi {
    suspend fun getScheduleList(): List<Schedule>
    suspend fun getSchedule(id: String): Schedule
}
