package club.nito.core.network.participation.model

import club.nito.core.model.participant.ParticipantDeclaration
import club.nito.core.model.schedule.ScheduleId
import kotlinx.serialization.Serializable

@Serializable
internal data class NetworkParticipantDeclaration(
    val scheduleId: ScheduleId,
    val userId: String,
    val status: NetworkParticipantStatus,
)

internal fun ParticipantDeclaration.toNetworkModel(): NetworkParticipantDeclaration =
    NetworkParticipantDeclaration(
        scheduleId = scheduleId,
        userId = userId,
        status = status.toNetworkModel(),
    )
