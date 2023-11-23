package club.nito.feature.schedule

public sealed class ScheduleListEvent {
    public data class NavigateToScheduleDetail(val scheduleId: String) : ScheduleListEvent()
}
