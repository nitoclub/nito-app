package club.nito.core.network.user.model

import club.nito.core.model.UserProfile
import kotlinx.serialization.Serializable

@Serializable
internal data class NetworkUserProfile(
    val id: String,
    val username: String,
    val displayName: String,
    val avatarUrl: String,
    val website: String,
) {
    fun toUserProfile() = UserProfile(
        id = id,
        username = username,
        displayName = displayName,
        avatarUrl = avatarUrl,
        website = website,
    )
}

internal fun createFakeNetworkUserProfile(
    id: String = "bbe00d24-d840-460d-a127-f23f9e472cc6",
    username: String = "username",
    displayName: String = "ユーザー名",
    avatarUrl: String = "https://placehold.jp/150x150.png",
    website: String = "https://nito.club/",
) = NetworkUserProfile(
    id = id,
    username = username,
    displayName = displayName,
    avatarUrl = avatarUrl,
    website = website,
)
