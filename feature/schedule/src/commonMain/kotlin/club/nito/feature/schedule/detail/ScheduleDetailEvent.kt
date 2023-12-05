package club.nito.feature.schedule.detail

public sealed class ScheduleDetailEvent {
    public data class NavigateToScheduleDetail(val scheduleId: String) : ScheduleDetailEvent()
}
