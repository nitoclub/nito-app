package club.nito.core.data

import club.nito.core.database.schedule.ScheduleDao
import club.nito.core.model.Order
import club.nito.core.model.schedule.Schedule
import club.nito.core.model.schedule.ScheduleId
import club.nito.core.model.schedule.ScheduleWithPlace
import club.nito.core.network.schedule.ScheduleRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Instant

public class OfflineFirstScheduleRepository(
    private val remoteDataSource: ScheduleRemoteDataSource,
    private val scheduleDao: ScheduleDao,
) : ScheduleRepository {
    override val scheduleListFlow: Flow<List<Schedule>>
        get() = scheduleDao.schedulesStream

    override suspend fun getScheduleList(
        limit: Int,
        order: Order,
        after: Instant?,
    ): List<Schedule> = remoteDataSource.getScheduleList(
        limit = limit,
        order = order,
        after = after,
    ).also {
        scheduleDao.upsert(it)
    }

    override fun scheduleStream(id: ScheduleId): Flow<Schedule?> = scheduleDao.scheduleStream(scheduleId = id)

    override fun scheduleWithPlaceStream(id: ScheduleId): Flow<ScheduleWithPlace?> {
        return scheduleDao.scheduleWithPlaceStream(scheduleId = id)
    }

    override suspend fun fetchSchedule(id: ScheduleId): Schedule = remoteDataSource
        .fetchSchedule(id = id)
        .also {
            scheduleDao.upsert(it)
        }
}
