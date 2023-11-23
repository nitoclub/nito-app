package club.nito.core.data

import club.nito.core.model.AuthStatus
import club.nito.core.model.FetchSingleResult
import club.nito.core.model.UserInfo
import club.nito.core.network.auth.AuthRemoteDataSource
import kotlinx.coroutines.flow.Flow

public class DefaultAuthRepository(
    private val remoteDataSource: AuthRemoteDataSource,
) : AuthRepository {
    override val authStatus: Flow<FetchSingleResult<AuthStatus>> = remoteDataSource.authStatus

    override suspend fun signIn(email: String, password: String): Unit = remoteDataSource.signIn(
        email = email,
        password = password,
    )

    override suspend fun signOut(): Unit = remoteDataSource.signOut()

    override suspend fun modifyAuthUser(email: String?, password: String?): UserInfo = remoteDataSource.modifyAuthUser(
        email = email,
        password = password,
    )
}
