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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import club.nito.core.designsystem.component.CenterAlignedTopAppBar
import club.nito.core.designsystem.component.Scaffold
import club.nito.core.designsystem.component.Text
import club.nito.feature.top.component.ConfirmParticipateDialog
import club.nito.feature.top.component.ScheduleSection

@Composable
fun TopRoute(
    viewModel: TopViewModel = hiltViewModel(),
    onScheduleListClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
) {
    viewModel.event.collectAsState(initial = null).value?.let {
        LaunchedEffect(it.hashCode()) {
            when (it) {
                TopEvent.NavigateToScheduleList -> onScheduleListClick()
                TopEvent.NavigateToSettings -> onSettingsClick()
            }
            viewModel.consume(it)
        }
    }

    val uiState by viewModel.uiState.collectAsState()

    TopScreen(
        uiState = uiState,
        dispatch = viewModel::dispatch,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopScreen(
    uiState: TopScreenUiState,
    dispatch: (TopIntent) -> Unit = {},
) {
    val recentSchedule = uiState.recentSchedule
    val confirmParticipateDialog = uiState.confirmParticipateDialog

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "トップ",
                    )
                },
                actions = {
                    IconButton(onClick = { dispatch(TopIntent.ClickSettings) }) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                        )
                    }
                },
            )
        },
        content = { padding ->
            if (confirmParticipateDialog is ConfirmParticipateDialogUiState.Show) {
                ConfirmParticipateDialog(
                    schedule = confirmParticipateDialog.schedule,
                    onDismissRequest = { dispatch(TopIntent.ClickDismissConfirmParticipateDialog) },
                )
            }

            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(32.dp),
            ) {
                ScheduleSection(
                    recentSchedule = recentSchedule,
                    onRecentScheduleClick = { dispatch(TopIntent.ClickShowConfirmParticipateDialog(it)) },
                    onScheduleListClick = { dispatch(TopIntent.ClickScheduleList) },
                )
            }
        },
    )
}
