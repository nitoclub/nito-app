package club.nito.feature.auth.login

public data class LoginScreenUiState(
    val email: String,
    val password: String,
    val isSignInning: Boolean,
) {
    val isSignInButtonEnabled: Boolean
        get() = email.isNotBlank() && password.isNotBlank() && !isSignInning
}
