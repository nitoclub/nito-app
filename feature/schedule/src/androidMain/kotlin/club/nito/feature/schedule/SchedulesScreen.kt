package club.nito.feature.schedule

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import club.nito.core.designsystem.component.Scaffold
import club.nito.core.designsystem.component.Text
import club.nito.core.designsystem.component.TopAppBar

@Composable
fun SchedulesRoute() {
    SchedulesScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SchedulesScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Schedules",
                    )
                },
            )
        },
        content = { padding ->
            Text(
                text = "Schedules",
                modifier = Modifier.padding(padding),
            )
        }
    )
}
