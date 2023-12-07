package club.nito.core.network.participation

import club.nito.core.model.participant.Participant
import club.nito.core.model.participant.ParticipantDeclaration
import club.nito.core.model.participant.ParticipantStatus
import club.nito.core.model.schedule.ScheduleId
import club.nito.core.network.NetworkService
import club.nito.core.network.participation.model.NetworkParticipant
import club.nito.core.network.participation.model.toNetworkModel
import club.nito.core.network.participation.model.toParticipantStatus
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

    override suspend fun existParticipantByUserId(scheduleId: ScheduleId, userId: String): Boolean {
        val count = postgrest
            .select {
                count(Count.EXACT)
                filter {
                    and {
                        eq("schedule_id", scheduleId)
                        eq("user_id", userId)
                    }
                }
            }
            .countOrNull() ?: 0

        return count > 0
    }

    override suspend fun fetchParticipantStatus(scheduleId: ScheduleId, userId: String): ParticipantStatus =
        networkService {
            postgrest
                .select {
                    filter {
                        and {
                            eq("schedule_id", scheduleId)
                            eq("user_id", userId)
                        }
                    }
                }
                .decodeSingleOrNull<NetworkParticipant>()
                .toParticipantStatus()
        }

    override suspend fun insertParticipate(declaration: ParticipantDeclaration): Participant = networkService {
        postgrest.insert(
            value = declaration.toNetworkModel(),
        ) {
            select()
            single()
        }
            .decodeAs<NetworkParticipant>()
            .let(NetworkParticipant::toParticipant)
    }

    override suspend fun updateParticipate(declaration: ParticipantDeclaration): Participant = networkService {
        postgrest.update(
            update = {
                set("status", declaration.status.toNetworkModel())
            },
            request = {
                select()
                single()
                filter {
                    and {
                        eq("schedule_id", declaration.scheduleId)
                        eq("user_id", declaration.userId)
                    }
                }
            },
        )
            .decodeAs<NetworkParticipant>()
            .let(NetworkParticipant::toParticipant)
    }
}
