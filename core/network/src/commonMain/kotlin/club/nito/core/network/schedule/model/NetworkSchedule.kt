package club.nito.core.network.schedule.model

import club.nito.core.model.Schedule
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
internal data class NetworkSchedule(
    val id: String,
    val scheduledAt: Instant,
) {
    fun toSchedule() = Schedule(
        id = id,
        scheduledAt = scheduledAt,
    )
}

internal fun createFakeNetworkSchedule(
    id: String = "bbe00d24-d840-460d-a127-f23f9e472cc6",
    scheduledAt: Instant = Clock.System.now(),
) = NetworkSchedule(
    id = id,
    scheduledAt = scheduledAt,
)
