package club.nito.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import club.nito.core.model.UserProfile

@Composable
expect fun ProfileImage(
    profile: UserProfile,
    modifier: Modifier = Modifier,
)
