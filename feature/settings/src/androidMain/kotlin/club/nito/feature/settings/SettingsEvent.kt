package club.nito.feature.settings

sealed class SettingsEvent {
    data object SignedOut : SettingsEvent()
}
