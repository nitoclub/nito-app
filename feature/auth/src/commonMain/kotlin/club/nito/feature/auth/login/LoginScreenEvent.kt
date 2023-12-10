package club.nito.feature.auth.login

public sealed class LoginScreenEvent {
    public data object LoggedIn : LoginScreenEvent()
    public data object NavigateToRegister : LoginScreenEvent()
}
