package club.nito.feature.top

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import club.nito.core.common.NitoDateTimeFormatter
import club.nito.core.domain.GetRecentScheduleUseCase
import club.nito.core.domain.model.ParticipantSchedule
import club.nito.core.model.FetchSingleContentResult
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
class TopViewModel @Inject constructor(
    getRecentScheduleUseCase: GetRecentScheduleUseCase,
    val userMessageStateHolder: UserMessageStateHolder,
    private val dateTimeFormatter: NitoDateTimeFormatter,
) : ViewModel(),
    UserMessageStateHolder by userMessageStateHolder {
    private val showConfirmParticipateSchedule = MutableStateFlow<ParticipantSchedule?>(null)

    private val recentSchedule = getRecentScheduleUseCase().stateIn(
        scope = viewModelScope,
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

    private val _events = MutableStateFlow<List<TopEvent>>(emptyList())
    val event: Flow<TopEvent?> = _events.map { it.firstOrNull() }

    fun dispatch(intent: TopIntent) {
        viewModelScope.launch {
            when (intent) {
                is TopIntent.ClickShowConfirmParticipateDialog -> showConfirmParticipateSchedule.emit(intent.schedule)
                is TopIntent.ClickParticipateSchedule -> {
                    showConfirmParticipateSchedule.emit(null)

                    val scheduledAt = dateTimeFormatter.formatDateTimeString(intent.schedule.scheduledAt)
                    userMessageStateHolder.showMessage("$scheduledAt に参加登録しました 🎉")
                }

                TopIntent.ClickDismissConfirmParticipateDialog -> showConfirmParticipateSchedule.emit(null)
                TopIntent.ClickScheduleList -> _events.emit(_events.value + TopEvent.NavigateToScheduleList)
                TopIntent.ClickSettings -> _events.emit(_events.value + TopEvent.NavigateToSettings)
            }
        }
    }

    fun consume(event: TopEvent) {
        viewModelScope.launch {
            _events.emit(_events.value.filterNot { it == event })
        }
    }
}
