package club.nito.feature.auth

sealed class SignInIntent {
    data class ChangeInputEmail(val email: String) : SignInIntent()
    data class ChangeInputPassword(val password: String) : SignInIntent()
    data object ClickSignIn : SignInIntent()
    data object ClickRegister : SignInIntent()
}
