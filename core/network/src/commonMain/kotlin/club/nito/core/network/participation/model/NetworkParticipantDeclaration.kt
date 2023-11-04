package club.nito.core.network.participation.model

import club.nito.core.model.participant.ParticipantDeclaration
import kotlinx.serialization.Serializable

@Serializable
internal data class NetworkParticipantDeclaration(
    val scheduleId: Long,
    val memberId: Long,
    val comment: String,
)

internal fun ParticipantDeclaration.toNetworkModel(): NetworkParticipantDeclaration =
    NetworkParticipantDeclaration(
        scheduleId = scheduleId.toLong(),
        memberId = memberId.toLong(),
        comment = comment,
    )
