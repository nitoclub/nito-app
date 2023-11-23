package club.nito.feature.schedule

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
import club.nito.core.ui.ConfirmParticipateDialog
import club.nito.core.ui.koinStateMachine
import club.nito.core.ui.message.SnackbarMessageEffect
import club.nito.feature.schedule.component.ScheduleListSection

@Composable
public fun ScheduleRoute(
    viewModel: ScheduleListViewModel = koinStateMachine(),
) {
    viewModel.event.collectAsState(initial = null).value?.let {
        LaunchedEffect(it.hashCode()) {
            when (it) {
                is ScheduleListEvent.NavigateToScheduleDetail -> {}
            }
            viewModel.consume(it)
        }
    }

    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    SnackbarMessageEffect(
        snackbarHostState = snackbarHostState,
        userMessageStateHolder = viewModel.userMessageStateHolder,
    )

    ScheduleScreen(
        uiState = uiState,
        snackbarHostState = snackbarHostState,
        dispatch = viewModel::dispatch,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScheduleScreen(
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
                    dateTimeFormatter = uiState.dateTimeFormatter,
                    onParticipateRequest = { dispatch(ScheduleListIntent.ClickParticipateSchedule(it)) },
                    onDismissRequest = { dispatch(ScheduleListIntent.ClickDismissConfirmParticipateDialog) },
                )
            }

            Column(
                modifier = Modifier.padding(innerPadding),
            ) {
                ScheduleListSection(
                    scheduleList = uiState.scheduleList,
                    dateTimeFormatter = uiState.dateTimeFormatter,
                    modifier = Modifier.fillMaxSize(),
                    onScheduleClick = { dispatch(ScheduleListIntent.ClickShowConfirmParticipateDialog(it)) },
                )
            }
        },
    )
}
