package club.nito.core.data

import club.nito.core.model.AuthStatus
import club.nito.core.network.auth.AuthRemoteDataSource
import kotlinx.coroutines.flow.Flow

class DefaultAuthRepository(
    private val remoteDataSource: AuthRemoteDataSource,
) : AuthRepository {
    override val authStatus: Flow<AuthStatus> = remoteDataSource.authStatus

    override suspend fun signIn(email: String, password: String) = remoteDataSource.signIn(
        email = email,
        password = password,
    )

    override suspend fun signOut() = remoteDataSource.signOut()
}
