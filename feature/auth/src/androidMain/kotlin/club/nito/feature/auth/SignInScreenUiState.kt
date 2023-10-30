package club.nito.feature.auth

data class SignInScreenUiState(
    val email: String,
    val password: String,
    val isSignInning: Boolean,
) {
    val isSignInButtonEnabled: Boolean
        get() = email.isNotBlank() && password.isNotBlank() && !isSignInning
}
