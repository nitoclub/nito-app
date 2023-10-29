package club.nito.feature.schedule

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import club.nito.core.designsystem.component.CenterAlignedTopAppBar
import club.nito.core.designsystem.component.Scaffold
import club.nito.core.designsystem.component.Text

@Composable
fun ScheduleRoute() {
    ScheduleScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScheduleScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Schedule",
                    )
                },
            )
        },
        content = { padding ->
            Text(
                text = "Schedule",
                modifier = Modifier.padding(padding),
            )
        },
    )
}
