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
public data class TopScreenUiState internal constructor(
    val dateTimeFormatter: NitoDateTimeFormatter,
    val recentSchedule: FetchSingleContentResult<ParticipantSchedule>,
    val confirmParticipateDialog: ConfirmParticipateDialogUiState,
)

/**
 * Confirm participate dialog ui state
 */
public sealed class ConfirmParticipateDialogUiState {
    /**
     * Show confirm participate dialog
     * @param schedule Schedule
     */
    public data class Show internal constructor(val schedule: ParticipantSchedule) : ConfirmParticipateDialogUiState()

    /**
     * Hide confirm participate dialog
     */
    public data object Hide : ConfirmParticipateDialogUiState()
}
