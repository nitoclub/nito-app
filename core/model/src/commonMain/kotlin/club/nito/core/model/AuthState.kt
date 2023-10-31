package club.nito.core.model

import kotlin.jvm.JvmInline

sealed interface AuthStatus {
    data object NotAuthenticated : AuthStatus

    @JvmInline
    value class Authenticated(val session: UserSession) : AuthStatus
}
