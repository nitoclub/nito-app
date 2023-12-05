package club.nito.core.data

import club.nito.core.model.Order
import club.nito.core.model.schedule.Schedule
import club.nito.core.model.schedule.ScheduleId
import club.nito.core.network.schedule.ScheduleRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.Instant

public class OfflineFirstScheduleRepository(
    private val remoteDataSource: ScheduleRemoteDataSource,
) : ScheduleRepository {
    override val scheduleListFlow: Flow<List<Schedule>> = flow {
        // TODO: LocalDataSource
        emit(remoteDataSource.getScheduleList(limit = 10))
    }

    override suspend fun getScheduleList(
        limit: Int,
        order: Order,
        after: Instant?,
    ): List<Schedule> = remoteDataSource.getScheduleList(
        limit = limit,
        order = order,
        after = after,
    )

    override suspend fun fetchSchedule(id: ScheduleId): Schedule = remoteDataSource.fetchSchedule(id = id)
}
