package club.nito.core.data

import club.nito.core.model.participant.Participant
import club.nito.core.model.participant.ParticipantDeclaration
import club.nito.core.model.schedule.ScheduleId
import club.nito.core.network.participation.ParticipantRemoteDataSource

public class DefaultParticipantRepository(
    private val remoteDataSource: ParticipantRemoteDataSource,
) : ParticipantRepository {
    override suspend fun getParticipants(scheduleId: String): List<Participant> =
        remoteDataSource.getParticipants(scheduleId = scheduleId)

    override suspend fun getParticipants(scheduleIds: List<String>): List<Participant> =
        remoteDataSource.getParticipants(scheduleIds = scheduleIds)

    override suspend fun existParticipantByUserId(scheduleId: ScheduleId, userId: String): Boolean =
        remoteDataSource.existParticipantByUserId(scheduleId = scheduleId, userId = userId)

    override suspend fun insertParticipate(declaration: ParticipantDeclaration): Long =
        remoteDataSource.insertParticipate(declaration = declaration)

    override suspend fun updateParticipate(declaration: ParticipantDeclaration): Long =
        remoteDataSource.updateParticipate(declaration = declaration)
}
