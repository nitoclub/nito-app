package club.nito.feature.settings

data class SettingsScreenUiState(
    val isSignOuting: Boolean,
    val modifyPassword: ModifyPasswordUiState,
) {
    val isSignOutButtonEnabled: Boolean
        get() = !isSignOuting
}

sealed class ModifyPasswordUiState {
    data object Idle : ModifyPasswordUiState()
    sealed class Show : ModifyPasswordUiState() {
        abstract val newPassword: String

        data class Input(override val newPassword: String) : Show()
        data class Modifying(override val newPassword: String) : Show()
    }
}
