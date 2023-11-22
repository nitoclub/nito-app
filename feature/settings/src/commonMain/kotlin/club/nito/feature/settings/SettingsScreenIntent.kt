package club.nito.feature.settings

sealed class SettingsScreenIntent {
    data object ClickShowModifyPasswordDialog : SettingsScreenIntent()
    data class ChangeNewPasswordValue(val newValue: String) : SettingsScreenIntent()
    data object ClickModifyPassword : SettingsScreenIntent()
    data object ClickDismissModifyPasswordDialog : SettingsScreenIntent()
    data object ClickSignOut : SettingsScreenIntent()
}
