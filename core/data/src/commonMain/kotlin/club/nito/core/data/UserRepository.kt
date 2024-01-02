package club.nito.core.data

import club.nito.core.model.FetchSingleContentResult
import club.nito.core.model.UserProfile
import kotlinx.coroutines.flow.Flow

/**
 * ユーザープロフィールに関するリポジトリ
 */
public sealed interface UserRepository {
    /**
     * ユーザープロフィールのストリームを取得する
     */
    public fun profilesStream(userIds: List<String>): Flow<List<UserProfile>>

    /**
     * ユーザープロフィールを取得する
     */
    public suspend fun getProfile(userId: String): FetchSingleContentResult<UserProfile>

    /**
     * 複数のユーザープロフィールを取得する
     */
    public suspend fun getProfiles(userIds: List<String>): List<UserProfile>
}
