package club.nito.core.data

import club.nito.core.database.profile.ProfileDao
import club.nito.core.model.FetchSingleContentResult
import club.nito.core.model.UserProfile
import club.nito.core.network.user.UserRemoteDataSource
import kotlinx.coroutines.flow.Flow

public class DefaultUserRepository(
    private val remoteDataSource: UserRemoteDataSource,
    private val profileDao: ProfileDao,
) : UserRepository {
    override fun profilesStream(userIds: List<String>): Flow<List<UserProfile>> =
        profileDao.profilesStream(userIds = userIds)

    override suspend fun getProfile(userId: String): FetchSingleContentResult<UserProfile> {
        val profile = remoteDataSource.getProfile(userId = userId)
        return profile?.let { FetchSingleContentResult.Success(it) }
            ?: FetchSingleContentResult.NoContent
    }

    override suspend fun getProfiles(userIds: List<String>): List<UserProfile> = remoteDataSource
        .getProfiles(userIds = userIds)
        .also(profileDao::upsert)
}
