package club.nito.core.network.participation.model

import club.nito.core.model.participation.ParticipationDeclaration
import kotlinx.serialization.Serializable

@Serializable
internal data class NetworkParticipationDeclaration(
    val scheduleId: Long,
    val memberId: Long,
    val comment: String,
)

internal fun ParticipationDeclaration.toNetworkModel(): NetworkParticipationDeclaration =
    NetworkParticipationDeclaration(
        scheduleId = scheduleId.toLong(),
        memberId = memberId.toLong(),
        comment = comment,
    )
