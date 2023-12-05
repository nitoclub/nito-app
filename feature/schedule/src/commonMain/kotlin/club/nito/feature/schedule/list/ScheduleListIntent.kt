package club.nito.feature.schedule.list

import club.nito.core.domain.model.ParticipantSchedule

public sealed class ScheduleListIntent {
    public data class ClickShowConfirmParticipateDialog(val schedule: ParticipantSchedule) : ScheduleListIntent()
    public data class ClickParticipateSchedule(val schedule: ParticipantSchedule) : ScheduleListIntent()
    public data object ClickDismissConfirmParticipateDialog : ScheduleListIntent()
}
