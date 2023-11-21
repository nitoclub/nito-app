package club.nito.feature.top

import club.nito.core.domain.model.ParticipantSchedule

sealed class TopScreenIntent {
    /**
     * Click schedule list
     */
    data object ClickScheduleList : TopScreenIntent()

    /**
     * Click settings
     */
    data object ClickSettings : TopScreenIntent()

    /**
     * Click show confirm participate dialog
     */
    data class ClickShowConfirmParticipateDialog(val schedule: ParticipantSchedule) : TopScreenIntent()

    /**
     * Click participate schedule
     */
    data class ClickParticipateSchedule(val schedule: ParticipantSchedule) : TopScreenIntent()

    /**
     * Click dismiss confirm participate dialog
     */
    data object ClickDismissConfirmParticipateDialog : TopScreenIntent()
}
