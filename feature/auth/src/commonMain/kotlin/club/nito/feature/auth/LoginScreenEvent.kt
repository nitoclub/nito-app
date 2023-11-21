package club.nito.feature.auth

sealed class LoginScreenEvent {
    data object LoggedIn : LoginScreenEvent()
    data object NavigateToRegister : LoginScreenEvent()
}
