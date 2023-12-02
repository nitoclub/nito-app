package club.nito.core.data

import club.nito.core.model.AuthStatus
import club.nito.core.model.UserInfo
import club.nito.core.network.auth.AuthRemoteDataSource
import kotlinx.coroutines.flow.Flow

public class DefaultAuthRepository(
    private val remoteDataSource: AuthRemoteDataSource,
) : AuthRepository {
    override val authStatus: Flow<AuthStatus> = remoteDataSource.authStatus

    override suspend fun login(email: String, password: String): Unit = remoteDataSource.login(
        email = email,
        password = password,
    )

    override suspend fun logout(): Unit = remoteDataSource.logout()

    override suspend fun modifyAuthUser(email: String?, password: String?): UserInfo = remoteDataSource.modifyAuthUser(
        email = email,
        password = password,
    )
}
