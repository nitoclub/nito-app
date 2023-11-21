package club.nito.feature.top

import club.nito.core.common.NitoDateTimeFormatter
import club.nito.core.domain.model.ParticipantSchedule
import club.nito.core.model.FetchSingleContentResult

/**
 * Top screen ui state
 * @param dateTimeFormatter Date time formatter
 * @param recentSchedule Recent schedule
 * @param confirmParticipateDialog Confirm participate dialog
 */
data class TopScreenUiState internal constructor(
    val dateTimeFormatter: NitoDateTimeFormatter,
    val recentSchedule: FetchSingleContentResult<ParticipantSchedule>,
    val confirmParticipateDialog: ConfirmParticipateDialogUiState,
)

/**
 * Confirm participate dialog ui state
 */
sealed class ConfirmParticipateDialogUiState {
    /**
     * Show confirm participate dialog
     * @param schedule Schedule
     */
    data class Show internal constructor(val schedule: ParticipantSchedule) : ConfirmParticipateDialogUiState()

    /**
     * Hide confirm participate dialog
     */
    data object Hide : ConfirmParticipateDialogUiState()
}
