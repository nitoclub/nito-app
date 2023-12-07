package club.nito.core.network.participation

import club.nito.core.model.participant.Participant
import club.nito.core.model.participant.ParticipantDeclaration
import club.nito.core.model.participant.ParticipantStatus
import club.nito.core.model.schedule.ScheduleId
import club.nito.core.network.participation.model.NetworkParticipant
import club.nito.core.network.participation.model.createFakeNetworkParticipant
import club.nito.core.network.participation.model.toNetworkModel
import kotlinx.coroutines.delay

public data object FakeParticipantRemoteDataSource : ParticipantRemoteDataSource {
    private const val DEFAULT_CHANGED_COUNT = 1L

    override suspend fun getParticipants(scheduleId: String): List<Participant> {
        delay(1000)
        return (1..10).map {
            createFakeNetworkParticipant(
                userId = it.toString(),
            )
        }.map(NetworkParticipant::toParticipant)
    }

    override suspend fun getParticipants(scheduleIds: List<String>): List<Participant> {
        delay(1000)
        return (1..10).map {
            createFakeNetworkParticipant(
                userId = it.toString(),
            )
        }.map(NetworkParticipant::toParticipant)
    }

    override suspend fun existParticipantByUserId(scheduleId: ScheduleId, userId: String): Boolean = true

    override suspend fun fetchParticipantStatus(scheduleId: ScheduleId, userId: String): ParticipantStatus {
        return ParticipantStatus.ATTENDANCE
    }

    override suspend fun insertParticipate(declaration: ParticipantDeclaration): Participant {
        delay(1000)
        return createFakeNetworkParticipant(
            scheduleId = declaration.scheduleId,
            userId = declaration.userId,
            status = declaration.status.toNetworkModel(),
        ).toParticipant()
    }

    override suspend fun updateParticipate(declaration: ParticipantDeclaration): Participant {
        delay(1000)
        return createFakeNetworkParticipant(
            scheduleId = declaration.scheduleId,
            userId = declaration.userId,
            status = declaration.status.toNetworkModel(),
        ).toParticipant()
    }
}
