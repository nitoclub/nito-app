package club.nito.feature.settings

public sealed class SettingsScreenIntent {
    public data object ClickShowModifyPasswordDialog : SettingsScreenIntent()
    public data class ChangeNewPasswordValue(val newValue: String) : SettingsScreenIntent()
    public data object ClickModifyPassword : SettingsScreenIntent()
    public data object ClickDismissModifyPasswordDialog : SettingsScreenIntent()
    public data object ClickSignOut : SettingsScreenIntent()
}
