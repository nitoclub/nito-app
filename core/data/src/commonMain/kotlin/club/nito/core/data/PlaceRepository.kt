package club.nito.core.data

import club.nito.core.model.place.Place
import club.nito.core.model.place.PlaceId
import kotlinx.coroutines.flow.Flow

/**
 * 場所に関するリポジトリ
 */
public sealed interface PlaceRepository {
    /**
     * 場所のストリームを取得する
     * @param placeId 取得する場所のID
     */
    public fun placeStream(placeId: PlaceId): Flow<Place?>

    /**
     * 場所を取得する
     * @param ids 取得する場所のID
     */
    public suspend fun fetchPlaceList(vararg ids: PlaceId): List<Place>
}
