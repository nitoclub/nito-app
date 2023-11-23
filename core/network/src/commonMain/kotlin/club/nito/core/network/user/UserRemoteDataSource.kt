package club.nito.core.network.user

import club.nito.core.model.UserProfile

/**
 * ユーザープロフィールに関するリポジトリ
 */
public sealed interface UserRemoteDataSource {
    /**
     * ユーザープロフィールを取得する
     */
    public suspend fun getProfile(userId: String): UserProfile?

    /**
     * 複数のユーザープロフィールを取得する
     */
    public suspend fun getProfiles(userIds: List<String>): List<UserProfile>
}
