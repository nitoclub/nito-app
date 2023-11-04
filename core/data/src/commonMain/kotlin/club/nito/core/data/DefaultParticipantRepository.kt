package club.nito.core.data

import club.nito.core.model.participation.ParticipantDeclaration
import club.nito.core.network.participation.ParticipantRemoteDataSource

class DefaultParticipantRepository(
    private val remoteDataSource: ParticipantRemoteDataSource,
) : ParticipantRepository {
    override suspend fun participate(declaration: ParticipantDeclaration): Long =
        remoteDataSource.participate(declaration = declaration)
}
