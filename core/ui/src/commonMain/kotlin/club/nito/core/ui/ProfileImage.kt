package club.nito.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import club.nito.core.model.UserProfile
import com.seiko.imageloader.rememberImagePainter

@Composable
public fun ProfileImage(
    profile: UserProfile,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = rememberImagePainter(
            url = profile.avatarUrl,
        ),
        contentDescription = profile.displayName,
        contentScale = ContentScale.Crop,
        modifier = modifier.clip(CircleShape),
    )
}
