package club.nito.feature.top

public sealed class TopScreenEvent {
    /**
     * Navigate to schedule list screen
     */
    public data object NavigateToScheduleList : TopScreenEvent()

    /**
     * Navigate to settings screen
     */
    public data object NavigateToSettings : TopScreenEvent()
}
