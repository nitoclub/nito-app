package club.nito.feature.top

import club.nito.core.domain.model.ParticipantSchedule

public sealed class TopScreenIntent {
    /**
     * Click schedule list
     */
    public data object ClickScheduleList : TopScreenIntent()

    /**
     * Click settings
     */
    public data object ClickSettings : TopScreenIntent()

    /**
     * Click show confirm participate dialog
     */
    public data class ClickShowConfirmParticipateDialog(val schedule: ParticipantSchedule) : TopScreenIntent()

    /**
     * Click participate schedule
     */
    public data class ClickParticipateSchedule(val schedule: ParticipantSchedule) : TopScreenIntent()

    /**
     * Click dismiss confirm participate dialog
     */
    public data object ClickDismissConfirmParticipateDialog : TopScreenIntent()
}
