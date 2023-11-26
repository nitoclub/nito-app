package club.nito.core.network.schedule.model

import club.nito.core.model.schedule.Schedule
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
internal data class NetworkSchedule(
    val id: String,
    val scheduledAt: Instant,
    val metAt: Instant,
    val venueId: String,
    val meetId: String,
    val description: String,
) {
    fun toSchedule() = Schedule(
        id = id,
        scheduledAt = scheduledAt,
        metAt = metAt,
        venueId = venueId,
        meetId = meetId,
        description = description,
    )
}

internal fun createFakeNetworkSchedule(
    id: String = "bbe00d24-d840-460d-a127-f23f9e472cc6",
    scheduledAt: Instant = Clock.System.now(),
    metAt: Instant = Clock.System.now(),
    venueId: String = "bbe00d24-d840-460d-a127-f23f9e472cc6",
    meetId: String = "bbe00d24-d840-460d-a127-f23f9e472cc6",
    description: String = "いつも通り 11:00 から 90 分で入る予定です。\n10:30 にレイクタウンの kaze で集合して向かいます。",
) = NetworkSchedule(
    id = id,
    scheduledAt = scheduledAt,
    metAt = metAt,
    venueId = venueId,
    meetId = meetId,
    description = description,
)
