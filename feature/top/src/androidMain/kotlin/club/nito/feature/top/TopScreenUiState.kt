package club.nito.feature.top

import club.nito.core.common.NitoDateTimeFormatter
import club.nito.core.domain.model.ParticipantSchedule
import club.nito.core.model.FetchSingleContentResult

data class TopScreenUiState(
    val dateTimeFormatter: NitoDateTimeFormatter,
    val recentSchedule: FetchSingleContentResult<ParticipantSchedule>,
    val confirmParticipateDialog: ConfirmParticipateDialogUiState,
)

sealed class ConfirmParticipateDialogUiState {
    data class Show(val schedule: ParticipantSchedule) : ConfirmParticipateDialogUiState()
    data object Hide : ConfirmParticipateDialogUiState()
}
