package club.nito.core.data

import club.nito.core.model.participation.ParticipationDeclaration
import club.nito.core.network.participation.ParticipationRemoteDataSource

class DefaultParticipationRepository(
    private val remoteDataSource: ParticipationRemoteDataSource,
) : ParticipationRepository {
    override suspend fun participate(declaration: ParticipationDeclaration): Long =
        remoteDataSource.participate(declaration = declaration)
}
