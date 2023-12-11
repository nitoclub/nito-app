package club.nito.core.data

import club.nito.core.database.participant.ParticipantDao
import club.nito.core.model.participant.Participant
import club.nito.core.model.participant.ParticipantDeclaration
import club.nito.core.model.participant.ParticipantStatus
import club.nito.core.model.participant.ParticipantUser
import club.nito.core.model.schedule.ScheduleId
import club.nito.core.network.participation.ParticipantRemoteDataSource
import kotlinx.coroutines.flow.Flow

public class DefaultParticipantRepository(
    private val remoteDataSource: ParticipantRemoteDataSource,
    private val dao: ParticipantDao,
) : ParticipantRepository {
    override fun participantUsersStream(scheduleId: String): Flow<List<ParticipantUser>> =
        dao.participantUsersStream(scheduleId = scheduleId)

    override suspend fun getParticipants(scheduleId: String): List<Participant> =
        remoteDataSource.getParticipants(scheduleId = scheduleId).also {
            dao.upsert(entities = it)
        }

    override suspend fun getParticipants(scheduleIds: List<String>): List<Participant> =
        remoteDataSource.getParticipants(scheduleIds = scheduleIds).also {
            dao.upsert(entities = it)
        }

    override suspend fun existParticipantByUserId(scheduleId: ScheduleId, userId: String): Boolean =
        remoteDataSource.existParticipantByUserId(scheduleId = scheduleId, userId = userId)

    override suspend fun fetchParticipantStatus(scheduleId: ScheduleId, userId: String): ParticipantStatus =
        remoteDataSource.fetchParticipantStatus(scheduleId = scheduleId, userId = userId)

    override fun participantStatusStream(scheduleId: ScheduleId, userId: String): Flow<ParticipantStatus> =
        dao.participantStatusStream(
            scheduleId = scheduleId,
            userId = userId,
        )

    override suspend fun upsertLocalParticipate(participant: Participant): Unit = dao.upsert(participant)

    override suspend fun upsertParticipate(participant: Participant): Participant = remoteDataSource
        .upsertParticipate(participant = participant)
        .also {
            dao.upsert(it)
        }

    override suspend fun updateParticipate(declaration: ParticipantDeclaration): Participant {
        dao.upsert(
            entity = Participant(
                scheduleId = declaration.scheduleId,
                userId = declaration.userId,
                status = declaration.status,
            ),
        )

        val participant = remoteDataSource.updateParticipate(declaration = declaration)
        dao.upsert(participant)
        return participant
    }
}
