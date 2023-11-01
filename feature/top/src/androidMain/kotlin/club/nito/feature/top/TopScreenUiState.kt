package club.nito.feature.top

import club.nito.core.common.NitoDateTimeFormatter
import club.nito.core.model.FetchSingleContentResult
import club.nito.core.model.Schedule

data class TopScreenUiState(
    val dateTimeFormatter: NitoDateTimeFormatter,
    val recentSchedule: FetchSingleContentResult<Schedule>,
    val confirmParticipateDialog: ConfirmParticipateDialogUiState,
)

sealed class ConfirmParticipateDialogUiState {
    data class Show(val schedule: Schedule) : ConfirmParticipateDialogUiState()
    data object Hide : ConfirmParticipateDialogUiState()
}
