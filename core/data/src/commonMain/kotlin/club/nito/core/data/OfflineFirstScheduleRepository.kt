package club.nito.core.data

import club.nito.core.model.Schedule
import club.nito.core.network.schedule.ScheduleRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class OfflineFirstScheduleRepository(
    private val remoteDataSource: ScheduleRemoteDataSource,
) : ScheduleRepository {
    override val scheduleListFlow: Flow<List<Schedule>> = flow {
        // TODO: LocalDataSource
        emit(remoteDataSource.getScheduleList(limit = 10))
    }

    override suspend fun getScheduleList(limit: Int): List<Schedule> =
        remoteDataSource.getScheduleList(limit = limit)

    override fun scheduleFlow(id: String): Flow<Schedule> = flow {
        // TODO: LocalDataSource
        emit(remoteDataSource.getSchedule(id = id))
    }
}
