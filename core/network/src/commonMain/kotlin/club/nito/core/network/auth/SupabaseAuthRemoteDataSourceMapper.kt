package club.nito.core.network.auth

import club.nito.core.model.UserInfo
import club.nito.core.model.UserMfaFactor

internal data object SupabaseAuthRemoteDataSourceMapper {
    fun transformToUserInfo(info: io.github.jan.supabase.gotrue.user.UserInfo) = UserInfo(
        aud = info.aud,
        confirmationSentAt = info.confirmationSentAt,
        confirmedAt = info.confirmedAt,
        createdAt = info.createdAt,
        email = info.email,
        emailConfirmedAt = info.emailConfirmedAt,
        factors = info.factors.map(::transformToUserMfaFactor),
        id = info.id,
        lastSignInAt = info.lastSignInAt,
        phone = info.phone,
        role = info.role,
        updatedAt = info.updatedAt,
        emailChangeSentAt = info.emailChangeSentAt,
        newEmail = info.newEmail,
        invitedAt = info.invitedAt,
        recoverySentAt = info.recoverySentAt,
        phoneConfirmedAt = info.phoneConfirmedAt,
        actionLink = info.actionLink,
    )

    private fun transformToUserMfaFactor(factor: io.github.jan.supabase.gotrue.user.UserMfaFactor) = UserMfaFactor(
        id = factor.id,
        createdAt = factor.createdAt,
        updatedAt = factor.updatedAt,
        isVerified = factor.isVerified,
        friendlyName = factor.friendlyName,
        factorType = factor.factorType,
    )
}
