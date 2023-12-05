package club.nito.core.network.schedule

import club.nito.core.model.Order
import club.nito.core.model.schedule.Schedule
import club.nito.core.model.schedule.ScheduleId
import club.nito.core.network.NetworkService
import club.nito.core.network.schedule.model.NetworkSchedule
import club.nito.core.network.toSupabaseOrder
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.datetime.Instant

private enum class Column(val columnName: String) {
    ID(columnName = "id"),
    DELETED_AT(columnName = "deleted_at"),
    SCHEDULED_AT(columnName = "scheduled_at"),
}

public class SupabaseScheduleRemoteDataSource(
    private val networkService: NetworkService,
    private val client: SupabaseClient,
) : ScheduleRemoteDataSource {
    private val postgrest = client.postgrest["schedules"]

    override suspend fun getScheduleList(
        limit: Int,
        order: Order,
        after: Instant?,
    ): List<Schedule> = networkService {
        postgrest
            .select {
                filter {
                    exact(Column.DELETED_AT.columnName, null)
                    after?.let { gte(Column.SCHEDULED_AT.columnName, it) }
                }
                order(Column.SCHEDULED_AT.columnName, order = order.toSupabaseOrder())
                limit(count = limit.toLong())
            }
            .decodeList<NetworkSchedule>()
            .map(NetworkSchedule::toSchedule)
    }

    override suspend fun fetchSchedule(id: ScheduleId): Schedule = networkService {
        postgrest
            .select {
                single()
                filter {
                    eq(Column.ID.columnName, id)
                    exact(Column.DELETED_AT.columnName, null)
                }
            }
            .decodeSingle<NetworkSchedule>()
            .let(NetworkSchedule::toSchedule)
    }
}
