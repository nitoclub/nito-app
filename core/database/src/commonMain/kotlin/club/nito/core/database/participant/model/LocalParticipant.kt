package club.nito.core.database.participant.model

import club.nito.core.model.UserProfile
import club.nito.core.model.participant.Participant
import club.nito.core.model.participant.ParticipantUser
import club.nito.core.database.ParticipantUsersByScheduleId as LocalParticipantUser
import club.nito.core.database.Participants as LocalParticipant

internal fun LocalParticipant.toModel(): Participant = Participant(
    scheduleId = schedule_id,
    userId = user_id,
    status = status,
)

internal fun LocalParticipantUser.toModel(): ParticipantUser = ParticipantUser(
    profile = UserProfile(
        id = id,
        username = username,
        displayName = display_name,
        avatarUrl = avatar_url,
        website = website,
    ),
    status = status,
)
