package club.nito.core.data

import club.nito.core.model.FetchSingleContentResult
import club.nito.core.model.UserProfile
import club.nito.core.network.user.UserRemoteDataSource

class DefaultUserRepository(
    private val remoteDataSource: UserRemoteDataSource,
) : UserRepository {
    override suspend fun getProfile(userId: String): FetchSingleContentResult<UserProfile> {
        val profile = remoteDataSource.getProfile(userId = userId)
        return profile?.let { FetchSingleContentResult.Success(it) }
            ?: FetchSingleContentResult.NoContent
    }

    override suspend fun getProfiles(userIds: List<String>): List<UserProfile> {
        return remoteDataSource.getProfiles(userIds = userIds)
    }
}
