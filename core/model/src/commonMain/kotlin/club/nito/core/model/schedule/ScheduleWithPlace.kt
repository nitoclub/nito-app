package club.nito.core.model.schedule

import club.nito.core.model.place.Place
import kotlinx.datetime.Instant

public data class ScheduleWithPlace(
    val id: ScheduleId,
    val scheduledAt: Instant,
    val metAt: Instant,
    val venue: Place,
    val meet: Place,
    val description: String,
)
