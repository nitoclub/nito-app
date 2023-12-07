package club.nito.core.network.participation.model

import club.nito.core.model.participant.Participant
import club.nito.core.model.participant.ParticipantStatus
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

/**
 * 参加状況に変換する
 */
internal fun NetworkParticipant?.toParticipantStatus(): ParticipantStatus = this?.status.toParticipantStatus()

internal fun createFakeNetworkParticipant(
    scheduleId: String = "bbe00d24-d840-460d-a127-f23f9e472cc6",
    userId: String = "bbe00d24-d840-460d-a127-f23f9e472cc6",
    status: NetworkParticipantStatus = NetworkParticipantStatus.ATTENDANCE,
) = NetworkParticipant(
    scheduleId = scheduleId,
    userId = userId,
    status = status,
)
