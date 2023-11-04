package club.nito.core.ui

import android.content.Context
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import club.nito.core.model.UserProfile
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun ProfileImage(
    profile: UserProfile,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
) {
    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(profile.avatarUrl)
            .crossfade(true)
            .build(),
        contentDescription = profile.displayName,
        contentScale = ContentScale.Crop,
        modifier = modifier.clip(CircleShape),
    )
}
