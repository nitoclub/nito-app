package club.nito.core.database.participant

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOneOrNull
import club.nito.core.common.NitoCoroutineDispatchers
import club.nito.core.database.Database
import club.nito.core.database.ParticipantUsersByScheduleId
import club.nito.core.database.participant.model.toModel
import club.nito.core.model.participant.Participant
import club.nito.core.model.participant.ParticipantStatus
import club.nito.core.model.participant.ParticipantUser
import club.nito.core.model.schedule.ScheduleId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class SqlDelightParticipantDao(
    private val database: Database,
    private val dispatchers: NitoCoroutineDispatchers,
) : ParticipantDao {
    override fun participantUsersStream(scheduleId: ScheduleId): Flow<List<ParticipantUser>> {
        return database.participantsQueries.participantUsersByScheduleId(scheduleId)
            .asFlow()
            .mapToList(dispatchers.io)
            .map {
                it.map(ParticipantUsersByScheduleId::toModel)
            }
    }

    override fun participantStatusStream(scheduleId: String, userId: String): Flow<ParticipantStatus> = database
        .participantsQueries
        .participantStatusByUserId(
            schedule_id = scheduleId,
            user_id = userId,
        )
        .asFlow()
        .mapToOneOrNull(dispatchers.io)
        .map {
            it ?: ParticipantStatus.NONE
        }

    override fun upsert(entities: List<Participant>) {
        database.transaction {
            for (entity in entities) {
                upsertParticipant(entity)
            }
        }
    }

    override fun upsert(entity: Participant) {
        database.transaction {
            upsertParticipant(entity)
        }
    }

    private fun upsertParticipant(entity: Participant) {
        database.participantsQueries.upsert(
            schedule_id = entity.scheduleId,
            user_id = entity.userId,
            status = entity.status,
        )
    }
}
