package club.nito.core.network.user

import club.nito.core.model.UserProfile

/**
 * ユーザープロフィールに関するリポジトリ
 */
sealed interface UserRemoteDataSource {
    /**
     * ユーザープロフィールを取得する
     */
    suspend fun getProfile(userId: String): UserProfile?

    /**
     * 複数のユーザープロフィールを取得する
     */
    suspend fun getProfiles(userIds: List<String>): List<UserProfile>
}
