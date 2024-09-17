package club.nito.feature.schedule.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import club.nito.core.common.NitoDateFormatter
import club.nito.core.designsystem.component.CenterAlignedTopAppBar
import club.nito.core.designsystem.component.Scaffold
import club.nito.core.designsystem.component.Text
import club.nito.core.model.FetchMultipleContentResult
import club.nito.core.model.FetchSingleContentResult
import club.nito.core.model.participant.ParticipantStatus
import club.nito.core.model.participant.ParticipantUser
import club.nito.core.model.schedule.ScheduleId
import club.nito.core.model.schedule.ScheduleWithPlace
import club.nito.core.ui.ProfileImage
import club.nito.core.ui.koinStateMachine
import club.nito.core.ui.message.SnackbarMessageEffect
import coil3.compose.AsyncImage
import org.koin.core.parameter.parametersOf

@Composable
public fun ScheduleDetailRoute(
    id: ScheduleId,
    stateMachine: ScheduleDetailStateMachine = koinStateMachine(ScheduleDetailStateMachine::class) {
        parametersOf(id)
    },
    hideTopAppBar: Boolean = false,
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
        hideTopAppBar = hideTopAppBar,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScheduleDetailScreen(
    uiState: ScheduleDetailScreenUiState,
    snackbarHostState: SnackbarHostState,
    dispatch: (ScheduleDetailIntent) -> Unit = {},
    hideTopAppBar: Boolean = false,
) {
    val layoutDirection = LocalLayoutDirection.current
    val localDensity = LocalDensity.current

    val schedule = uiState.scheduleWithPlace
    var bottomParticipateBarHeightDp by remember {
        mutableStateOf(0.dp)
    }

    Scaffold(
        topBar = {
            if (hideTopAppBar.not()) {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "スケジュール詳細",
                        )
                    },
                )
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        content = { innerPadding ->
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                val containerModifier = Modifier
                    .padding(
                        start = innerPadding.calculateStartPadding(layoutDirection),
                        end = innerPadding.calculateEndPadding(layoutDirection),
                    )
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)

                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(
                            top = innerPadding.calculateTopPadding(),
                            bottom = innerPadding.calculateBottomPadding(),
                        )
                        .padding(bottom = bottomParticipateBarHeightDp)
                        .padding(vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(40.dp),
                ) {
                    when (schedule) {
                        FetchSingleContentResult.Loading -> Box(
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center),
                            )
                        }

                        FetchSingleContentResult.NoContent -> Box(
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            Text(
                                text = "スケジュールが見つかりませんでした",
                                modifier = Modifier.align(Alignment.Center),
                            )
                        }

                        is FetchSingleContentResult.Success -> {
                            VenueSection(
                                schedule = schedule.data,
                                dateFormatter = uiState.dateFormatter,
                                modifier = containerModifier,
                            )

                            MeetSection(
                                schedule = schedule.data,
                                dateFormatter = uiState.dateFormatter,
                                modifier = containerModifier,
                            )
                        }

                        is FetchSingleContentResult.Failure -> Box(
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            Text(
                                text = schedule.error?.message ?: "エラーが発生しました",
                                modifier = Modifier.align(Alignment.Center),
                            )
                        }
                    }

                    ParticipantSection(
                        users = uiState.users,
                        onClickParticipantUser = { dispatch(ScheduleDetailIntent.ClickParticipantUser(it)) },
                        modifier = containerModifier,
                        contentPadding = PaddingValues(horizontal = 8.dp),
                    )
                }

                BottomParticipateBar(
                    status = uiState.myParticipantStatus,
                    onClickParticipateChip = {
                        dispatch(ScheduleDetailIntent.ClickParticipantStatusChip.Participate)
                    },
                    onClickAbsentChip = {
                        dispatch(ScheduleDetailIntent.ClickParticipantStatusChip.Absent)
                    },
                    onClickHoldChip = {
                        dispatch(ScheduleDetailIntent.ClickParticipantStatusChip.Hold)
                    },
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            bottomParticipateBarHeightDp = with(localDensity) { coordinates.size.height.toDp() }
                        },
                    innerPadding = innerPadding,
                )
            }
        },
    )
}

