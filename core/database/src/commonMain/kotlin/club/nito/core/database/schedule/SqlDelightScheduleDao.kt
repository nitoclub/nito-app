package club.nito.core.database.schedule

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOneOrNull
import club.nito.core.common.NitoCoroutineDispatchers
import club.nito.core.database.Database
import club.nito.core.database.Schedules
import club.nito.core.database.schedule.model.toModel
import club.nito.core.model.schedule.Schedule
import club.nito.core.model.schedule.ScheduleId
import club.nito.core.model.schedule.ScheduleWithPlace
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class SqlDelightScheduleDao(
    private val database: Database,
    private val dispatchers: NitoCoroutineDispatchers,
) : ScheduleDao {
    override val schedulesStream: Flow<List<Schedule>>
        get() = database.schedulesQueries.schedules()
            .asFlow()
            .mapToList(dispatchers.io)
            .map {
                it.map(Schedules::toModel)
            }

    override fun scheduleStream(scheduleId: ScheduleId): Flow<Schedule?> {
        return database.schedulesQueries.scheduleById(scheduleId)
            .asFlow()
            .mapToOneOrNull(dispatchers.io)
            .map {
                it?.toModel()
            }
    }

    override fun scheduleWithPlaceStream(scheduleId: ScheduleId): Flow<ScheduleWithPlace?> {
        return database.schedulesQueries.scheduleWithPlace(scheduleId)
            .asFlow()
            .mapToOneOrNull(dispatchers.io)
            .map {
                it?.toModel()
            }
    }

    override fun schedulesStream(scheduleIds: List<ScheduleId>): Flow<List<Schedule>> {
        return database.schedulesQueries.schedulesByIds(scheduleIds)
            .asFlow()
            .mapToList(dispatchers.io)
            .map {
                it.map(Schedules::toModel)
            }
    }

    override fun upsert(entities: List<Schedule>) {
        database.transaction {
            for (entity in entities) {
                upsertSchedule(entity)
            }
        }
    }

    override fun upsert(entity: Schedule) {
        database.transaction {
            upsertSchedule(entity)
        }
    }

    private fun upsertSchedule(entity: Schedule) {
        database.schedulesQueries.upsert(
            id = entity.id,
            scheduled_at = entity.scheduledAt,
            met_at = entity.metAt,
            venue_id = entity.venueId,
            meet_id = entity.meetId,
            description = entity.description,
        )
    }
}
