package club.nito.core.domain.model

import club.nito.core.model.place.Place
import club.nito.core.model.schedule.ScheduleId
import kotlinx.datetime.Instant

public data class ParticipantSchedule(
    val id: ScheduleId,
    val scheduledAt: Instant,
    val metAt: Instant,
    val venue: Place,
    val meet: Place,
    val description: String,
    val users: List<ParticipantUser>,
)
