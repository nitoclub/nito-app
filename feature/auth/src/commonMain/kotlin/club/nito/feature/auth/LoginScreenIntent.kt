package club.nito.feature.auth

sealed class LoginScreenIntent {
    data class ChangeInputEmail(val email: String) : LoginScreenIntent()
    data class ChangeInputPassword(val password: String) : LoginScreenIntent()
    data object ClickSignIn : LoginScreenIntent()
    data object ClickRegister : LoginScreenIntent()
}
