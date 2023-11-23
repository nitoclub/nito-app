package club.nito.feature.settings

public sealed class SettingsScreenEvent {
    public data object SignedOut : SettingsScreenEvent()
}
