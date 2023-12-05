package club.nito.feature.schedule.detail

import androidx.compose.foundation.layout.Column
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
import org.koin.core.parameter.parametersOf

@Composable
public fun ScheduleDetailRoute(
    id: ScheduleId,
    stateMachine: ScheduleDetailStateMachine = koinStateMachine(ScheduleDetailStateMachine::class) {
        parametersOf(id)
    },
) {
    stateMachine.event.collectAsState(initial = null).value?.let {
        LaunchedEffect(it.hashCode()) {
            when (it) {
                is ScheduleDetailEvent.NavigateToScheduleDetail -> {}
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

    ScheduleDetailScreen(
        uiState = uiState,
        snackbarHostState = snackbarHostState,
        dispatch = stateMachine::dispatch,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScheduleDetailScreen(
    uiState: ScheduleDetailScreenUiState,
    snackbarHostState: SnackbarHostState,
    dispatch: (ScheduleDetailIntent) -> Unit = {},
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
                    onParticipateRequest = { dispatch(ScheduleDetailIntent.ClickParticipateSchedule(it)) },
                    onDismissRequest = { dispatch(ScheduleDetailIntent.ClickDismissConfirmParticipateDialog) },
                )
            }

            Column(
                modifier = Modifier.padding(innerPadding),
            ) {
            }
        },
    )
}
