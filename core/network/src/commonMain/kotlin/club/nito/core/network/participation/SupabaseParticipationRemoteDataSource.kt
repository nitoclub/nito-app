package club.nito.core.network.participation

import club.nito.core.model.participation.ParticipationDeclaration
import club.nito.core.network.participation.model.toNetworkModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Returning

class SupabaseParticipationRemoteDataSource(
    private val client: SupabaseClient,
) : ParticipationRemoteDataSource {
    private val postgrest = client.postgrest["participation"]

    override suspend fun participate(declaration: ParticipationDeclaration): Long {
        val result = postgrest.insert(
            value = declaration.toNetworkModel(),
            upsert = true,
            returning = Returning.MINIMAL,
            filter = {},
        )
        return result.count() ?: 0
    }
}
