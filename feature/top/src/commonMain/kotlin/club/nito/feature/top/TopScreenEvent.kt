package club.nito.feature.top

sealed class TopScreenEvent {
    /**
     * Navigate to schedule list screen
     */
    data object NavigateToScheduleList : TopScreenEvent()

    /**
     * Navigate to settings screen
     */
    data object NavigateToSettings : TopScreenEvent()
}
