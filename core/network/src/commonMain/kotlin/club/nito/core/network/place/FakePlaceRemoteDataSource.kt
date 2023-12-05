package club.nito.core.network.place

import club.nito.core.model.place.Place
import club.nito.core.model.place.PlaceId
import club.nito.core.network.place.model.NetworkPlace
import club.nito.core.network.place.model.createFakeNetworkPlace

public data object FakePlaceRemoteDataSource : PlaceRemoteDataSource {
    override suspend fun fetchPlaceList(idList: List<PlaceId>): List<Place> = idList.map { id ->
        createFakeNetworkPlace(
            id = id,
        ).let(NetworkPlace::toPlace)
    }
}
