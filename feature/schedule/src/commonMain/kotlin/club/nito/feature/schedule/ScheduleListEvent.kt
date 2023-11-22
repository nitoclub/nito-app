package club.nito.feature.schedule

sealed class ScheduleListEvent {
    data class NavigateToScheduleDetail(val scheduleId: String) : ScheduleListEvent()
}
