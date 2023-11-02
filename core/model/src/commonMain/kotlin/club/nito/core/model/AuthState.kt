package club.nito.core.model

sealed interface AuthStatus {
    data object NotAuthenticated : AuthStatus

    data class Authenticated(val session: UserSession) : AuthStatus
}
