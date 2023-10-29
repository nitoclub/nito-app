package club.nito.feature.top

sealed class TopEvent {
    data object NavigateToScheduleList : TopEvent()
    data object NavigateToSettings : TopEvent()
}
