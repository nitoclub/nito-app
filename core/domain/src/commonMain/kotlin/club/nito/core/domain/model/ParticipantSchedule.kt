package club.nito.core.domain.model

import club.nito.core.model.UserProfile
import club.nito.core.model.schedule.ScheduleId
import kotlinx.datetime.Instant

public data class ParticipantSchedule(
    val id: ScheduleId,
    val scheduledAt: Instant,
    val metAt: Instant,
    val venueId: String,
    val meetId: String,
    val description: String,
    val participants: List<UserProfile>,
)
