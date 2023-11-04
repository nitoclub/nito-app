package club.nito.core.network.participation.model

import club.nito.core.model.participation.ParticipantDeclaration
import kotlinx.serialization.Serializable

@Serializable
internal data class NetworkParticipationDeclaration(
    val scheduleId: Long,
    val memberId: Long,
    val comment: String,
)

internal fun ParticipantDeclaration.toNetworkModel(): NetworkParticipationDeclaration =
    NetworkParticipationDeclaration(
        scheduleId = scheduleId.toLong(),
        memberId = memberId.toLong(),
        comment = comment,
    )
