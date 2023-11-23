package club.nito.core.domain.model

import club.nito.core.model.UserProfile
import kotlinx.datetime.Instant

public data class ParticipantSchedule(
    val id: String,
    val scheduledAt: Instant,
    val metAt: Instant,
    val venueId: String,
    val meetId: String,
    val description: String,
    val participants: List<UserProfile>,
)
