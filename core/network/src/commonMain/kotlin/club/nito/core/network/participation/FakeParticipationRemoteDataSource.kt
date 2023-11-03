package club.nito.core.network.participation

import club.nito.core.model.participation.ParticipationDeclaration
import kotlinx.coroutines.delay

data object FakeParticipationRemoteDataSource : ParticipationRemoteDataSource {
    private const val DEFAULT_CHANGED_COUNT = 1L

    override suspend fun participate(declaration: ParticipationDeclaration): Long {
        delay(1000)
        return DEFAULT_CHANGED_COUNT
    }
}
