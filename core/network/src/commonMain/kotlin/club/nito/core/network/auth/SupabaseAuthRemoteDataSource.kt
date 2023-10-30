package club.nito.core.network.auth

import club.nito.core.model.AuthStatus
import club.nito.core.model.UserInfo
import club.nito.core.model.UserMfaFactor
import club.nito.core.model.UserSession
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.gotrue.SessionStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SupabaseAuthRemoteDataSource(
    private val goTrue: GoTrue,
) : AuthRemoteDataSource {
    override val authStatus: Flow<AuthStatus> = goTrue.sessionStatus.map {
        when (it) {
            is SessionStatus.Authenticated -> AuthStatus.Authenticated(
                session = UserSession(
                    accessToken = it.session.accessToken,
                    refreshToken = it.session.refreshToken,
                    providerRefreshToken = it.session.providerRefreshToken,
                    providerToken = it.session.providerToken,
                    expiresIn = it.session.expiresIn,
                    tokenType = it.session.tokenType,
                    user = it.session.user?.let {
                        UserInfo(
                            aud = it.aud,
                            confirmationSentAt = it.confirmationSentAt,
                            confirmedAt = it.confirmedAt,
                            createdAt = it.createdAt,
                            email = it.email,
                            emailConfirmedAt = it.emailConfirmedAt,
                            factors = it.factors.map { factor ->
                                UserMfaFactor(
                                    id = factor.id,
                                    createdAt = factor.createdAt,
                                    updatedAt = factor.updatedAt,
                                    isVerified = factor.isVerified,
                                    friendlyName = factor.friendlyName,
                                    factorType = factor.factorType,
                                )
                            },
                            id = it.id,
                            lastSignInAt = it.lastSignInAt,
                            phone = it.phone,
                            role = it.role,
                            updatedAt = it.updatedAt,
                            emailChangeSentAt = it.emailChangeSentAt,
                            newEmail = it.newEmail,
                            invitedAt = it.invitedAt,
                            recoverySentAt = it.recoverySentAt,
                            phoneConfirmedAt = it.phoneConfirmedAt,
                            actionLink = it.actionLink,
                        )
                    },
                    type = it.session.type,
                    expiresAt = it.session.expiresAt,
                ),
            )

            is SessionStatus.NotAuthenticated -> AuthStatus.NotAuthenticated
            is SessionStatus.LoadingFromStorage -> AuthStatus.Loading
            is SessionStatus.NetworkError -> AuthStatus.NetworkError
        }
    }
}
