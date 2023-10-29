package club.nito.feature.top

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import club.nito.core.designsystem.component.CenterAlignedTopAppBar
import club.nito.core.designsystem.component.Scaffold
import club.nito.core.designsystem.component.Text

@Composable
fun TopRoute(
    onScheduleClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
) {
    TopScreen(
        onScheduleClick = onScheduleClick,
        onSettingsClick = onSettingsClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopScreen(
    onScheduleClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Top",
                    )
                },
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp),
            ) {
                Text(
                    text = "Top",
                )
                Button(onClick = onScheduleClick) {
                    Text(
                        text = "Schedule",
                    )
                }
                Button(onClick = onSettingsClick) {
                    Text(
                        text = "Settings",
                    )
                }
            }
        },
    )
}
