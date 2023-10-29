package club.nito.feature.top

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import club.nito.core.designsystem.component.CenterAlignedTopAppBar
import club.nito.core.designsystem.component.Scaffold
import club.nito.core.designsystem.component.Text
import club.nito.feature.top.component.ScheduleSection

@Composable
fun TopRoute(
    viewModel: TopViewModel = hiltViewModel(),
    onScheduleClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsState()

    TopScreen(
        uiState = uiState,
        onScheduleClick = onScheduleClick,
        onSettingsClick = onSettingsClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopScreen(
    uiState: TopScreenUiState,
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
                actions = {
                    IconButton(onClick = onSettingsClick) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                        )
                    }
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
                    recentSchedule = uiState.recentSchedule,
                    onRecentScheduleClick = { onScheduleClick() },
                    onScheduleListClick = onScheduleClick,
                )
            }
        },
    )
}
