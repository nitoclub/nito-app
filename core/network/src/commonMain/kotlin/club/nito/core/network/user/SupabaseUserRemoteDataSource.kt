package club.nito.core.network.user

import club.nito.core.model.UserProfile
import club.nito.core.network.user.model.NetworkUserProfile
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest

class SupabaseUserRemoteDataSource(
    private val client: SupabaseClient,
) : UserRemoteDataSource {
    private val postgrest = client.postgrest["profiles"]

    override suspend fun getProfile(userId: String): UserProfile? {
        return postgrest
            .select(
                filter = {
                    eq("id", userId)
                },
            )
            .decodeSingleOrNull<NetworkUserProfile>()
            ?.let(NetworkUserProfile::toUserProfile)
    }

    override suspend fun getProfiles(userIds: List<String>): List<UserProfile> {
        return postgrest
            .select(
                filter = {
                    isIn("id", userIds)
                },
            )
            .decodeList<NetworkUserProfile>()
            .map(NetworkUserProfile::toUserProfile)
    }
}
