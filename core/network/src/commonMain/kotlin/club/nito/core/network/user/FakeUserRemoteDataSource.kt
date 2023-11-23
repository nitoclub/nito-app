package club.nito.core.network.user

import club.nito.core.model.UserProfile
import club.nito.core.network.user.model.NetworkUserProfile
import club.nito.core.network.user.model.createFakeNetworkUserProfile
import kotlinx.coroutines.delay

public data object FakeUserRemoteDataSource : UserRemoteDataSource {
    private const val DEFAULT_CHANGED_COUNT = 1L

    override suspend fun getProfile(userId: String): UserProfile? {
        delay(1000)

        return createFakeNetworkUserProfile(id = userId)
            .let(NetworkUserProfile::toUserProfile)
    }

    override suspend fun getProfiles(userIds: List<String>): List<UserProfile> {
        delay(1000)
        return (1..10).map {
            createFakeNetworkUserProfile(
                id = it.toString(),
            )
        }.map(NetworkUserProfile::toUserProfile)
    }
}
