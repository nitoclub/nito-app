package club.nito.feature.schedule.detail

import club.nito.core.common.NitoDateFormatter
import club.nito.core.domain.FetchMyParticipantStatusUseCase
import club.nito.core.domain.FetchParticipantScheduleByIdUseCase
import club.nito.core.domain.ParticipateUseCase
import club.nito.core.domain.model.ParticipantSchedule
import club.nito.core.model.FetchSingleContentResult
import club.nito.core.model.handleResult
import club.nito.core.model.participant.ParticipantStatus
import club.nito.core.model.schedule.ScheduleId
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
    id: ScheduleId,
    fetchParticipantScheduleById: FetchParticipantScheduleByIdUseCase,
    private val fetchMyParticipantStatus: FetchMyParticipantStatusUseCase,
    private val participate: ParticipateUseCase,
    public val userMessageStateHolder: UserMessageStateHolder,
    private val dateTimeFormatter: NitoDateFormatter,
) : StateMachine(),
    UserMessageStateHolder by userMessageStateHolder {
    private val participantSchedule: MutableStateFlow<FetchSingleContentResult<ParticipantSchedule>> =
        MutableStateFlow(FetchSingleContentResult.Loading)
    private val myParticipantStatus: MutableStateFlow<FetchSingleContentResult<ParticipantStatus>> =
        MutableStateFlow(FetchSingleContentResult.Loading)

    public val uiState: StateFlow<ScheduleDetailScreenUiState> = buildUiState(
        participantSchedule,
        myParticipantStatus,
    ) { participantSchedule, myParticipantStatus ->
        ScheduleDetailScreenUiState(
            dateFormatter = dateTimeFormatter,
            schedule = participantSchedule,
            myParticipantStatus = myParticipantStatus,
        )
    }

    private val _events = MutableStateFlow<List<ScheduleDetailEvent>>(emptyList())
    public val event: Flow<ScheduleDetailEvent?> = _events.map { it.firstOrNull() }

    init {
        viewModelScope.launch {
            launch {
                participantSchedule.value = fetchParticipantScheduleById(id = id)
            }
            launch {
                myParticipantStatus.value = fetchMyParticipantStatus(id = id)
            }
        }
    }

    public fun dispatch(intent: ScheduleDetailIntent) {
        viewModelScope.launch {
            when (intent) {
                is ScheduleDetailIntent.ClickParticipantUser -> {
                    // TODO: ユーザー詳細画面へ遷移する
                }

                is ScheduleDetailIntent.ClickParticipantStatusChip -> {
                    // NOTE: 失敗時の復元用キャッシュ
                    val cachedParticipantStatus = myParticipantStatus.value
                    // NOTE: 成功可否に関わらず一旦選択した状態を反映する
                    myParticipantStatus.value = FetchSingleContentResult.Success(intent.status)

                    participate(intent.schedule.id, intent.status).handleResult(
                        onSuccess = { participant ->
                            myParticipantStatus.value = FetchSingleContentResult.Success(participant.status)

                            val scheduledAt = dateTimeFormatter.formatDateTime(intent.schedule.scheduledAt)
                            val message = when (participant.status) {
                                ParticipantStatus.NONE -> return@handleResult
                                ParticipantStatus.PENDING -> "$scheduledAt を未定にしました"
                                ParticipantStatus.ATTENDANCE -> "$scheduledAt に参加登録しました 🎉"
                                ParticipantStatus.ABSENCE -> "$scheduledAt を欠席にしました"
                            }
                            userMessageStateHolder.showMessage(message)
                        },
                        onFailure = {
                            // NOTE: 失敗した場合はキャッシュを復元する
                            myParticipantStatus.value = cachedParticipantStatus
                        },
                    )
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
