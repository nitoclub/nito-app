package club.nito.core.network.participation

import club.nito.core.model.participant.Participant
import club.nito.core.model.participant.ParticipantDeclaration
import club.nito.core.network.participation.model.NetworkParticipant
import club.nito.core.network.participation.model.toNetworkModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Returning

class SupabaseParticipantRemoteDataSource(
    private val client: SupabaseClient,
) : ParticipantRemoteDataSource {
    private val postgrest = client.postgrest["participants"]

    override suspend fun getParticipants(scheduleId: String): List<Participant> = postgrest
        .select(
            filter = {
                and {
                    eq("schedule_id", scheduleId)
                    exact("deleted_at", null)
                }
            },
        )
        .decodeList<NetworkParticipant>()
        .map(NetworkParticipant::toParticipant)

    override suspend fun participate(declaration: ParticipantDeclaration): Long {
        val result = postgrest.insert(
            value = declaration.toNetworkModel(),
            upsert = true,
            returning = Returning.MINIMAL,
            filter = {},
        )
        return result.count() ?: 0
    }
}
