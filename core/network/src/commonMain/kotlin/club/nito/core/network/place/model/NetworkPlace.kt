package club.nito.core.network.place.model

import club.nito.core.model.place.Place
import kotlinx.serialization.Serializable

/**
 * 場所
 * @param id ID
 * @param name 名前
 * @param url URL
 * @param description 説明文
 * @param mapUrl 地図URL
 * @param imageUrl 画像URL
 */
@Serializable
public data class NetworkPlace internal constructor(
    val id: String,
    val name: String,
    val url: String,
    val description: String,
    val mapUrl: String,
    val imageUrl: String,
) {
    internal fun toPlace() = Place(
        id = id,
        name = name,
        url = url,
        description = description.replace("\\n", "\n"),
        mapUrl = mapUrl,
        imageUrl = imageUrl,
    )
}

internal fun createFakeNetworkPlace(
    id: String = "bbe00d24-d840-460d-a127-f23f9e472cc6",
    name: String = "レイクタウン",
    url: String = "https://www.google.com",
    description: String = "レイクタウンは、埼玉県越谷市にある複合商業施設である。2008年（平成20年）3月14日に開業した。運営は三井不動産商業マネジメントが行っている。",
    mapUrl: String = "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3239.919508821314!2d139.790403315258!3d35.89174898014363!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x6018f3b0b0b0b0b1%3A0x1b0b0b0b0b0b0b0b!2z44CSMzUwLTAwMjQg5p2x5Lqs6YO95riv5Yy65Y2X6YCa5L2c5a2Q5bGx77yR5LiB55uu77yR77yR4oiS77yR!5e0!3m2!1sja!2sjp!4v1629788517009!5m2!1sja!2sjp",
    imageUrl: String = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6e/Laketown_outside.jpg/1200px-Laketown_outside.jpg",
) = NetworkPlace(
    id = id,
    name = name,
    url = url,
    description = description,
    mapUrl = mapUrl,
    imageUrl = imageUrl,
)
