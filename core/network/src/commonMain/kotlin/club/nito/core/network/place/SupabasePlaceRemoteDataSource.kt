package club.nito.core.network.place

import club.nito.core.model.place.Place
import club.nito.core.model.place.PlaceId
import club.nito.core.network.NetworkService
import club.nito.core.network.place.model.NetworkPlace
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest

private enum class Column(val columnName: String) {
    ID(columnName = "id"),
    DELETED_AT(columnName = "deleted_at"),
}

public class SupabasePlaceRemoteDataSource(
    private val networkService: NetworkService,
    private val client: SupabaseClient,
) : PlaceRemoteDataSource {
    private val postgrest = client.postgrest["places"]

    override suspend fun fetchPlaceList(idList: List<PlaceId>): List<Place> = networkService {
        postgrest
            .select {
                filter {
                    isIn(Column.ID.columnName, idList)
                    exact(Column.DELETED_AT.columnName, null)
                }
            }
            .decodeList<NetworkPlace>()
            .map(NetworkPlace::toPlace)
    }
}
