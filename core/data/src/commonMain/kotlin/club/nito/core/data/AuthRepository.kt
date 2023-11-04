package club.nito.core.data

import club.nito.core.model.AuthStatus
import club.nito.core.model.FetchSingleResult
import club.nito.core.model.UserInfo
import kotlinx.coroutines.flow.Flow

/**
 * 認証に関するリポジトリ
 */
sealed interface AuthRepository {
    /**
     * 認証情報の状態
     */
    val authStatus: Flow<FetchSingleResult<AuthStatus>>

    /**
     * サインインする
     */
    suspend fun signIn(email: String, password: String)

    /**
     * サインアウトする
     */
    suspend fun signOut()

    /**
     * 認証ユーザー情報を更新する
     */
    suspend fun modifyAuthUser(email: String?, password: String?): UserInfo
}
