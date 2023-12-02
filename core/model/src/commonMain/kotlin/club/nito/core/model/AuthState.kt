package club.nito.core.model

public sealed interface AuthStatus {
    public data object Loading : AuthStatus

    public data object NotAuthenticated : AuthStatus

    public data class Authenticated(val session: UserSession) : AuthStatus

    public data object NetworkError : AuthStatus
}
