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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.hilt.navigation.compose.hiltViewModel
import club.nito.core.common.previewNitoDateTimeFormatter
import club.nito.core.designsystem.component.CenterAlignedTopAppBar
import club.nito.core.designsystem.component.Scaffold
import club.nito.core.designsystem.component.Text
import club.nito.core.designsystem.theme.NitoTheme
import club.nito.core.model.FetchMultipleContentResult
import club.nito.core.ui.ConfirmParticipateDialog
import club.nito.core.ui.message.SnackbarMessageEffect
import club.nito.feature.schedule.component.ScheduleListSection

@Composable
fun ScheduleRoute(
    viewModel: ScheduleListViewModel = hiltViewModel(),
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

private class ScheduleListScreenUiStatePreviewParameterProvider :
    PreviewParameterProvider<ScheduleListScreenUiState> {
    private val dateTimeFormatter = previewNitoDateTimeFormatter
    override val values: Sequence<ScheduleListScreenUiState> = sequenceOf(
        ScheduleListScreenUiState(
            dateTimeFormatter = dateTimeFormatter,
            scheduleList = FetchMultipleContentResult.Loading,
            confirmParticipateDialog = ConfirmParticipateDialogUiState.Hide,
        ),
    )
}

@Preview
@Composable
fun PreviewScheduleScreen(
    @PreviewParameter(ScheduleListScreenUiStatePreviewParameterProvider::class) uiState: ScheduleListScreenUiState,
) {
    NitoTheme {
        ScheduleScreen(
            uiState = uiState,
            snackbarHostState = SnackbarHostState(),
        )
    }
}
