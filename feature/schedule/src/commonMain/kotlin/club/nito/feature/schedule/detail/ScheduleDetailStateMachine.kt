package club.nito.feature.schedule.detail

import club.nito.core.common.NitoDateFormatter
import club.nito.core.domain.FetchParticipantScheduleByIdUseCase
import club.nito.core.domain.model.ParticipantSchedule
import club.nito.core.model.FetchSingleContentResult
import club.nito.core.ui.StateMachine
import club.nito.core.ui.buildUiState
import club.nito.core.ui.message.UserMessageStateHolder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.viewModelScope

public class ScheduleDetailStateMachine(
    fetchParticipantScheduleById: FetchParticipantScheduleByIdUseCase,
    public val userMessageStateHolder: UserMessageStateHolder,
    private val dateTimeFormatter: NitoDateFormatter,
) : StateMachine(),
    UserMessageStateHolder by userMessageStateHolder {
    private val showConfirmParticipateSchedule = MutableStateFlow<ParticipantSchedule?>(null)
    private val participantSchedule: MutableStateFlow<FetchSingleContentResult<ParticipantSchedule>> =
        MutableStateFlow(FetchSingleContentResult.Loading)

    public val uiState: StateFlow<ScheduleDetailScreenUiState> = buildUiState(
        showConfirmParticipateSchedule,
        participantSchedule,
    ) { showConfirmParticipateSchedule, participantSchedule ->
        ScheduleDetailScreenUiState(
            dateFormatter = dateTimeFormatter,
            scheduleList = participantSchedule,
            confirmParticipateDialog = showConfirmParticipateSchedule
                ?.let(ConfirmParticipateDialogUiState::Show)
                ?: ConfirmParticipateDialogUiState.Hide,
        )
    }

    private val _events = MutableStateFlow<List<ScheduleDetailEvent>>(emptyList())
    public val event: Flow<ScheduleDetailEvent?> = _events.map { it.firstOrNull() }

    init {
        viewModelScope.launch {
            participantSchedule.value = fetchParticipantScheduleById(id = "")
        }
    }

    public fun dispatch(intent: ScheduleDetailIntent) {
        viewModelScope.launch {
            when (intent) {
                is ScheduleDetailIntent.ClickShowConfirmParticipateDialog -> {
                    showConfirmParticipateSchedule.emit(intent.schedule)
                }

                is ScheduleDetailIntent.ClickParticipateSchedule -> {
                    showConfirmParticipateSchedule.emit(null)

                    val scheduledAt = dateTimeFormatter.formatDateTime(intent.schedule.scheduledAt)
                    userMessageStateHolder.showMessage("$scheduledAt に参加登録しました 🎉")
                }

                ScheduleDetailIntent.ClickDismissConfirmParticipateDialog -> {
                    showConfirmParticipateSchedule.emit(null)
                }
            }
        }
    }

    public fun consume(event: ScheduleDetailEvent) {
        viewModelScope.launch {
            _events.emit(_events.value.filterNot { it == event })
        }
    }
}
