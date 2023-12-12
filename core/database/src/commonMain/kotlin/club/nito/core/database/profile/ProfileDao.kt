package club.nito.core.database.profile

import club.nito.core.model.UserProfile
import kotlinx.coroutines.flow.Flow

public interface ProfileDao {
    /**
     * ユーザー情報のストリームを取得する
     *
     * @param userId 取得するユーザーの ID
     */
    public fun profileStream(userId: String): Flow<UserProfile?>

    /**
     * ユーザー情報のストリームを取得する
     *
     * @param userIds 取得するユーザーの ID 配列
     */
    public fun profilesStream(userIds: List<String>): Flow<List<UserProfile>>

    /**
     * ユーザー一覧を登録 / 更新する
     */
    public fun upsert(entities: List<UserProfile>)

    /**
     * ユーザーを登録 / 更新する
     */
    public fun upsert(entity: UserProfile)
}
