package club.nito.feature.auth.login

public sealed class LoginScreenIntent {
    public data class ChangeInputEmail(val email: String) : LoginScreenIntent()
    public data class ChangeInputPassword(val password: String) : LoginScreenIntent()
    public data class ChangePasswordVisible(val isPasswordVisible: Boolean) : LoginScreenIntent()
    public data object ClickSignIn : LoginScreenIntent()
    public data object ClickRegister : LoginScreenIntent()
}
