package club.nito.core.network.participation.model

import club.nito.core.model.UnknownException
import club.nito.core.model.participant.ParticipantStatus
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 参加状況
 */
@Serializable
internal enum class NetworkParticipantStatus {
    /**
     * 未定
     */
    @SerialName(value = "pending")
    PENDING,

    /**
     * 出席
     */
    @SerialName(value = "attendance")
    ATTENDANCE,

    /**
     * 欠席
     */
    @SerialName(value = "absence")
    ABSENCE;

    fun toParticipantStatus(): ParticipantStatus =
        when (this) {
            PENDING -> ParticipantStatus.PENDING
            ATTENDANCE -> ParticipantStatus.ATTENDANCE
            ABSENCE -> ParticipantStatus.ABSENCE
        }
}

internal fun ParticipantStatus.toNetworkModel(): NetworkParticipantStatus = when (this) {
    ParticipantStatus.PENDING -> NetworkParticipantStatus.PENDING
    ParticipantStatus.ATTENDANCE -> NetworkParticipantStatus.ATTENDANCE
    ParticipantStatus.ABSENCE -> NetworkParticipantStatus.ABSENCE

    // NOTE: NONE は操作上ありえないので、例外を投げる
    ParticipantStatus.NONE -> throw UnknownException(
        cause = IllegalArgumentException("ParticipantStatus.NONE is not supported"),
    )
}