@Composable
private fun VenueSection(
    schedule: ScheduleWithPlace,
    dateFormatter: NitoDateFormatter,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSecondary,
                shape = RoundedCornerShape(16.dp),
            ),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 200.dp),
        ) {
            AsyncImage(
                model = schedule.venue.imageUrl,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth,
                contentDescription = null,
            )

            Text(
                text = "開催情報",
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
                    .background(
                        color = Color.White.copy(alpha = 0.5f),
                        shape = CircleShape,
                    )
                    .padding(vertical = 4.dp, horizontal = 8.dp),
                fontSize = 14.sp,
                color = Color.Black,
            )
        }

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                text = schedule.venue.name,
            )
            Text(
                text = dateFormatter.formatDateTime(schedule.scheduledAt),
            )
            Text(
                text = schedule.description,
            )
        }
    }
}

@Composable
private fun MeetSection(
    schedule: ScheduleWithPlace,
    dateFormatter: NitoDateFormatter,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSecondary,
                shape = RoundedCornerShape(16.dp),
            ),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 200.dp),
        ) {
            AsyncImage(
                model = schedule.meet.imageUrl,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth,
                contentDescription = null,
            )

            Text(
                text = "集合情報",
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
                    .background(
                        color = Color.White.copy(alpha = 0.5f),
                        shape = CircleShape,
                    )
                    .padding(vertical = 4.dp, horizontal = 8.dp),
                fontSize = 14.sp,
                color = Color.Black,
            )
        }

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                text = schedule.meet.name,
            )
            Text(
                text = dateFormatter.formatDateTime(schedule.metAt),
            )
        }
    }
}

@Composable
private fun ParticipantSection(
    users: FetchMultipleContentResult<ParticipantUser>,
    onClickParticipantUser: (ParticipantUser) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        Text(
            text = "参加情報",
            modifier = Modifier.padding(contentPadding),
            fontSize = 20.sp,
        )

        when (users) {
            FetchMultipleContentResult.Loading -> CircularProgressIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )

            FetchMultipleContentResult.NoContent -> Text(
                text = "参加者がいません",
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )

            is FetchMultipleContentResult.Success -> Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                users.data.forEach { user ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onClickParticipantUser(user) }
                            .padding(contentPadding)
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        ProfileImage(
                            profile = user.profile,
                            modifier = Modifier.size(48.dp),
                        )
                        Spacer(Modifier.width(16.dp))
                        Text(
                            text = user.profile.displayName,
                            modifier = Modifier.weight(1f),
                        )
                        Spacer(Modifier.width(16.dp))
                        Text(
                            text = when (user.status) {
                                ParticipantStatus.NONE -> ""
                                ParticipantStatus.PENDING -> "未定"
                                ParticipantStatus.ATTENDANCE -> "参加"
                                ParticipantStatus.ABSENCE -> "欠席"
                            },
                        )
                    }
                }
            }

            is FetchMultipleContentResult.Failure -> Text(
                text = users.error?.message ?: "エラーが発生しました",
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BottomParticipateBar(
    status: ParticipantStatus,
    onClickParticipateChip: () -> Unit,
    onClickAbsentChip: () -> Unit,
    onClickHoldChip: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = lerp(
        start = MaterialTheme.colorScheme.secondaryContainer,
        stop = MaterialTheme.colorScheme.background,
        fraction = 0.7f,
    ),
    innerPadding: PaddingValues = PaddingValues(),
    layoutDirection: LayoutDirection = LocalLayoutDirection.current,
) {
    val insetPadding = remember(modifier, innerPadding, layoutDirection) {
        PaddingValues(
            start = innerPadding.calculateStartPadding(layoutDirection = layoutDirection),
            end = innerPadding.calculateEndPadding(layoutDirection = layoutDirection),
            bottom = innerPadding.calculateBottomPadding(),
        )
    }

    Row(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp,
                ),
            )
            .padding(insetPadding)
            .heightIn(min = 80.dp)
            .padding(horizontal = 16.dp)
            .padding(
                top = 16.dp,
                bottom = 8.dp,
            ),
        horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.End),
    ) {
        val chipModifier = Modifier
            .align(Alignment.CenterVertically)
            .padding(all = 8.dp)

        InputChip(
            selected = status == ParticipantStatus.ATTENDANCE,
            onClick = onClickParticipateChip,
            label = {
                Text(
                    text = "参加",
                    modifier = chipModifier,
                )
            },
            shape = CircleShape,
        )

        InputChip(
            selected = status == ParticipantStatus.ABSENCE,
            onClick = onClickAbsentChip,
            label = {
                Text(
                    text = "欠席",
                    modifier = chipModifier,
                )
            },
            shape = CircleShape,
        )

        InputChip(
            selected = status == ParticipantStatus.PENDING,
            onClick = onClickHoldChip,
            label = {
                Text(
                    text = "未定",
                    modifier = chipModifier,
                )
            },
            shape = CircleShape,
        )
    }
}
