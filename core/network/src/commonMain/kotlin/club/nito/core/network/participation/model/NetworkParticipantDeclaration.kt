package club.nito.core.network.participation.model

import club.nito.core.model.participant.ParticipantDeclaration
import kotlinx.serialization.Serializable

@Serializable
internal data class NetworkParticipantDeclaration(
    val scheduleId: String,
    val userId: String,
    val comment: String,
)

internal fun ParticipantDeclaration.toNetworkModel(): NetworkParticipantDeclaration =
    NetworkParticipantDeclaration(
        scheduleId = scheduleId,
        userId = memberId,
        comment = comment,
    )
