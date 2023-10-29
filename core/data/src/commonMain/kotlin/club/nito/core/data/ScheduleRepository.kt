package club.nito.core.data

import club.nito.core.model.Schedule
import kotlinx.coroutines.flow.Flow

sealed interface ScheduleRepository {
    val scheduleListFlow: Flow<List<Schedule>>
    fun scheduleFlow(id: String): Flow<Schedule>
}
