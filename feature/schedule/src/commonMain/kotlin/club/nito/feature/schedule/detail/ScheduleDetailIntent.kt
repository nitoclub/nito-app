package club.nito.feature.schedule.detail

import club.nito.core.domain.model.ParticipantSchedule

public sealed class ScheduleDetailIntent {
    public data class ClickShowConfirmParticipateDialog(val schedule: ParticipantSchedule) : ScheduleDetailIntent()
    public data class ClickParticipateSchedule(val schedule: ParticipantSchedule) : ScheduleDetailIntent()
    public data object ClickDismissConfirmParticipateDialog : ScheduleDetailIntent()
}
