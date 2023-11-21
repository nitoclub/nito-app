package club.nito.feature.top

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import club.nito.core.ui.ConfirmParticipateDialog
import club.nito.core.ui.message.SnackbarMessageEffect
import club.nito.feature.top.component.ParticipantScheduleSection
import org.koin.compose.koinInject

@Composable
fun TopRoute(
    stateMachine: TopStateMachine = koinInject(),
    onScheduleListClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
) {
    stateMachine.event.collectAsState(initial = null).value?.let {
        LaunchedEffect(it.hashCode()) {
            when (it) {
                TopScreenEvent.NavigateToScheduleList -> onScheduleListClick()
                TopScreenEvent.NavigateToSettings -> onSettingsClick()
            }
            stateMachine.consume(it)
        }
    }

    val uiState by stateMachine.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    SnackbarMessageEffect(
        snackbarHostState = snackbarHostState,
        userMessageStateHolder = stateMachine.userMessageStateHolder,
    )

    TopScreen(
        uiState = uiState,
        snackbarHostState = snackbarHostState,
        dispatch = stateMachine::dispatch,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopScreen(
    uiState: TopScreenUiState,
    snackbarHostState: SnackbarHostState,
    dispatch: (TopScreenIntent) -> Unit = {},
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
                    IconButton(onClick = { dispatch(TopScreenIntent.ClickSettings) }) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                        )
                    }
                },
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        content = { padding ->
            if (confirmParticipateDialog is ConfirmParticipateDialogUiState.Show) {
                ConfirmParticipateDialog(
                    schedule = confirmParticipateDialog.schedule,
                    dateTimeFormatter = uiState.dateTimeFormatter,
                    onParticipateRequest = { dispatch(TopScreenIntent.ClickParticipateSchedule(it)) },
                    onDismissRequest = { dispatch(TopScreenIntent.ClickDismissConfirmParticipateDialog) },
                )
            }

            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(32.dp),
            ) {
                ParticipantScheduleSection(
                    recentSchedule = recentSchedule,
                    dateTimeFormatter = uiState.dateTimeFormatter,
                    onRecentScheduleClick = { dispatch(TopScreenIntent.ClickShowConfirmParticipateDialog(it)) },
                    onScheduleListClick = { dispatch(TopScreenIntent.ClickScheduleList) },
                )
            }
        },
    )
}
