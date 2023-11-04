package club.nito.feature.settings

sealed class SettingsIntent {
    data object ClickShowModifyPasswordDialog : SettingsIntent()
    data class ChangeNewPasswordValue(val newValue: String) : SettingsIntent()
    data object ClickModifyPassword : SettingsIntent()
    data object ClickDismissModifyPasswordDialog : SettingsIntent()
    data object ClickSignOut : SettingsIntent()
}
