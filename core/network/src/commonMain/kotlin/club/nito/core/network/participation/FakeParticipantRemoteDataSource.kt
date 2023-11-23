package club.nito.core.network.participation

import club.nito.core.model.participant.Participant
import club.nito.core.model.participant.ParticipantDeclaration
import club.nito.core.network.participation.model.NetworkParticipant
import club.nito.core.network.participation.model.createFakeNetworkParticipant
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

    override suspend fun participate(declaration: ParticipantDeclaration): Long {
        delay(1000)
        return DEFAULT_CHANGED_COUNT
    }
}
