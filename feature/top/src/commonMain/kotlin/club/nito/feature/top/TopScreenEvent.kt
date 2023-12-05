package club.nito.feature.top

import club.nito.core.model.schedule.ScheduleId

public sealed class TopScreenEvent {
    /**
     * Navigate to schedule detail screen
     */
    public data class OnRecentScheduleClicked(val scheduleId: ScheduleId) : TopScreenEvent()

    /**
     * Navigate to schedule list screen
     */
    public data object NavigateToScheduleList : TopScreenEvent()

    /**
     * Navigate to settings screen
     */
    public data object NavigateToSettings : TopScreenEvent()
}
