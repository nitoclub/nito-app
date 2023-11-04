package club.nito.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import club.nito.core.model.UserProfile

@Composable
fun ProfileImagesRow(
    profiles: List<UserProfile>,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy((-24).dp, Alignment.End),
        reverseLayout = true,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        items(
            items = profiles,
            key = { profile -> profile.id },
        ) { profile ->
            ProfileImage(
                profile = profile,
                modifier = Modifier.size(48.dp),
            )
        }
    }
}
