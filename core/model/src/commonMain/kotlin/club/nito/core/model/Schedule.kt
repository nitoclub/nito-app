package club.nito.core.model

data class Schedule(
    val id: String,
    val title: String,
    val description: String,
    val date: String,
    val location: String,
    val url: String,
    val isOnline: Boolean,
    val isFavorite: Boolean,
)
