package club.nito.feature.schedule.list

public sealed class ScheduleListEvent {
    public data class NavigateToScheduleDetail(val scheduleId: String) : ScheduleListEvent()
}
