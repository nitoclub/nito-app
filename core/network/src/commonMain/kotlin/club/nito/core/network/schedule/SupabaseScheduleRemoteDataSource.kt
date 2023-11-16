package club.nito.core.network.schedule

import club.nito.core.model.Schedule
import club.nito.core.network.schedule.model.NetworkSchedule
import co.touchlab.kermit.Logger
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Order

class SupabaseScheduleRemoteDataSource(
    private val client: SupabaseClient,
) : ScheduleRemoteDataSource {
    private val log = Logger.withTag("SupabaseScheduleRemoteDataSource")
    private val postgrest = client.postgrest["schedules"]

    override suspend fun getScheduleList(limit: Int): List<Schedule> = postgrest
        .select(
            filter = {
                order("scheduled_at", order = Order.DESCENDING)
                limit(count = limit.toLong())
            },
        )
        .decodeList<NetworkSchedule>()
        .map(NetworkSchedule::toSchedule)
        .also { log.d { "getScheduleList: $it" } }

    override suspend fun getSchedule(id: String): Schedule {
        return postgrest
            .select(
                single = true,
                filter = { eq("id", id) },
            )
            .decodeSingle<NetworkSchedule>()
            .toSchedule()
    }
}
