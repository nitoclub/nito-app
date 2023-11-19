package club.nito.core.data

import club.nito.core.model.Order
import club.nito.core.model.Schedule
import club.nito.core.network.schedule.ScheduleRemoteDataSource
import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.Instant

class OfflineFirstScheduleRepository(
    private val remoteDataSource: ScheduleRemoteDataSource,
) : ScheduleRepository {
    private val log = Logger.withTag("OfflineFirstScheduleRepository")

    override val scheduleListFlow: Flow<List<Schedule>> = flow {
        // TODO: LocalDataSource
        emit(remoteDataSource.getScheduleList(limit = 10))
    }

    override suspend fun getScheduleList(
        limit: Int,
        order: Order,
        after: Instant?,
    ): List<Schedule> =
        remoteDataSource.getScheduleList(
            limit = limit,
            order = order,
            after = after,
        ).also {
            log.d { "getScheduleList: $it" }
        }

    override fun scheduleFlow(id: String): Flow<Schedule> = flow {
        // TODO: LocalDataSource
        emit(remoteDataSource.getSchedule(id = id))
    }
}
