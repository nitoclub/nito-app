package club.nito.feature.top

import club.nito.core.common.NitoDateTimeFormatter
import club.nito.core.domain.GetRecentScheduleUseCase
import club.nito.core.domain.model.ParticipantSchedule
import club.nito.core.model.FetchSingleContentResult
import club.nito.core.ui.StateMachine
import club.nito.core.ui.buildUiState
import club.nito.core.ui.message.UserMessageStateHolder
import club.nito.core.ui.stateMachineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TopScreenStateMachine internal constructor(
    getRecentSchedule: GetRecentScheduleUseCase,
    val userMessageStateHolder: UserMessageStateHolder,
    private val dateTimeFormatter: NitoDateTimeFormatter,
) : StateMachine(), UserMessageStateHolder by userMessageStateHolder {
    private val showConfirmParticipateSchedule = MutableStateFlow<ParticipantSchedule?>(null)

    private val recentSchedule = getRecentSchedule().stateIn(
        scope = stateMachineScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = FetchSingleContentResult.Loading,
    )

    val uiState: StateFlow<TopScreenUiState> = buildUiState(
        showConfirmParticipateSchedule,
        recentSchedule,
    ) { showConfirmParticipateSchedule, recentSchedule ->
        TopScreenUiState(
            dateTimeFormatter = dateTimeFormatter,
            recentSchedule = recentSchedule,
            confirmParticipateDialog = showConfirmParticipateSchedule
                ?.let(ConfirmParticipateDialogUiState::Show)
                ?: ConfirmParticipateDialogUiState.Hide,
        )
    }

    private val _events = MutableStateFlow<List<TopScreenEvent>>(emptyList())
    val event: Flow<TopScreenEvent?> = _events.map { it.firstOrNull() }

    fun dispatch(intent: TopScreenIntent) {
        stateMachineScope.launch {
            when (intent) {
                is TopScreenIntent.ClickShowConfirmParticipateDialog -> showConfirmParticipateSchedule.emit(intent.schedule)
                is TopScreenIntent.ClickParticipateSchedule -> {
                    showConfirmParticipateSchedule.emit(null)

                    val scheduledAt = dateTimeFormatter.formatDateTimeString(intent.schedule.scheduledAt)
                    userMessageStateHolder.showMessage("$scheduledAt に参加登録しました 🎉")
                }

                TopScreenIntent.ClickDismissConfirmParticipateDialog -> showConfirmParticipateSchedule.emit(null)
                TopScreenIntent.ClickScheduleList -> _events.emit(_events.value + TopScreenEvent.NavigateToScheduleList)
                TopScreenIntent.ClickSettings -> _events.emit(_events.value + TopScreenEvent.NavigateToSettings)
            }
        }
    }

    fun consume(event: TopScreenEvent) {
        stateMachineScope.launch {
            _events.emit(_events.value.filterNot { it == event })
        }
    }
}
