package club.nito.core.network.schedule.model

import club.nito.core.model.Schedule
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class NetworkSchedule(
    val id: String,
    @SerialName("scheduled_at")
    val scheduledAt: String,
) {
    fun toSchedule() = Schedule(
        id = id,
        scheduledAt = scheduledAt,
    )
}

internal fun createFakeNetworkSchedule(
    id: String = "1",
    scheduledAt: String = "2021/01/01",
) = NetworkSchedule(
    id = id,
    scheduledAt = scheduledAt,
)
