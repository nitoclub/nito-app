package club.nito.feature.settings

data class SettingsScreenUiState(
    val isSignOuting: Boolean,
) {
    val isSignOutButtonEnabled: Boolean
        get() = !isSignOuting
}
