package club.nito.feature.settings

sealed class SettingsIntent {
    data object ClickSignOut : SettingsIntent()
}
