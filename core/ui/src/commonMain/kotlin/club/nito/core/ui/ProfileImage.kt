package club.nito.core.ui

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import club.nito.core.designsystem.icon.NitoIcons
import club.nito.core.model.UserProfile
import coil3.compose.AsyncImage

@Composable
public fun ProfileImage(
    profile: UserProfile,
    modifier: Modifier = Modifier,
) {
    if (profile.avatarUrl.isNotBlank()) {
        AsyncImage(
            model = profile.avatarUrl,
            contentDescription = profile.displayName,
            contentScale = ContentScale.Crop,
            modifier = modifier.clip(CircleShape),
        )
    } else {
        AsyncImage(
            model = NitoIcons.AccountCircle,
            contentDescription = profile.displayName,
            modifier = modifier,
        )
    }
}
