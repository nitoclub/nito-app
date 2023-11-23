package club.nito.core.data

import club.nito.core.model.FetchSingleContentResult
import club.nito.core.model.UserProfile

/**
 * ユーザープロフィールに関するリポジトリ
 */
public sealed interface UserRepository {
    /**
     * ユーザープロフィールを取得する
     */
    public suspend fun getProfile(userId: String): FetchSingleContentResult<UserProfile>

    /**
     * 複数のユーザープロフィールを取得する
     */
    public suspend fun getProfiles(userIds: List<String>): List<UserProfile>
}
