package club.nito.core.data

import club.nito.core.model.place.Place
import club.nito.core.model.place.PlaceId

/**
 * 場所に関するリポジトリ
 */
public sealed interface PlaceRepository {
    /**
     * 場所を取得する
     * @param ids 取得する場所のID
     */
    public suspend fun fetchPlaceList(vararg ids: PlaceId): List<Place>
}
