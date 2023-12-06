package club.nito.core.network.participation.model

import club.nito.core.model.participant.Participant
import kotlinx.serialization.Serializable

@Serializable
internal data class NetworkParticipant(
    val scheduleId: String,
    val userId: String,
    val status: NetworkParticipantStatus,
) {
    fun toParticipant() = Participant(
        scheduleId = scheduleId,
        userId = userId,
        status = status.toParticipantStatus(),
    )
}

internal fun createFakeNetworkParticipant(
    scheduleId: String = "bbe00d24-d840-460d-a127-f23f9e472cc6",
    userId: String = "bbe00d24-d840-460d-a127-f23f9e472cc6",
    status: NetworkParticipantStatus = NetworkParticipantStatus.ATTENDANCE,
) = NetworkParticipant(
    scheduleId = scheduleId,
    userId = userId,
    status = status,
)
