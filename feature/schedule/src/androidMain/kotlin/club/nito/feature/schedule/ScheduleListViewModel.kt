package club.nito.feature.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import club.nito.core.common.NitoDateTimeFormatter
import club.nito.core.domain.GetParticipantScheduleListUseCase
import club.nito.core.domain.model.ParticipantSchedule
import club.nito.core.model.FetchMultipleContentResult
import club.nito.core.ui.buildUiState
import club.nito.core.ui.message.UserMessageStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleListViewModel @Inject constructor(
    getParticipantScheduleListUseCase: GetParticipantScheduleListUseCase,
    val userMessageStateHolder: UserMessageStateHolder,
    private val dateTimeFormatter: NitoDateTimeFormatter,
) : ViewModel(),
    UserMessageStateHolder by userMessageStateHolder {
    private val showConfirmParticipateSchedule = MutableStateFlow<ParticipantSchedule?>(null)

    private val scheduleList = getParticipantScheduleListUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = FetchMultipleContentResult.Loading,
    )

    val uiState: StateFlow<ScheduleListScreenUiState> = buildUiState(
        showConfirmParticipateSchedule,
        scheduleList,
    ) { showConfirmParticipateSchedule, scheduleList ->
        ScheduleListScreenUiState(
            dateTimeFormatter = dateTimeFormatter,
            scheduleList = scheduleList,
            confirmParticipateDialog = showConfirmParticipateSchedule
                ?.let(ConfirmParticipateDialogUiState::Show)
                ?: ConfirmParticipateDialogUiState.Hide,
        )
    }

    private val _events = MutableStateFlow<List<ScheduleListEvent>>(emptyList())
    val event: Flow<ScheduleListEvent?> = _events.map { it.firstOrNull() }

    fun dispatch(intent: ScheduleListIntent) {
        viewModelScope.launch {
            when (intent) {
                is ScheduleListIntent.ClickShowConfirmParticipateDialog -> showConfirmParticipateSchedule.emit(intent.schedule)
                is ScheduleListIntent.ClickParticipateSchedule -> {
                    showConfirmParticipateSchedule.emit(null)

                    val scheduledAt = dateTimeFormatter.formatDateTimeString(intent.schedule.scheduledAt)
                    userMessageStateHolder.showMessage("$scheduledAt に参加登録しました 🎉")
                }

                ScheduleListIntent.ClickDismissConfirmParticipateDialog -> showConfirmParticipateSchedule.emit(null)
            }
        }
    }

    fun consume(event: ScheduleListEvent) {
        viewModelScope.launch {
            _events.emit(_events.value.filterNot { it == event })
        }
    }
}