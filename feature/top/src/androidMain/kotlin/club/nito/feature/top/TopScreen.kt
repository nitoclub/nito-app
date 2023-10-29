package club.nito.feature.top

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import club.nito.core.designsystem.component.Scaffold
import club.nito.core.designsystem.component.Text
import club.nito.core.designsystem.component.TopAppBar

@Composable
fun TopRoute() {
    TopScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Top",
                    )
                },
            )
        },
        content = { padding ->
            Text(
                text = "Top",
                modifier = Modifier.padding(padding),
            )
        }
    )
}