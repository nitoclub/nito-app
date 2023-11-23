package club.nito.feature.settings

public data class SettingsScreenUiState(
    val isSignOuting: Boolean,
    val modifyPassword: ModifyPasswordUiState,
) {
    val isSignOutButtonEnabled: Boolean
        get() = !isSignOuting
}

public sealed class ModifyPasswordUiState {
    public data object Idle : ModifyPasswordUiState()
    public sealed class Show : ModifyPasswordUiState() {
        public abstract val newPassword: String

        public data class Input(override val newPassword: String) : Show()
        public data class Modifying(override val newPassword: String) : Show()
    }
}
