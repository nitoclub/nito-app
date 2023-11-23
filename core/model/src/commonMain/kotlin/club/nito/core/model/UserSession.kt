package club.nito.core.model

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlin.time.Duration.Companion.seconds

public data class UserSession(
    val accessToken: String,
    val refreshToken: String,
    val providerRefreshToken: String? = null,
    val providerToken: String? = null,
    val expiresIn: Long,
    val tokenType: String,
    val user: UserInfo?,
    val type: String = "",
    val expiresAt: Instant = Clock.System.now() + (expiresIn.seconds),
)

public data class UserInfo(
    val aud: String,
    val confirmationSentAt: Instant? = null,
    val confirmedAt: Instant? = null,
    val createdAt: Instant? = null,
    val email: String? = null,
    val emailConfirmedAt: Instant? = null,
    val factors: List<UserMfaFactor> = listOf(),
    val id: String,
    val lastSignInAt: Instant? = null,
    val phone: String? = null,
    val role: String? = null,
    val updatedAt: Instant? = null,
    val emailChangeSentAt: Instant? = null,
    val newEmail: String? = null,
    val invitedAt: Instant? = null,
    val recoverySentAt: Instant? = null,
    val phoneConfirmedAt: Instant? = null,
    val actionLink: String? = null,
)

public data class UserMfaFactor(
    val id: String,
    val createdAt: Instant,
    val updatedAt: Instant,
    val isVerified: Boolean,
    val friendlyName: String? = null,
    val factorType: String,
)
