package club.nito.core.network.participation

import club.nito.core.model.participation.ParticipantDeclaration
import kotlinx.coroutines.delay

data object FakeParticipantRemoteDataSource : ParticipantRemoteDataSource {
    private const val DEFAULT_CHANGED_COUNT = 1L

    override suspend fun participate(declaration: ParticipantDeclaration): Long {
        delay(1000)
        return DEFAULT_CHANGED_COUNT
    }
}
