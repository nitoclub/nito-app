package club.nito.feature.schedule

import club.nito.core.domain.model.ParticipantSchedule

sealed class ScheduleListIntent {
    data class ClickShowConfirmParticipateDialog(val schedule: ParticipantSchedule) : ScheduleListIntent()
    data class ClickParticipateSchedule(val schedule: ParticipantSchedule) : ScheduleListIntent()
    data object ClickDismissConfirmParticipateDialog : ScheduleListIntent()
}
