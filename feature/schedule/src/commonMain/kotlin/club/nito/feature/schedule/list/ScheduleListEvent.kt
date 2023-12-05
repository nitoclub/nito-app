package club.nito.feature.schedule.list

public sealed class ScheduleListEvent {
    /**
     * Navigate to schedule detail screen
     */
    public data class OnScheduleItemClick(val scheduleId: String) : ScheduleListEvent()
}
