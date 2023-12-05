package club.nito.core.network.place

import club.nito.core.model.place.Place
import club.nito.core.model.place.PlaceId

/**
 * 場所リモートデータソース
 */
public sealed interface PlaceRemoteDataSource {
    /**
     * リモートから場所を取得する
     * @param idList 取得する場所のID
     */
    public suspend fun fetchPlaceList(idList: List<PlaceId>): List<Place>
}
