package club.nito.feature.schedule

import club.nito.core.common.NitoDateTimeFormatter
import club.nito.core.domain.model.ParticipantSchedule
import club.nito.core.model.FetchMultipleContentResult

data class ScheduleListScreenUiState(
    val dateTimeFormatter: NitoDateTimeFormatter,
    val scheduleList: FetchMultipleContentResult<ParticipantSchedule>,
    val confirmParticipateDialog: ConfirmParticipateDialogUiState,
)

sealed class ConfirmParticipateDialogUiState {
    data class Show(val schedule: ParticipantSchedule) : ConfirmParticipateDialogUiState()
    data object Hide : ConfirmParticipateDialogUiState()
}
