package club.nito.core.model

import kotlin.jvm.JvmInline

sealed interface AuthStatus {
    data object Loading : AuthStatus

    data object NetworkError : AuthStatus

    data object NotAuthenticated : AuthStatus

    @JvmInline
    value class Authenticated(val session: UserSession) : AuthStatus
}
