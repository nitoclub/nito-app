package club.nito.feature.schedule.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import club.nito.core.designsystem.component.CenterAlignedTopAppBar
import club.nito.core.designsystem.component.Scaffold
import club.nito.core.designsystem.component.Text
import club.nito.core.model.schedule.ScheduleId
import club.nito.core.ui.ConfirmParticipateDialog
import club.nito.core.ui.koinStateMachine
import club.nito.core.ui.message.SnackbarMessageEffect
import club.nito.feature.schedule.component.ScheduleListSection

@Composable
public fun ScheduleListRoute(
    stateMachine: ScheduleListStateMachine = koinStateMachine(ScheduleListStateMachine::class),
    onScheduleItemClick: (ScheduleId) -> Unit = {},
) {
    stateMachine.event.collectAsState(initial = null).value?.let {
        LaunchedEffect(it.hashCode()) {
            when (it) {
                is ScheduleListEvent.OnScheduleItemClick -> onScheduleItemClick(it.scheduleId)
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

    ScheduleListScreen(
        uiState = uiState,
        snackbarHostState = snackbarHostState,
        dispatch = stateMachine::dispatch,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScheduleListScreen(
    uiState: ScheduleListScreenUiState,
    snackbarHostState: SnackbarHostState,
    dispatch: (ScheduleListIntent) -> Unit = {},
) {
    val confirmParticipateDialog = uiState.confirmParticipateDialog

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "スケジュール一覧",
                    )
                },
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        content = { innerPadding ->
            if (confirmParticipateDialog is ConfirmParticipateDialogUiState.Show) {
                ConfirmParticipateDialog(
                    schedule = confirmParticipateDialog.schedule,
                    dateTimeFormatter = uiState.dateFormatter,
                    onParticipateRequest = { dispatch(ScheduleListIntent.ClickParticipateSchedule(it)) },
                    onDismissRequest = { dispatch(ScheduleListIntent.ClickDismissConfirmParticipateDialog) },
                )
            }

            Column(
                modifier = Modifier.padding(innerPadding),
            ) {
                ScheduleListSection(
                    scheduleList = uiState.scheduleList,
                    dateTimeFormatter = uiState.dateFormatter,
                    modifier = Modifier.fillMaxSize(),
                    onScheduleClick = { dispatch(ScheduleListIntent.ClickShowConfirmParticipateDialog(it)) },
                )
            }
        },
    )
}
