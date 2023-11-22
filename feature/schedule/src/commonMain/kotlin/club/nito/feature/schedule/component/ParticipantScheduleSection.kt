package club.nito.feature.schedule.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import club.nito.core.common.NitoDateTimeFormatter
import club.nito.core.designsystem.component.Text
import club.nito.core.domain.model.ParticipantSchedule
import club.nito.core.model.FetchMultipleContentResult
import club.nito.core.model.NitoError
import club.nito.core.ui.ParticipantScheduleItem

@Composable
internal fun ScheduleListSection(
    scheduleList: FetchMultipleContentResult<ParticipantSchedule>,
    dateTimeFormatter: NitoDateTimeFormatter,
    modifier: Modifier = Modifier,
    onScheduleClick: (schedule: ParticipantSchedule) -> Unit = {},
) {
    when (scheduleList) {
        FetchMultipleContentResult.Loading -> LoadingParticipantSchedule(modifier = modifier)
        FetchMultipleContentResult.NoContent -> NoParticipantSchedule(modifier = modifier)
        is FetchMultipleContentResult.Success -> ParticipantScheduleList(
            modifier = modifier,
            schedules = scheduleList.data,
            dateTimeFormatter = dateTimeFormatter,
            onScheduleClick = onScheduleClick,
        )

        is FetchMultipleContentResult.Failure -> FailureParticipantSchedule(
            error = scheduleList.error,
            modifier = modifier,
        )
    }
}

@Composable
private fun LoadingParticipantSchedule(
    modifier: Modifier = Modifier,
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically,
) {
    CircularProgressIndicator()
}

@Composable
private fun NoParticipantSchedule(
    modifier: Modifier = Modifier,
) = Column(
    modifier = modifier.padding(8.dp),
    verticalArrangement = Arrangement.Center,
) {
    Text(text = "スケジュールがありません")
}

@Composable
private fun ParticipantScheduleList(
    schedules: List<ParticipantSchedule>,
    dateTimeFormatter: NitoDateTimeFormatter,
    modifier: Modifier = Modifier,
    onScheduleClick: (schedule: ParticipantSchedule) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(
            items = schedules,
            key = { schedule -> schedule.id },
        ) { schedule ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            ) {
                ParticipantScheduleItem(
                    schedule = schedule,
                    dateTimeFormatter = dateTimeFormatter,
                    onScheduleClick = onScheduleClick,
                )
            }
        }
    }
}

@Composable
private fun FailureParticipantSchedule(
    error: NitoError?,
    modifier: Modifier = Modifier,
) = Column(
    modifier = modifier.padding(8.dp),
    verticalArrangement = Arrangement.Center,
) {
    Text(text = error?.message ?: "スケジュールの取得に失敗しました")
}
