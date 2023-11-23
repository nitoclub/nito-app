package club.nito.core.data

import club.nito.core.model.participant.Participant
import club.nito.core.model.participant.ParticipantDeclaration
import club.nito.core.network.participation.ParticipantRemoteDataSource

public class DefaultParticipantRepository(
    private val remoteDataSource: ParticipantRemoteDataSource,
) : ParticipantRepository {
    override suspend fun getParticipants(scheduleId: String): List<Participant> =
        remoteDataSource.getParticipants(scheduleId = scheduleId)

    override suspend fun getParticipants(scheduleIds: List<String>): List<Participant> =
        remoteDataSource.getParticipants(scheduleIds = scheduleIds)

    override suspend fun participate(declaration: ParticipantDeclaration): Long =
        remoteDataSource.participate(declaration = declaration)
}
