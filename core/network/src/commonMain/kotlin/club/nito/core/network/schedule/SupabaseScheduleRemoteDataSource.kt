package club.nito.core.network.schedule

import club.nito.core.model.Schedule
import club.nito.core.network.schedule.model.NetworkSchedule
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest

class SupabaseScheduleRemoteDataSource(
    private val client: SupabaseClient,
) : ScheduleRemoteDataSource {
    private val postgrest = client.postgrest["schedules"]

    override suspend fun getScheduleList(): List<Schedule> {
        return postgrest
            .select()
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
