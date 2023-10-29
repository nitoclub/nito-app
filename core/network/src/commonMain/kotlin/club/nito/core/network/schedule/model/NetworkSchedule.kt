package club.nito.core.network.schedule.model

import club.nito.core.model.Schedule

internal data class NetworkSchedule(
    val id: String,
    val title: String,
    val description: String,
    val date: String,
    val location: String,
    val url: String,
    val isOnline: Boolean,
    val isFavorite: Boolean,
) {
    fun toSchedule() = Schedule(
        id = id,
        title = title,
        description = description,
        date = date,
        location = location,
        url = url,
        isOnline = isOnline,
        isFavorite = isFavorite,
    )
}

internal val dummyNetworkSchedule = NetworkSchedule(
    id = "1",
    title = "Title",
    description = "Description",
    date = "2021/01/01",
    location = "Location",
    url = "https://example.com",
    isOnline = true,
    isFavorite = false,
)

internal fun createFakeNetworkSchedule(
    id: String = "1",
    title: String = "Title",
    description: String = "Description",
    date: String = "2021/01/01",
    location: String = "Location",
    url: String = "https://example.com",
    isOnline: Boolean = true,
    isFavorite: Boolean = false,
) = NetworkSchedule(
    id = id,
    title = title,
    description = description,
    date = date,
    location = location,
    url = url,
    isOnline = isOnline,
    isFavorite = isFavorite,
)
