package club.nito.core.data

import club.nito.core.datastore.DataStore
import club.nito.core.model.ApiException
import club.nito.core.model.AuthStatus
import club.nito.core.model.UserInfo
import club.nito.core.network.auth.AuthRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

public class DefaultAuthRepository(
    private val remoteDataSource: AuthRemoteDataSource,
    private val dataStore: DataStore,
) : AuthRepository {
    override val authStatus: Flow<AuthStatus> = remoteDataSource.authStatus.map {
        if (it is AuthStatus.Authenticated && dataStore.isRefreshed().not()) {
            remoteDataSource.refreshCurrentSession()
            dataStore.setRefreshed(true)
        }

        it
    }

    override suspend fun login(email: String, password: String): Unit = remoteDataSource.login(
        email = email,
        password = password,
    )

    override suspend fun logout(): Unit = remoteDataSource.logout()

    override suspend fun modifyAuthUser(email: String?, password: String?): UserInfo = remoteDataSource.modifyAuthUser(
        email = email,
        password = password,
    )

    override suspend fun currentUser(): UserInfo = remoteDataSource.currentUserOrNull()
        ?: throw ApiException.SessionNotFoundException(cause = null)
}
