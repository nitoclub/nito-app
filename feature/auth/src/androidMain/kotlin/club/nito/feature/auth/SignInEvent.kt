package club.nito.feature.auth

sealed class SignInEvent {
    data object SignedIn : SignInEvent()
    data object NavigateToRegister : SignInEvent()
}
