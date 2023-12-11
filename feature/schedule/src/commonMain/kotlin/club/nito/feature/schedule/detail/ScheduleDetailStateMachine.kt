package club.nito.feature.schedule.detail

import club.nito.core.common.NitoDateFormatter
import club.nito.core.domain.MyParticipantStatusStreamUseCase
import club.nito.core.domain.ParticipateUseCase
import club.nito.core.domain.ScheduleParticipantsStreamUseCase
import club.nito.core.domain.ScheduleStreamUseCase
import club.nito.core.domain.model.ParticipantSchedule
import club.nito.core.model.FetchMultipleContentResult
import club.nito.core.model.FetchSingleContentResult
import club.nito.core.model.handleResult
import club.nito.core.model.participant.ParticipantStatus
import club.nito.core.model.participant.ParticipantUser
import club.nito.core.model.schedule.ScheduleId
import club.nito.core.model.schedule.ScheduleWithPlace
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
import moe.tlaster.precompose.viewmodel.viewModelScope

public class ScheduleDetailStateMachine(
    private val scheduleId: ScheduleId,
    scheduleStream: ScheduleStreamUseCase,
    scheduleParticipantsStream: ScheduleParticipantsStreamUseCase,
    myParticipantStatusStream: MyParticipantStatusStreamUseCase,
    private val participate: ParticipateUseCase,
    public val userMessageStateHolder: UserMessageStateHolder,
    private val dateTimeFormatter: NitoDateFormatter,
) : StateMachine(),
    UserMessageStateHolder by userMessageStateHolder {
    private val participantSchedule: MutableStateFlow<FetchSingleContentResult<ParticipantSchedule>> =
        MutableStateFlow(FetchSingleContentResult.Loading)

    private val schedule: StateFlow<FetchSingleContentResult<ScheduleWithPlace>> =
        scheduleStream(id = scheduleId).stateIn(
            scope = stateMachineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = FetchSingleContentResult.Loading,
        )

    private val participants: StateFlow<FetchMultipleContentResult<ParticipantUser>> =
        scheduleParticipantsStream(id = scheduleId).stateIn(
            scope = stateMachineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = FetchMultipleContentResult.Loading,
        )

    private val myParticipantStatus: StateFlow<ParticipantStatus> =
        myParticipantStatusStream(id = scheduleId).stateIn(
            scope = stateMachineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ParticipantStatus.NONE,
        )

    public val uiState: StateFlow<ScheduleDetailScreenUiState> = buildUiState(
        schedule,
        participantSchedule,
        participants,
        myParticipantStatus,
    ) { schedule, participantSchedule, users, myParticipantStatus ->
        ScheduleDetailScreenUiState(
            dateFormatter = dateTimeFormatter,
            participantSchedule = participantSchedule,
            scheduleWithPlace = schedule,
            users = users,
            myParticipantStatus = myParticipantStatus,
        )
    }

    private val _events = MutableStateFlow<List<ScheduleDetailEvent>>(emptyList())
    public val event: Flow<ScheduleDetailEvent?> = _events.map { it.firstOrNull() }

    init {
        viewModelScope.launch {
        }
    }

    public fun dispatch(intent: ScheduleDetailIntent) {
        viewModelScope.launch {
            when (intent) {
                is ScheduleDetailIntent.ClickParticipantUser -> {
                    // TODO: ユーザー詳細画面へ遷移する
                }

                is ScheduleDetailIntent.ClickParticipantStatusChip -> participate(
                    scheduleId = scheduleId,
                    oldStatus = myParticipantStatus.value,
                    newStatus = intent.status,
                ).handleResult(
                    onSuccess = { participant ->
                        userMessageStateHolder.showMessage(
                            when (participant.status) {
                                ParticipantStatus.NONE -> return@handleResult
                                ParticipantStatus.PENDING -> "未定にしました"
                                ParticipantStatus.ATTENDANCE -> "参加登録しました 🎉"
                                ParticipantStatus.ABSENCE -> "欠席にしました"
                            }
                        )
                    },
                    onFailure = {
                        userMessageStateHolder.showMessage(it?.message ?: "エラーが発生しました")
                    },
                )
            }
        }
    }

    public fun consume(event: ScheduleDetailEvent) {
        viewModelScope.launch {
            _events.emit(_events.value.filterNot { it == event })
        }
    }
}
