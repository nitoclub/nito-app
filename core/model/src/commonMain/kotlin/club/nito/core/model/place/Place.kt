package club.nito.core.model.place

/**
 * 場所
 * @param id ID
 * @param name 名前
 * @param url URL
 * @param description 説明文
 * @param mapUrl 地図URL
 * @param imageUrl 画像URL
 */
public data class Place(
    val id: PlaceId,
    val name: String,
    val url: String,
    val description: String,
    val mapUrl: String,
    val imageUrl: String,
)
