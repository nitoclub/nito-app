package club.nito.core.data

import club.nito.core.database.place.PlaceDao
import club.nito.core.model.place.Place
import club.nito.core.model.place.PlaceId
import club.nito.core.network.place.PlaceRemoteDataSource
import kotlinx.coroutines.flow.Flow

public class DefaultPlaceRepository(
    private val remoteDataSource: PlaceRemoteDataSource,
    private val placeDao: PlaceDao,
) : PlaceRepository {
    override fun placeStream(placeId: PlaceId): Flow<Place?> = placeDao.placeStream(placeId)

    override suspend fun fetchPlaceList(vararg ids: PlaceId): List<Place> {
        return remoteDataSource.fetchPlaceList(ids.toList()).also {
            placeDao.upsert(it)
        }
    }
}
