package club.nito.feature.schedule

import club.nito.core.common.NitoDateFormatter
import club.nito.core.domain.model.ParticipantSchedule
import club.nito.core.model.FetchMultipleContentResult

public data class ScheduleListScreenUiState(
    val dateTimeFormatter: NitoDateFormatter,
    val scheduleList: FetchMultipleContentResult<ParticipantSchedule>,
    val confirmParticipateDialog: ConfirmParticipateDialogUiState,
)

public sealed class ConfirmParticipateDialogUiState {
    public data class Show(val schedule: ParticipantSchedule) : ConfirmParticipateDialogUiState()
    public data object Hide : ConfirmParticipateDialogUiState()
}
