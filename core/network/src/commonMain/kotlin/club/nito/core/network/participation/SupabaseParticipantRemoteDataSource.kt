package club.nito.core.network.participation

import club.nito.core.model.participant.Participant
import club.nito.core.model.participant.ParticipantDeclaration
import club.nito.core.network.NetworkService
import club.nito.core.network.participation.model.NetworkParticipant
import club.nito.core.network.participation.model.toNetworkModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Count

public class SupabaseParticipantRemoteDataSource(
    private val networkService: NetworkService,
    private val client: SupabaseClient,
) : ParticipantRemoteDataSource {
    private val postgrest = client.postgrest["participants"]

    override suspend fun getParticipants(scheduleId: String): List<Participant> = networkService {
        postgrest
            .select {
                filter {
                    and {
                        eq("schedule_id", scheduleId)
                    }
                }
            }
            .decodeList<NetworkParticipant>()
            .map(NetworkParticipant::toParticipant)
    }

    override suspend fun getParticipants(scheduleIds: List<String>): List<Participant> = networkService {
        postgrest
            .select {
                filter {
                    and {
                        isIn("schedule_id", scheduleIds)
                    }
                }
            }
            .decodeList<NetworkParticipant>()
            .map(NetworkParticipant::toParticipant)
    }

    override suspend fun participate(declaration: ParticipantDeclaration): Long = networkService {
        val result = postgrest.insert(
            value = declaration.toNetworkModel(),
        ) {
            count(Count.EXACT)
        }
        result.countOrNull() ?: 0
    }
}
