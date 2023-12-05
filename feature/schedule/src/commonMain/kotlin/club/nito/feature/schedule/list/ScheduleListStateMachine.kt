package club.nito.feature.schedule.list

import club.nito.core.common.NitoDateFormatter
import club.nito.core.domain.GetParticipantScheduleListUseCase
import club.nito.core.domain.model.ParticipantSchedule
import club.nito.core.model.FetchMultipleContentResult
import club.nito.core.ui.StateMachine
import club.nito.core.ui.buildUiState
import club.nito.core.ui.message.UserMessageStateHolder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.viewModelScope

public class ScheduleListStateMachine(
    getParticipantScheduleList: GetParticipantScheduleListUseCase,
    public val userMessageStateHolder: UserMessageStateHolder,
    private val dateFormatter: NitoDateFormatter,
) : StateMachine(),
    UserMessageStateHolder by userMessageStateHolder {
    private val showConfirmParticipateSchedule = MutableStateFlow<ParticipantSchedule?>(null)

    private val scheduleList = getParticipantScheduleList().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = FetchMultipleContentResult.Loading,
    )

    public val uiState: StateFlow<ScheduleListScreenUiState> = buildUiState(
        showConfirmParticipateSchedule,
        scheduleList,
    ) { showConfirmParticipateSchedule, scheduleList ->
        ScheduleListScreenUiState(
            dateFormatter = dateFormatter,
            scheduleList = scheduleList,
            confirmParticipateDialog = showConfirmParticipateSchedule
                ?.let(ConfirmParticipateDialogUiState::Show)
                ?: ConfirmParticipateDialogUiState.Hide,
        )
    }

    private val _events = MutableStateFlow<List<ScheduleListEvent>>(emptyList())
    public val event: Flow<ScheduleListEvent?> = _events.map { it.firstOrNull() }

    public fun dispatch(intent: ScheduleListIntent) {
        viewModelScope.launch {
            when (intent) {
                is ScheduleListIntent.ClickShowConfirmParticipateDialog -> {
                    _events.emit(_events.value + ScheduleListEvent.OnScheduleItemClick(intent.schedule.id))
                }

                is ScheduleListIntent.ClickParticipateSchedule -> {
                    showConfirmParticipateSchedule.emit(null)

                    val scheduledAt = dateFormatter.formatDateTime(intent.schedule.scheduledAt)
                    userMessageStateHolder.showMessage("$scheduledAt に参加登録しました 🎉")
                }

                ScheduleListIntent.ClickDismissConfirmParticipateDialog -> {
                    showConfirmParticipateSchedule.emit(null)
                }
            }
        }
    }

    public fun consume(event: ScheduleListEvent) {
        viewModelScope.launch {
            _events.emit(_events.value.filterNot { it == event })
        }
    }
}
