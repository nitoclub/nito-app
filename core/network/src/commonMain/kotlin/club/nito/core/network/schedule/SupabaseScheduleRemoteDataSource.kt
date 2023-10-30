package club.nito.core.network.schedule

import club.nito.core.model.Schedule
import club.nito.core.network.schedule.model.NetworkSchedule
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Order

class SupabaseScheduleRemoteDataSource(
    private val client: SupabaseClient,
) : ScheduleRemoteDataSource {
    private val postgrest = client.postgrest["schedules"]

    override suspend fun getScheduleList(limit: Long): List<Schedule> {
        client.gotrue

        return postgrest
            .select(
                filter = {
                    order("scheduled_at", order = Order.DESCENDING)
                    limit(count = limit)
                },
            )
            .decodeList<NetworkSchedule>()
            .map(NetworkSchedule::toSchedule)
    }

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
