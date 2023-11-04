package club.nito.feature.top

import club.nito.core.domain.model.ParticipantSchedule

sealed class TopIntent {
    data object ClickScheduleList : TopIntent()
    data object ClickSettings : TopIntent()
    data class ClickShowConfirmParticipateDialog(val schedule: ParticipantSchedule) : TopIntent()
    data class ClickParticipateSchedule(val schedule: ParticipantSchedule) : TopIntent()
    data object ClickDismissConfirmParticipateDialog : TopIntent()
}
