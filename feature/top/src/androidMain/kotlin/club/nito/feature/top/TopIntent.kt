package club.nito.feature.top

import club.nito.core.model.Schedule

sealed class TopIntent {
    data object ClickScheduleList : TopIntent()
    data object ClickSettings : TopIntent()
    data class ClickShowConfirmParticipateDialog(val schedule: Schedule) : TopIntent()
    data object ClickDismissConfirmParticipateDialog : TopIntent()
}
