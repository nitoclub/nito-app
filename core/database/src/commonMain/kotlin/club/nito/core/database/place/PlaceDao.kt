package club.nito.core.database.place

import club.nito.core.model.place.Place
import kotlinx.coroutines.flow.Flow

public interface PlaceDao {
    /**
     * 場所一覧のストリームを取得する
     */
    public val placesStream: Flow<List<Place>>

    /**
     * 場所のストリームを取得する
     *
     * @param placeId 取得する場所の ID
     */
    public fun placeStream(placeId: String): Flow<Place?>

    /**
     * 場所一覧のストリームを取得する
     *
     * @param placeIds 取得する場所の ID 配列
     */
    public fun placesStream(placeIds: List<String>): Flow<List<Place>>

    /**
     * スケジュール一覧を登録 / 更新する
     */
    public fun upsert(entities: List<Place>)

    /**
     * スケジュールを登録 / 更新する
     */
    public fun upsert(entity: Place)
}
