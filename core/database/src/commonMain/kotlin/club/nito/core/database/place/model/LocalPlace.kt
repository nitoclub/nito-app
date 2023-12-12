package club.nito.core.database.place.model

import club.nito.core.model.place.Place
import club.nito.core.database.Places as LocalPlace

internal fun LocalPlace.toModel(): Place = Place(
    id = id,
    name = name,
    url = url,
    description = description,
    mapUrl = map_url,
    imageUrl = image_url,
)
