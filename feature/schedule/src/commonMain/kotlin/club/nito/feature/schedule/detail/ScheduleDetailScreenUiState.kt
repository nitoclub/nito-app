package club.nito.feature.schedule.detail

import club.nito.core.common.NitoDateFormatter
import club.nito.core.domain.model.ParticipantSchedule
import club.nito.core.model.FetchSingleContentResult
import club.nito.core.model.participant.ParticipantStatus

public data class ScheduleDetailScreenUiState(
    val dateFormatter: NitoDateFormatter,
    val schedule: FetchSingleContentResult<ParticipantSchedule>,
    val myParticipantStatus: FetchSingleContentResult<ParticipantStatus>,
    val confirmParticipateDialog: ConfirmParticipateDialogUiState,
)

public sealed class ConfirmParticipateDialogUiState {
    public data class Show(val schedule: ParticipantSchedule) : ConfirmParticipateDialogUiState()
    public data object Hide : ConfirmParticipateDialogUiState()
}
