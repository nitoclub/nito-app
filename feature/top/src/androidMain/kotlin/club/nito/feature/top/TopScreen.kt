package club.nito.feature.top

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import club.nito.core.designsystem.component.CenterAlignedTopAppBar
import club.nito.core.designsystem.component.Scaffold
import club.nito.core.designsystem.component.Text
import club.nito.core.ui.ScheduleItem

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
                verticalArrangement = Arrangement.spacedBy(32.dp),
            ) {
                ScheduleSection(
                    onRecentScheduleClick = { onScheduleClick() },
                    onScheduleListClick = onScheduleClick,
                )
                Button(onClick = onSettingsClick) {
                    Text(
                        text = "Settings",
                    )
                }
            }
        },
    )
}

@Composable
private fun ScheduleSection(
    modifier: Modifier = Modifier,
    onRecentScheduleClick: (scheduleId: String) -> Unit = {},
    onScheduleListClick: () -> Unit = {},
) {
    Card(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = "スケジュール",
                fontSize = 24.sp,
                modifier = Modifier.padding(horizontal = 8.dp),
            )
            ScheduleItem(
                scheduleId = "1",
                onScheduleClick = onRecentScheduleClick,
            )
            TextButton(
                modifier = Modifier
                    .align(alignment = Alignment.End)
                    .padding(horizontal = 8.dp),
                onClick = onScheduleListClick,
            ) {
                Text(
                    text = "スケジュール一覧を見る",
                )
            }
        }
    }
}
