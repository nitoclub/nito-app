package club.nito.feature.settings

sealed class SettingsScreenEvent {
    data object SignedOut : SettingsScreenEvent()
}
