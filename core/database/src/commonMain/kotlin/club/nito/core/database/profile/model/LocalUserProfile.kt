package club.nito.core.database.profile.model

import club.nito.core.model.UserProfile
import club.nito.core.database.Profiles as LocalUserProfile

internal fun LocalUserProfile.toModel(): UserProfile = UserProfile(
    id = id,
    username = username,
    displayName = display_name,
    avatarUrl = avatar_url,
    website = website,
)
