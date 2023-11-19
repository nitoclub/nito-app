package club.nito.core.network.schedule

import club.nito.core.model.Order
import club.nito.core.model.Schedule
import club.nito.core.network.schedule.model.NetworkSchedule
import club.nito.core.network.toSupabaseOrder
import co.touchlab.kermit.Logger
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.datetime.Instant

private enum class Column(val columnName: String) {
    DELETED_AT(columnName = "deleted_at"),
    SCHEDULED_AT(columnName = "scheduled_at"),
}

class SupabaseScheduleRemoteDataSource(
    private val client: SupabaseClient,
) : ScheduleRemoteDataSource {
    private val log = Logger.withTag("SupabaseScheduleRemoteDataSource")
    private val postgrest = client.postgrest["schedules"]

    override suspend fun getScheduleList(
        limit: Int,
        order: Order,
        after: Instant?,
    ): List<Schedule> = postgrest
        .select(
            filter = {
                exact(Column.DELETED_AT.columnName, null)
                after?.let { gte(Column.SCHEDULED_AT.columnName, it) }
                order(Column.SCHEDULED_AT.columnName, order = order.toSupabaseOrder())
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
