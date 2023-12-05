package club.nito.core.data

import club.nito.core.model.place.Place
import club.nito.core.model.place.PlaceId
import club.nito.core.network.place.PlaceRemoteDataSource

public class DefaultPlaceRepository(
    private val remoteDataSource: PlaceRemoteDataSource,
) : PlaceRepository {
    override suspend fun fetchPlaceList(vararg ids: PlaceId): List<Place> {
        return remoteDataSource.fetchPlaceList(ids.toList())
    }
}
