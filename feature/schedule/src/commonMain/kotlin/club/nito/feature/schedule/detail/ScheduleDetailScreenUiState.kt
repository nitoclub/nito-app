package club.nito.feature.schedule.detail

import club.nito.core.common.NitoDateFormatter
import club.nito.core.domain.model.ParticipantSchedule
import club.nito.core.model.FetchMultipleContentResult
import club.nito.core.model.FetchSingleContentResult
import club.nito.core.model.participant.ParticipantStatus
import club.nito.core.model.participant.ParticipantUser
import club.nito.core.model.schedule.ScheduleWithPlace

public data class ScheduleDetailScreenUiState(
    val dateFormatter: NitoDateFormatter,
    val participantSchedule: FetchSingleContentResult<ParticipantSchedule>,
    val scheduleWithPlace: FetchSingleContentResult<ScheduleWithPlace>,
    val users: FetchMultipleContentResult<ParticipantUser>,
    val myParticipantStatus: ParticipantStatus,
)
